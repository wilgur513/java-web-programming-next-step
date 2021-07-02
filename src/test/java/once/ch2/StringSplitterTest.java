package once.ch2;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class StringSplitterTest {
    @Test
    public void shouldReturnEmptyList() {
        StringSplitter splitter = new StringSplitter("");
        assertThat(splitter.split().isEmpty(), is(true));
    }

    @Test
    public void shouldSplitComma() {
        StringSplitter splitter = new StringSplitter("1,2,3,4");
        assertThat(splitter.split(), containsInAnyOrder("1", "2", "3", "4"));
    }

    @Test
    public void shouldSplitColon() {
        StringSplitter splitter = new StringSplitter("2:4");
        assertThat(splitter.split(), containsInAnyOrder("2", "4"));
    }

    @Test
    public void shouldSplitCommaAndColon() {
        StringSplitter splitter = new StringSplitter("1,2,5:7,3:4");
        assertThat(splitter.split(), containsInAnyOrder("1", "2", "5", "7", "3", "4"));
    }

    @Test
    public void shouldSplitDuplicateNumber() {
        StringSplitter splitter = new StringSplitter("1,2,3:6,5,6");
        assertThat(splitter.split(), containsInAnyOrder("1", "2", "3", "6", "5", "6"));
    }

    @Test
    public void shouldSplitCustomDelimiter() {
        StringSplitter splitter = new StringSplitter("//;\n1;2;3");
        assertThat(splitter.split(), containsInAnyOrder("1", "2", "3"));
    }

    @Test
    public void shouldSplitCustomDelimiterAndComma() {
        StringSplitter splitter = new StringSplitter("//;\n1;2,3;4;5,6;7");
        assertThat(splitter.split(), containsInAnyOrder("1", "2", "3", "4", "5", "6", "7"));
    }

    @Test
    public void shouldSplitCustomDelimiterAndCommaAndColon() {
        StringSplitter splitter = new StringSplitter("//!\n1!2:5,3!3!3,4,5:6");
        assertThat(splitter.split(), containsInAnyOrder("1", "2", "5", "3", "3", "3", "4", "5", "6"));
    }
}
