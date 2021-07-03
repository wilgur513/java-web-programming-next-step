package book.ch2;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StringCalculatorTest {
    private StringCalculator cal;

    @Before
    public void setUp() throws Exception {
        cal = new StringCalculator();
    }

    @Test
    public void add_null_또는_빈문자() {
        assertThat(cal.add(null), is(0));
        assertThat(cal.add(""), is(0));
    }

    @Test
    public void add_숫자하나() {
        assertThat(cal.add("1"), is(1));
    }
}
