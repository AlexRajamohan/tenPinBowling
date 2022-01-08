package test.java;

import main.java.Frame;
import main.java.RollValuesEnum;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class FrameTU {

    @Test
    public void shouldThrowCannotSpareInOneShot() {

        var frame = new Frame(new ArrayList<>(), 1);
        String expected = "Cannot SPARE in one shot";

        String actual = assertThrows(Exception.class, () -> {

            frame.checkValidRolls("/");
        }).getMessage();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowCannotRollTwiceIfStrike() throws Exception {

        var frame = new Frame(new ArrayList<>(), 1);
        frame.addRoll("X");

        String expected = "Cannot roll twice if STRIKE";

        String actual = assertThrows(Exception.class, () -> {

            frame.checkValidRolls("/");
        }).getMessage();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowFrame1InvalidResultFor2ndRoll() throws Exception {

        var frame = new Frame(new ArrayList<>(), 1);
        frame.addRoll("8");

        String expected = "Frame : 1 Invalid result for 2nd roll";

        String actual = assertThrows(Exception.class, () -> {

            frame.checkValidRolls("8");
        }).getMessage();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowAlreadyShotTwice() throws Exception {

        var frame = new Frame(new ArrayList<>(), 1);
        frame.addRoll("8");
        frame.addRoll("1");

        String expected = "Already shot twice";

        String actual = assertThrows(Exception.class, () -> {

            frame.checkValidRolls("8");
        }).getMessage();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowFrame10InvalidResultFor3rdRoll() throws Exception {

        String expected = "Frame : 10 Invalid result for 3rd roll";

        var frame1 = new Frame(new ArrayList<>(), 10);
        frame1.addRoll("X");
        frame1.addRoll("1");

        var frame2 = new Frame(new ArrayList<>(), 10);
        frame2.addRoll("3");
        frame2.addRoll("5");

        var frame3 = new Frame(new ArrayList<>(), 10);
        frame3.addRoll("X");
        frame3.addRoll("1");

        var frame4 = new Frame(new ArrayList<>(), 10);
        frame4.addRoll("X");
        frame4.addRoll("X");

        String actual1 = assertThrows(Exception.class, () -> {

            frame1.checkValidRolls("X");
        }).getMessage();

        String actual2 = assertThrows(Exception.class, () -> {

            frame2.checkValidRolls("1");
        }).getMessage();

        String actual3 = assertThrows(Exception.class, () -> {

            frame3.checkValidRolls("9");
        }).getMessage();

        String actual4 = assertThrows(Exception.class, () -> {

            frame4.checkValidRolls("/");
        }).getMessage();

        assertEquals(expected, actual1);
        assertEquals(expected, actual2);
        assertEquals(expected, actual3);
        assertEquals(expected, actual4);
    }

    @Test
    public void testAddRoll() throws Exception {
        var frame1 = new Frame(new ArrayList<>(), 10);
        frame1.addRoll("8");
        frame1.addRoll("/");
        frame1.addRoll("X");

        var frame2 = new Frame(new ArrayList<>(), 1);
        frame2.addRoll("-");

        assertEquals(frame1.getRolls().get(0).getValue(), "8");
        assertEquals(frame1.getRolls().get(1).getValue(), "/");
        assertEquals(frame1.getRolls().get(2).getValue(), "X");

        assertEquals(frame2.getRolls().get(0).getValue(), "-");
        assertEquals(frame2.getRolls().size(), 1);
    }

    @Test
    public void testCheckResult() throws Exception {
        var frame1 = new Frame(new ArrayList<>(), 10);
        frame1.addRoll("X");
        frame1.addRoll("8");

        var frame2 = new Frame(new ArrayList<>(), 1);
        frame2.addRoll("-");
        frame2.addRoll("/");

        assertTrue(frame1.checkResult(RollValuesEnum.STRIKE));
        assertTrue(frame1.checkResult(RollValuesEnum.EIGHT));
        assertTrue(frame2.checkResult(RollValuesEnum.MISS));
        assertTrue(frame2.checkResult(RollValuesEnum.SPARE));
    }

    @Test
    public void testResult() throws Exception {
        var frame1 = new Frame(new ArrayList<>(), 1);
        frame1.addRoll("X");

        var frame2 = new Frame(new ArrayList<>(), 2);
        frame2.addRoll("1");
        frame2.addRoll("/");

        var frame3 = new Frame(new ArrayList<>(), 2);
        frame3.addRoll("1");
        frame3.addRoll("-");

        assertEquals(10, frame1.result());
        assertEquals(10, frame2.result());
        assertEquals(1, frame3.result());
    }
}
