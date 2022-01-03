import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Frame {

    private List<Roll> rolls;
    private int index;

    /**
     * Add roll to the frame, remaining making a shot
     *
     * @param value
     * @throws Exception
     */
    public void addRoll(String value) throws Exception {
        checkValidRolls(value);
        this.rolls.add(new Roll(this.rolls.size() + 1, value));

    }

    /**
     * Check if roll are valid given a value
     *
     * @param value This parameter is the value of the current roll
     * @throws Exception
     */
    public void checkValidRolls(String value) throws Exception {

        // All frames except last one
        if (this.index < 10) {
            // 1st roll
            if (this.rolls.isEmpty()) {
                // SPARE in 1st roll
                if (RollValuesEnum.SPARE.getValue().equals(value)) {
                    throw new Exception("Cannot SPARE in one shot");
                }

                // 2nd roll
            } else if (this.rolls.size() == 1) {
                // STRIKE => cannot roll twice
                if (RollValuesEnum.STRIKE.getValue().equals(this.rolls.get(0).getValue())) {
                    throw new Exception("Cannot roll twice if STRIKE");
                }

                // Not SPARE and result of roll 1 + result of roll 2 > 10
                if (!RollValuesEnum.SPARE.getValue().equals(value) && RollValuesEnum.getResultByValue(this.rolls.get(0).getValue()) + RollValuesEnum.getResultByValue(value) >= 10) {
                    throw new Exception("Frame : " + this.getIndex() + " Invalid result for 2nd roll");
                }

            } else throw new Exception("Already shot twice");
        }
        // 10th frame
        else {
            // 1st roll
            if (this.rolls.isEmpty()) {
                // SPARE in 1st roll
                if (RollValuesEnum.SPARE.getValue().equals(value)) {
                    throw new Exception("Cannot SPARE in one shot");
                }
            } // 2nd roll
            else if (this.rolls.size() == 1) {
                // Not SPARE and result of roll 1 + result of roll 2 HIGHER OR EQUALS TO 10
                if ((!RollValuesEnum.SPARE.getValue().equals(value) && !RollValuesEnum.STRIKE.getValue().equals(this.rolls.get(0).getValue())
                        && RollValuesEnum.getResultByValue(this.rolls.get(0).getValue()) + RollValuesEnum.getResultByValue(value) >= 10)
                        || RollValuesEnum.SPARE.getValue().equals(value) && RollValuesEnum.STRIKE.getValue().equals(this.rolls.get(0).getValue())) {
                    throw new Exception("Frame : " + this.getIndex() + " Invalid result for 2nd roll");
                }
            }// 3rd roll
            else if (this.rolls.size() == 2) {
                // Has to STRIKE or SPARE to rolls 3rd times
                // Cannot SPARE just after a STRIKE
                // Cannot roll a 3rd time if :
                // Not SPARE at current roll, NOR SPARE at 2nd roll, NOR STRIKE at 1st roll AND result 2nd roll + result current roll HIGHER OR EQUALS TO 10
                if ((!RollValuesEnum.STRIKE.getValue().equals(this.rolls.get(0).getValue()) && !RollValuesEnum.SPARE.getValue().equals(this.rolls.get(1).getValue()))
                        || (RollValuesEnum.SPARE.getValue().equals(value) && RollValuesEnum.STRIKE.getValue().equals(this.rolls.get(1).getValue()))
                        || (!RollValuesEnum.SPARE.getValue().equals(value)
                        && !RollValuesEnum.SPARE.getValue().equals(this.rolls.get(1).getValue())
                        && !RollValuesEnum.STRIKE.getValue().equals(this.rolls.get(0).getValue())
                        && RollValuesEnum.getResultByValue(this.rolls.get(1).getValue()) + RollValuesEnum.getResultByValue(value) >= 10)) {
                    throw new Exception("Frame : " + this.getIndex() + " Invalid result for 3rd roll");
                }

            }
        }
    }

    /**
     * Chech if the result match with a given valid value
     * Example : STRIKE, SPARE, MISS, "1"
     *
     * @param val
     * @return
     */
    public boolean checkResult(RollValuesEnum val) {
        return this.rolls.stream()
                .map(Roll::getValue)
                .anyMatch(v -> v.equals(val.getValue()));
    }

    /**
     * Give the result of the frame
     *
     * @return
     */
    public int result() {
        // Case where there is no rolls, the result is 0
        if (this.rolls.isEmpty()) {
            return 0;
            // Case where there is only 1 roll done, the result is the result of this roll
        } else if (this.rolls.size() == 1) {
            return this.rolls.get(0).result();

            // Case where 2 rolls have been done AND SPARE, the result is 10
        } else if (this.rolls.size() == 2 && checkResult(RollValuesEnum.SPARE)) {
            return RollValuesEnum.SPARE.getResult();

            // Case where 3 rolls have been done AND SPARE
        } else if (this.rolls.size() == 3 && checkResult(RollValuesEnum.SPARE)) {
            // If SPARE at 2nd rolls, the result is 10 + the result of the 3rd roll
            if (RollValuesEnum.SPARE.getValue().equals(this.rolls.get(1).getValue())) {

                return RollValuesEnum.SPARE.getResult() + this.rolls.get(2).result();
            }
            // Else, result is 10 + te result of the 1st roll
            return RollValuesEnum.SPARE.getResult() + this.rolls.get(0).result();

            // Else, result is the sum of all results of the frame
        } else return this.rolls.stream()
                .map(Roll::result)
                .reduce(0, Integer::sum);
    }
}