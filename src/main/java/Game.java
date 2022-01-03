import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;

@Data
public class Game {

    private Frame[] frames;
    private int[] scores = new int[10];
    private int currentFrameIndex = 0;

    public Game() throws Exception {
        this.frames = new Frame[]{
                new Frame(new ArrayList<>(), 1),
                new Frame(new ArrayList<>(), 2),
                new Frame(new ArrayList<>(), 3),
                new Frame(new ArrayList<>(), 4),
                new Frame(new ArrayList<>(), 5),
                new Frame(new ArrayList<>(), 6),
                new Frame(new ArrayList<>(), 7),
                new Frame(new ArrayList<>(), 8),
                new Frame(new ArrayList<>(), 9),
                new Frame(new ArrayList<>(), 10)};
        checkValidFrames();
        Arrays.fill(this.scores, 0);
    }

    /**
     * Check we've the exact number of frames
     *
     * @throws Exception
     */
    public void checkValidFrames() throws Exception {
        if (this.frames.length != 10) {
            throw new Exception("Invalid number of frames");

        }
    }

    /**
     * Calculate score of differents frames and total score
     *
     * @return the total score
     */
    public int calculateScore() {
        for (Frame f : frames) {
            var curFrame = this.frames[currentFrameIndex];
            Frame nextFrame = getNextFrame();

            if (curFrame.getRolls().size() >= 1 && null != nextFrame) {
                nextFrame = this.frames[currentFrameIndex + 1];

                // STRIKE
                if (curFrame.checkResult(RollValuesEnum.STRIKE)) {
                    if (nextFrame.getRolls().size() == 1 && !nextFrame.checkResult(RollValuesEnum.STRIKE)) {

                        scores[currentFrameIndex] = RollValuesEnum.STRIKE.getResult();
                    } else if (nextFrame.getRolls().size() >= 1 && nextFrame.checkResult(RollValuesEnum.STRIKE)) {
                        scores[currentFrameIndex] = RollValuesEnum.STRIKE.getResult() * 2 + this.frames[currentFrameIndex + 1].getRolls().get(0).result();

                    } else {
                        scores[currentFrameIndex] = RollValuesEnum.STRIKE.getResult() + nextFrame.result();

                    }

                }
                // SPARE
                else if (curFrame.checkResult(RollValuesEnum.SPARE)) {

                    if (!nextFrame.getRolls().isEmpty()) {
                        scores[currentFrameIndex] = RollValuesEnum.SPARE.getResult() + nextFrame.getRolls().get(0).result();

                    } else scores[currentFrameIndex] = RollValuesEnum.SPARE.getResult();
                } else {
                    scores[currentFrameIndex] = curFrame.result();
                }
            }
            currentFrameIndex++;
        }
        // Last frame
        scores[9] = frames[9].result();

        return Arrays.stream(scores)
                .reduce(0, Integer::sum);

    }

    /**
     * Get the next frame if exist..
     *
     * @return The next frame..
     */
    public Frame getNextFrame() {
        if (currentFrameIndex < 9) {
            return this.frames[currentFrameIndex + 1];
        }
        return null;
    }

}
