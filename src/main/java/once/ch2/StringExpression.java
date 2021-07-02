package once.ch2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringExpression {
    private static final Pattern CUSTOM_DELIMITER_PATTERN = Pattern.compile("//(.)\n(.*)");
    private final String expression;
    private final Matcher matcher;

    public StringExpression(String expression) {
        this.expression = expression;
        this.matcher = CUSTOM_DELIMITER_PATTERN.matcher(expression);
    }

    public boolean hasCustomDelimiter() {
        return matcher.find();
    }
}
