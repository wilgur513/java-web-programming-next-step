package once.ch2;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;


public class NumberConvertorTest {
    @Test
    public void shouldConvertToNumber() {
        NumberConvertor convertor = new NumberConvertor();
        assertThat(convertor.convert(Arrays.asList("1", "2", "3", "4")), is(containsInAnyOrder(1, 2, 3, 4)));
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowRuntimeException() {
        NumberConvertor convertor = new NumberConvertor();
        convertor.convert(Arrays.asList("1", "2", "-3"));
    }
}
