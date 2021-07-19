package book.ch5.webserver;

import book.ch5.util.IOUtils;
import book.ch5.util.HttpRequestUtils;
import once.ch6_2.http.HttpSession;
import once.ch6_2.http.SessionStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpRequest.class);
    private RequestLine requestLine;
    private Map<String, String> params = new HashMap<>();
    private Map<String, String> headers = new HashMap<>();
    private Map<String, String> cookies = new HashMap<>();
    private HttpSession session = null;

    public HttpRequest(InputStream in) {
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = reader.readLine();

            if(line == null) {
                return;
            }

            requestLine = new RequestLine(line);

            line = reader.readLine();
            while(!line.equals("")) {
                LOGGER.debug("header: {}", line);
                String[] tokens = line.split(":");
                headers.put(tokens[0].trim(), tokens[1].trim());

                if(tokens[0].trim().equals("Cookie")) {
                    cookies = HttpRequestUtils.parseCookies(tokens[1].trim());
                }

                line = reader.readLine();
            }

            if(getMethod().isPost()) {
                String body = IOUtils.readData(reader, Integer.parseInt(headers.get("Content-Length")));
                params = HttpRequestUtils.parseQueryString(body);
            } else {
                params = requestLine.getParams();
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public HttpMethod getMethod() {
        return requestLine.getMethod();
    }

    public String getPath() {
        return requestLine.getPath();
    }

    public String getHeader(String key) {
        return headers.get(key);
    }

    public String getParameter(String key) {
        return params.get(key);
    }

    public HttpSession getSession() {
        if(session != null) {
            return session;
        }

        if(hasSessionId()) {
            return SessionStore.get(cookies.get("JSESSIONID"));
        }

        session = HttpSession.create();
        SessionStore.save(session);
        return session;
    }

    private boolean hasSessionId() {
        return cookies.get("JSESSIONID") != null;
    }
}
