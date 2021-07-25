package once.ch8_1;

import once.ch8_1.controller.*;
import once.ch8_1.controller.qna.CreateQuestionController;
import once.ch8_1.controller.qna.QuestionController;
import once.ch8_1.controller.user.*;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {
    private static Map<String, Controller> mapping = new HashMap<>();

    static {
        mapping.put("/index", new IndexController());
        mapping.put("/qna/show", new QuestionController());
        mapping.put("/question", new CreateQuestionController());
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
