package once.ch6_2.http;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HttpSessionTest {
    private HttpSession session;

    @Before
    public void setUp() throws Exception {
        session = HttpSession.create();
    }

    @Test
    public void 세션아이디_반환() {
        assertThat(session.getId(), matchesPattern("^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$"));
    }

    @Test
    public void 세션에_속성_저장() {
        Object value = new Object();
        session.setAttribute("key", value);

        assertThat(session.getAttribute("key"), is(value));
        assertThat(session.getAttribute("null"), is(nullValue()));
        assertThat(session.getAttribute(null), is(nullValue()));
    }

    @Test
    public void 세션에_저장한_속성_제거() {
        session.setAttribute("key", "value");
        session.removeAttribute("key");

        assertThat(session.getAttribute("key"), is(nullValue()));
    }

    @Test
    public void 세션에_저장한_모든_속성_제거() {
        session.setAttribute("key1", "value1");
        session.setAttribute("key2", "value2");
        session.setAttribute("key3", "value3");

        session.invalidate();

        assertThat(session.getAttribute("key1"), is(nullValue()));
        assertThat(session.getAttribute("key2"), is(nullValue()));
        assertThat(session.getAttribute("key3"), is(nullValue()));
    }
}
