package once.ch2;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;


public class StringCalculatorTest {
    @Test
    public void shouldReturnZeroWhenStringIsBlank() {
        StringCalculator calculator = new StringCalculator();
        assertThat(calculator.calculate(""), is(0));
    }
}
