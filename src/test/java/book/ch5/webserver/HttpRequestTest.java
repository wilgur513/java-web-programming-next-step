package book.ch5.webserver;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HttpRequestTest {
    private String testDir = "./src/test/resources/";

    @Test
    public void request_GET() throws FileNotFoundException {
        InputStream in = new FileInputStream(new File(testDir + "Http_GET.txt"));
        HttpRequest request = new HttpRequest(in);

        assertThat(request.getMethod(), is("GET"));
        assertThat(request.getPath(), is("/user/create"));
        assertThat(request.getHeader("Connection"), is("keep-alive"));
        assertThat(request.getParameter("userId"), is("javajigi"));
    }

    @Test
    public void request_POST() throws FileNotFoundException {
        InputStream in = new FileInputStream(new File(testDir + "Http_POST.txt"));
        HttpRequest request = new HttpRequest(in);

        assertThat(request.getMethod(), is("POST"));
        assertThat(request.getPath(), is("/user/create"));
        assertThat(request.getHeader("Connection"), is("keep-alive"));
        assertThat(request.getParameter("userId"), is("javajigi"));
    }
}
