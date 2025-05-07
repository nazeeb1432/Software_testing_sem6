package math;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArithmeticOperationsTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void divide() {
        double expected= 3.0;
        double actual=(new ArithmeticOperations()).divide(18,6);
        assertEquals(expected,actual,1e-3);
    }

    @Test(expected=ArithmeticException.class)
    public void whether_denominator_is_zero(){
        (new ArithmeticOperations()).divide(18,0);
    }

    @Test()
    public void multiply() {
        int expected=30;
        int actual=(new ArithmeticOperations()).multiply(3,10);
        assertEquals(expected,actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whether_product_out_of_integer_bound(){
        (new ArithmeticOperations()).multiply(Integer.MAX_VALUE,2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whether_first_arg_is_negative(){
        (new ArithmeticOperations()).multiply(-2,2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whether_second_arg_is_negative(){
        (new ArithmeticOperations()).multiply(2,-2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whether_both_args_are_negative(){
        (new ArithmeticOperations()).multiply(-2,-2);
    }
}