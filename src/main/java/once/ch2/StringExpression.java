package once.ch2;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringExpression {
    private static final Pattern CUSTOM_DELIMITER_HEADER = Pattern.compile("//(.)\n(.*)");
    private final String expression;
    private final Matcher matcher;

    public StringExpression(String expression) {
        this.expression = expression;
        this.matcher = CUSTOM_DELIMITER_HEADER.matcher(expression);
    }

    public boolean hasCustomDelimiter() {
        return matcher.matches();
    }

    public String getExpression() {
        return hasCustomDelimiter() ? removeCustomDelimiterHeader() : expression;
    }

    private String removeCustomDelimiterHeader() {
        return matcher.group(2);
    }

    public String getCustomDelimiter() {
        if(hasCustomDelimiter())
            return matcher.group(1);

        throw new IllegalStateException();
    }

    public boolean isBlank() {
        return expression.trim().isEmpty();
    }

    public List<String> splitByCustomDelimiter() {
        if(hasCustomDelimiter())
            return Arrays.asList(getExpression().split(getCustomDelimiter()));

        return Arrays.asList(getExpression());
    }
}
