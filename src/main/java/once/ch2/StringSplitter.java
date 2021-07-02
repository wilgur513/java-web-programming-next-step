package once.ch2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringSplitter {
    private final String str;

    public StringSplitter(String str) {
        this.str = str;
    }

    public List<String> split() {
        if(isEmpty(str)) {
            return new ArrayList<>();
        }

        return Arrays.stream(splitByComma(str))
                .map(this::splitString).flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private boolean isEmpty(String str) {
        return str.trim().isEmpty();
    }

    private String[] splitByComma(String str) {
        return str.split(",");
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
