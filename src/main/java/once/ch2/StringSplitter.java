package once.ch2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringSplitter {
    private final StringExpression expression;

    public StringSplitter(StringExpression expression) {
        this.expression = expression;
    }

    public List<String> split() {
        if(expression.isBlank()) {
            return new ArrayList<>();
        }

        return splitByCustomDelimiter(splitByCommaAndColon(expression.getExpression())).stream()
                .collect(Collectors.toList());
    }

    private List<String> splitByCommaAndColon(String str) {
        return Arrays.asList(str.split(",|:"));
    }

    private List<String> splitByCustomDelimiter(List<String> stringList) {
        if(expression.hasCustomDelimiter()) {
            return stringList.stream()
                    .map(s -> s.split(expression.getCustomDelimiter()))
                    .flatMap(Arrays::stream)
                    .collect(Collectors.toList());
        }

        return stringList;
    }
}
