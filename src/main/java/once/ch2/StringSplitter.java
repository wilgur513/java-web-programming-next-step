package once.ch2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class StringSplitter {
    private final StringExpression expression;

    public StringSplitter(StringExpression expression) {
        this.expression = expression;
    }

    public List<String> split() {
        if(expression.isBlank()) {
            return new ArrayList<>();
        }

        return splitByCommaAndColon(expression.splitByCustomDelimiter()).stream().collect(toList());
    }

    private List<String> splitByCommaAndColon(List<String> stringList) {
        return stringList.stream().map(s -> s.split(",|:")).flatMap(Arrays::stream).collect(toList());
    }
}
