package book.ch2;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;

public class SplitTest {
    @Test
    public void split() {
        assertThat("1".split(","), arrayContainingInAnyOrder("1"));
        assertThat("1,2".split(","), arrayContainingInAnyOrder("1", "2"));
    }
}
