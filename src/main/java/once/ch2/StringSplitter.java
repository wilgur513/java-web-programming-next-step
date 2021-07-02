package once.ch2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringSplitter {
    private static final Pattern CUSTOM_DELIMITER_PATTERN = Pattern.compile("//(.)\n(.*)");
    private final String customDelimiter;
    private final String str;

    public StringSplitter(String str) {
        Matcher m = CUSTOM_DELIMITER_PATTERN.matcher(str);

        if(m.find()) {
            customDelimiter = m.group(1);
            this.str = m.group(2);
        } else {
            customDelimiter = "";
            this.str = str;
        }
    }

    public List<String> split() {
        if(isBlank(str)) {
            return new ArrayList<>();
        }

        return splitByCustomDelimiter(splitByCommaAndColon(str)).stream()
                .collect(Collectors.toList());
    }

    private boolean isBlank(String str) {
        return str.trim().isEmpty();
    }

    private List<String> splitByCommaAndColon(String str) {
        return Arrays.asList(str.split(",|:"));
    }

    private List<String> splitByCustomDelimiter(List<String> str) {
        if(isBlank(customDelimiter)) {
            return str;
        }

        return str.stream()
            .map(s -> s.split(customDelimiter))
            .flatMap(Arrays::stream)
            .collect(Collectors.toList());
    }
}
