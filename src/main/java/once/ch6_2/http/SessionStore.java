package once.ch6_2.http;

import java.util.HashMap;
import java.util.Map;

public class SessionStore {
    private static Map<String, HttpSession> sessions = new HashMap<>();

    public static void save(HttpSession session) {
        sessions.put(session.getId(), session);
    }

    public static HttpSession get(String id) {
        return sessions.get(id);
    }

    public static void remove(String id) {
        sessions.remove(id);
    }
}
