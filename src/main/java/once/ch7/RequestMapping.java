package once.ch7;

import ch6.controller.*;
import once.ch7.controller.*;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {
    private static Map<String, Controller> mapping = new HashMap<>();

    static {
        mapping.put("/user/create", new CreateUserController());
        mapping.put("/user/list", new ListUserController());
        mapping.put("/user/login", new LoginUserController());
        mapping.put("/user/logout", new LogoutUserController());
        mapping.put("/user/update", new UserUpdateController());
        mapping.put("/user/update-form", new UserUpdateFormController());
    }

    public static Controller find(String url) {
        return mapping.get(url);
    }
}
