package once.ch6_2.http;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HttpSession {
    private UUID uuid;
    private Map<String, Object> attributes;

    private HttpSession(UUID uuid) {
        this.uuid = uuid;
        this.attributes = new HashMap<>();
    }

    public String getId() {
        return uuid.toString();
    }

    public void setAttribute(String key, Object value) {
        attributes.put(key, value);
    }

    public Object getAttribute(String key) {
        return attributes.get(key);
    }

    public void removeAttribute(String key) {
        attributes.remove(key);
    }

    public void invalidate() {
        attributes.clear();
    }

    public static HttpSession create() {
        return new HttpSession(UUID.randomUUID());
    }
}
