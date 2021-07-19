package book.ch5.webserver;

import once.ch6_2.http.HttpSession;
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
    public void request_GET() throws FileNotFoundException {
        InputStream in = new FileInputStream(new File(testDir + "Http_GET.txt"));
        HttpRequest request = new HttpRequest(in);

        assertThat(request.getMethod(), is(HttpMethod.GET));
        assertThat(request.getPath(), is("/user/create"));
        assertThat(request.getHeader("Connection"), is("keep-alive"));
        assertThat(request.getParameter("userId"), is("javajigi"));
    }

    @Test
    public void request_POST() throws FileNotFoundException {
        InputStream in = new FileInputStream(new File(testDir + "Http_POST.txt"));
        HttpRequest request = new HttpRequest(in);

        assertThat(request.getMethod(), is(HttpMethod.POST));
        assertThat(request.getPath(), is("/user/create"));
        assertThat(request.getHeader("Connection"), is("keep-alive"));
        assertThat(request.getParameter("userId"), is("javajigi"));
    }

    @Test
    public void session_new() throws FileNotFoundException {
        InputStream in = new FileInputStream(new File(testDir + "Http_Not_Has_Cookie.txt"));
        HttpRequest request = new HttpRequest(in);
        HttpSession session = request.getSession();

        assertThat(request.getSession(), is(session));
    }

    @Test
    public void session_find_by_cookie() {
        InputStream in = new FileInputStream(new File(testDir + "Http_Has_Cookie.txt"));
        HttpRequest request = new HttpRequest(in);
        HttpSession session = request.getSession();

        assertThat(request.getSession(), is(session));
    }
}
