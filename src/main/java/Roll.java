import lombok.Data;

@Data
public class Roll {

    private final int index;
    private final String value;

    public Roll(int index, String value) throws Exception {

        checkValidRoll(index, value);
        this.index = index;
        this.value = value;
    }

    /**
     * Check if the roll is valid given the index and the value
     *
     * @param index
     * @param val
     * @throws Exception
     */
    public void checkValidRoll(int index, String val) throws Exception {
        if (!RollValuesEnum.isInEnum(val)) {
            throw new Exception("Invalid roll's value");
        } else if (index > 3 || index < 1) throw new Exception("Invalid roll's index");
    }

    /**
     * Give the result of the roll
     *
     * @return
     */
    public int result() {
        return RollValuesEnum.getResultByValue(this.getValue());
    }
}
