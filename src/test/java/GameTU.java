import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTU {

    @Test
    public void testGetNextFrame() throws Exception {
        var game = buildDatas1();
        game.setCurrentFrameIndex(8);

        var frame1 = game.getNextFrame();
        game.setCurrentFrameIndex(9);
        var frame2 = game.getNextFrame();

        assertTrue(frame1 != null && frame2 == null);
    }

    @Test
    public void testCalculateScore() throws Exception {

        var game1 = buildDatas1();
        var game2 = buildDatas2();
        var game3 = buildDatas3();

        int[] scores1 = new int[10];
        Arrays.fill(scores1, 30);

        int[] scores2 = new int[10];
        Arrays.fill(scores2, 9);

        int[] scores3 = new int[10];
        Arrays.fill(scores3, 15);


        int score1 = game1.calculateScore();
        int score2 = game2.calculateScore();
        int score3 = game3.calculateScore();
        assertEquals(score1, 300);
        assertEquals(score2, 90);
        assertEquals(score3, 150);

        assertTrue(Arrays.equals(game1.getScores(), scores1));
        assertTrue(Arrays.equals(game2.getScores(), scores2));
        assertTrue(Arrays.equals(game3.getScores(), scores3));

        System.out.println(Arrays.toString(scores1) + " Total : " + score1);
        System.out.println(Arrays.toString(scores2) + "           Total : " + score2);
        System.out.println(Arrays.toString(scores3) + " Total : " + score3);
    }

    /****************************************
     * Prepare differents senarios
     * @return Game
     *
     *****************************************/

    // X X X X X X X X X X X X
    public Game buildDatas1() throws Exception {

        var game = new Game();
        game.getFrames()[0].addRoll("X");

        game.getFrames()[1].addRoll("X");

        game.getFrames()[2].addRoll("X");

        game.getFrames()[3].addRoll("X");

        game.getFrames()[4].addRoll("X");

        game.getFrames()[5].addRoll("X");

        game.getFrames()[6].addRoll("X");

        game.getFrames()[7].addRoll("X");

        game.getFrames()[8].addRoll("X");

        game.getFrames()[9].addRoll("X");
        game.getFrames()[9].addRoll("X");
        game.getFrames()[9].addRoll("X");

        return game;
    }

    // 9- 9- 9- 9- 9- 9- 9- 9- 9- 9-
    public Game buildDatas2() throws Exception {
        var game = new Game();
        game.getFrames()[0].addRoll("9");
        game.getFrames()[0].addRoll("-");

        game.getFrames()[1].addRoll("9");
        game.getFrames()[1].addRoll("-");

        game.getFrames()[2].addRoll("9");
        game.getFrames()[2].addRoll("-");

        game.getFrames()[3].addRoll("9");
        game.getFrames()[3].addRoll("-");

        game.getFrames()[4].addRoll("9");
        game.getFrames()[4].addRoll("-");

        game.getFrames()[5].addRoll("9");
        game.getFrames()[5].addRoll("-");

        game.getFrames()[6].addRoll("9");
        game.getFrames()[6].addRoll("-");

        game.getFrames()[7].addRoll("9");
        game.getFrames()[7].addRoll("-");

        game.getFrames()[8].addRoll("9");
        game.getFrames()[8].addRoll("-");

        game.getFrames()[9].addRoll("9");
        game.getFrames()[9].addRoll("-");

        return game;
    }

    // 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5
    public Game buildDatas3() throws Exception {
        var game = new Game();
        game.getFrames()[0].addRoll("5");
        game.getFrames()[0].addRoll("/");

        game.getFrames()[1].addRoll("5");
        game.getFrames()[1].addRoll("/");

        game.getFrames()[2].addRoll("5");
        game.getFrames()[2].addRoll("/");

        game.getFrames()[3].addRoll("5");
        game.getFrames()[3].addRoll("/");

        game.getFrames()[4].addRoll("5");
        game.getFrames()[4].addRoll("/");

        game.getFrames()[5].addRoll("5");
        game.getFrames()[5].addRoll("/");

        game.getFrames()[6].addRoll("5");
        game.getFrames()[6].addRoll("/");

        game.getFrames()[7].addRoll("5");
        game.getFrames()[7].addRoll("/");

        game.getFrames()[8].addRoll("5");
        game.getFrames()[8].addRoll("/");

        game.getFrames()[9].addRoll("5");
        game.getFrames()[9].addRoll("/");
        game.getFrames()[9].addRoll("5");

        return game;
    }
}
