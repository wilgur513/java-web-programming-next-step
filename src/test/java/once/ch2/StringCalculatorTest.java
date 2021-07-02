package once.ch2;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;


public class StringCalculatorTest {
    private StringCalculator calculator;

    @Before
    public void setUp() throws Exception {
        calculator = new StringCalculator();
    }

    @Test
    public void shouldReturnZeroListIsEmpty() {
        assertThat(calculator.calculate(new ArrayList<>()), is(0));
    }
    
    @Test
    public void shouldReturnListOfSum() {
        assertThat(calculator.calculate(Arrays.asList("1", "4", "2", "5", "3")), is(15));
    }
}
