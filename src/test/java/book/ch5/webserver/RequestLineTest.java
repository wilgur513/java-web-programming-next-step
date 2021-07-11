package book.ch5.webserver;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RequestLineTest {
    @Test
    public void create_method() {
        RequestLine line = new RequestLine("GET /index.html HTTP/1.1");

        assertThat(line.getMethod(), is(HttpMethod.GET));
        assertThat(line.getPath(), is("/index.html"));

        line = new RequestLine("POST /index.html HTTP/1.1");
        assertThat(line.getMethod(), is(HttpMethod.POST));
    }

    @Test
    public void create_path_and_params() {
        RequestLine line = new RequestLine("GET /user/create?userId=javajigi&password=pass HTTP/1.1");

        assertThat(line.getMethod(), is(HttpMethod.GET));
        assertThat(line.getPath(), is("/user/create"));
        assertThat(line.getParams().size(), is(2));
        assertThat(line.getParams().get("userId"), is("javajigi"));
        assertThat(line.getParams().get("password"), is("pass"));
    }
}
