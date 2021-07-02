package once.ch2;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class StringExpressionTest {
    @Test
    public void hasCustomDelimiter() {
        StringExpression expression = new StringExpression("1,2,3");
        assertThat(expression.hasCustomDelimiter(), is(false));
    }
}
