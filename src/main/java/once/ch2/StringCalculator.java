package once.ch2;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {
    public int calculate(String str) {
        if(isEmpty(str)) {
            return 0;
        }

        return sum(split(str));
    }

    private boolean isEmpty(String str) {
        return str.trim().isEmpty();
    }

    public int sum(List<String> split) {
        return split.stream().mapToInt(Integer::parseInt).sum();
    }

    private List<String> split(String str) {
        List<String> result = new ArrayList<>();

        for(String split : splitByComma(str)) {
            addSplitString(split, result);
        }

        return result;
    }

    private String[] splitByComma(String str) {
        return str.split(",");
    }

    private void addSplitString(String str, List<String> result) {
        if(hasColon(str)) {
            addContinuousString(str, result);
        } else {
            result.add(str.trim());
        }
    }

    private boolean hasColon(String str) {
        return str.contains(":");
    }

    private void addContinuousString(String str, List<String> result) {
        for(int value = low(str); value <= high(str); value++) {
            result.add(String.valueOf(value));
        }
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
