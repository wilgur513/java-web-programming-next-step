package once.ch2;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class StringExpressionTest {
    @Test
    public void shouldNotHasCustomDelimiter() {
        StringExpression expression = new StringExpression("1,2,3");
        assertThat(expression.hasCustomDelimiter(), is(false));
    }

    @Test
    public void shouldHasCustomerDelimiter() {
        StringExpression expression = new StringExpression("//;\n1;2;3");
        assertThat(expression.hasCustomDelimiter(), is(true));
    }

    @Test
    public void shouldGetExpression() {
        StringExpression expression = new StringExpression("1,2,3");
        assertThat(expression.getExpression(), is("1,2,3"));
    }

    @Test
    public void shouldGetExpressionWithoutCustomDelimiterHeader() {
        StringExpression expression = new StringExpression("//;\n1;2;3");
        assertThat(expression.getExpression(), is("1;2;3"));
    }

    @Test
    public void shouldGetCustomDelimiter() {
        StringExpression expression = new StringExpression("//;\n1;2;3");
        assertThat(expression.getCustomDelimiter(), is(";"));
    }

    @Test
    public void isBlank() {
        StringExpression expression = new StringExpression("");
        assertThat(expression.isBlank(), is(true));

        expression = new StringExpression("1,2,3");
        assertThat(expression.isBlank(), is(false));
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowException() {
        StringExpression expression = new StringExpression("1,2,3");
        expression.getCustomDelimiter();
    }
}
