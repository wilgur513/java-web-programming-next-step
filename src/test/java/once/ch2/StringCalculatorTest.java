package once.ch2;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;


public class StringCalculatorTest {
    @Test
    public void calculateCommaAndColonExpression() {
        StringSplitter splitter = new StringSplitter(new StringExpression("1,2:3,4:5"));
        NumberConvertor convertor = new NumberConvertor();
        StringCalculator calculator = new StringCalculator(convertor, splitter);

        assertThat(calculator.sum(), is(15));
    }

    @Test
    public void calculateCommaAndColonAndCustomDelimiter() {
        StringSplitter splitter = new StringSplitter(new StringExpression("//;\n1,2:3;4:5;6"));
        NumberConvertor convertor = new NumberConvertor();
        StringCalculator calculator = new StringCalculator(convertor, splitter);

        assertThat(calculator.sum(), is(21));
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowException() {
        StringSplitter splitter = new StringSplitter(new StringExpression("//;\n1,2:3;4:-5;6"));
        NumberConvertor convertor = new NumberConvertor();
        StringCalculator calculator = new StringCalculator(convertor, splitter);

        calculator.sum();
    }
}
