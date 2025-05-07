package io;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FileIOTest {

    private FileIO fileIO = new FileIO();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void readFile() {
        int[] expected = {3,9,2,7,13,50,24,12};
        int[] actual = fileIO.readFile("F:\\Software Testing\\unittesting\\src\\test\\resources\\array_input.txt");
        assertArrayEquals(expected, actual);
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_file_does_not_exist() {
        fileIO.readFile("F:\\Software Testing\\unittesting\\src\\test\\resources\\gradu.txt");
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_file_is_empty() {
        fileIO.readFile("F:\\Software Testing\\unittesting\\src\\test\\resources\\empty_file.txt");
    }

    @Test (expected = NumberFormatException.class)
    public void test_for_invalid_entry() {
        fileIO.readFile("F:\\Software Testing\\unittesting\\src\\test\\resources\\grades_invalid.txt");
    }
}