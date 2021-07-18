package once.ch6_2.http;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class SessionStoreTest {
    @Test
    public void 세션을_저장() {
        HttpSession session = HttpSession.create();

        SessionStore.save(session);

        assertThat(SessionStore.get(session.getId()), is(session));
    }

    @Test
    public void 세션을_제거() {
        HttpSession session = HttpSession.create();

        SessionStore.save(session);
        SessionStore.remove(session.getId());

        assertThat(SessionStore.get(session.getId()), is(nullValue()));
    }
}
