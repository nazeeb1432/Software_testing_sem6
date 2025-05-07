package math;

import io.FileIO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayOperationsTest {

    FileIO fileIO = new FileIO();
    MyMath myMath = new MyMath();
    ArrayOperations arrayOperations = new ArrayOperations();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findPrimesInFile() {
        int[] expected={3,2,7,13};
        int[] actual = arrayOperations.findPrimesInFile(fileIO,"src/test/resources/array_input.txt",myMath);
        assertArrayEquals(expected,actual);
    }
}