package once.ch6_2.http;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HttpRequestTest {
    private String testDir = "./src/test/resources/";

    @Test
    public void session_new() throws FileNotFoundException {
        InputStream in = new FileInputStream(new File(testDir + "Http_Not_Has_Cookie.txt"));
        HttpRequest request = new HttpRequest(in);
        HttpSession session = request.getSession();

        assertThat(request.getSession(), is(session));
    }

    @Test
    public void session_find_by_cookie() throws FileNotFoundException {
        SessionStore.save(new HttpSession(UUID.fromString("550e8400-e29b-41d4-a716-446655440000")));

        InputStream in = new FileInputStream(new File(testDir + "Http_Has_Cookie.txt"));
        HttpRequest request = new HttpRequest(in);
        HttpSession session = request.getSession();

        assertThat(session.getId(), is("550e8400-e29b-41d4-a716-446655440000"));
    }
}
