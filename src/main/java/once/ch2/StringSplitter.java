package once.ch2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringSplitter {
    private static final Pattern CUSTOM_PATTERN = Pattern.compile("//(.)\n(.*)");
    private final String customDelimiter;
    private final String str;

    public StringSplitter(String str) {
        Matcher m = CUSTOM_PATTERN.matcher(str);

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

        return splitByCustomDelimiter(splitByComma(str)).stream()
                .map(this::splitString).flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private boolean isBlank(String str) {
        return str.trim().isEmpty();
    }

    private List<String> splitByCustomDelimiter(List<String> str) {
        if(isBlank(customDelimiter)) {
            return str;
        }

        return str.stream().map(s -> s.split(customDelimiter)).flatMap(Arrays::stream).collect(Collectors.toList());
    }

    private List<String> splitByComma(String str) {
        return Arrays.asList(str.split(","));
    }

    private List<String> splitString(String str) {
        return hasColon(str) ? continuousString(str) : singleString(str);
    }

    private boolean hasColon(String str) {
        return str.contains(":");
    }

    private List<String> continuousString(String str) {
        return IntStream.rangeClosed(low(str), high(str))
                .mapToObj(String::valueOf).collect(Collectors.toList());
    }

    private List<String> singleString(String str) {
        return Arrays.asList(str.trim());
    }

    private int low(String str) {
        return Integer.parseInt(splitByColon(str)[0]);
    }

    private int high(String str) {
        return Integer.parseInt(splitByColon(str)[1]);
    }

    private String[] splitByColon(String str) {
        return str.split(":");
    }
}
