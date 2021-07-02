package once.ch2;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;


public class StringCalculatorTest {
    private StringCalculator calculator;

    @Before
    public void setUp() throws Exception {
        calculator = new StringCalculator();
    }

    @Test
    public void shouldReturnZeroWhenStringIsBlank() {
        assertThat(calculator.calculate(""), is(0));
    }
    
    @Test
    public void shouldReturnSumSplitByComma() {
         assertThat(calculator.calculate("1,2"), is(3));
    }

    @Test
    public void shouldReturnContinuousSum() {
         assertThat(calculator.calculate("1:3,4"), is(10));
    }
}
