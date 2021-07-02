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
            String[] splitByColon = str.split(":");
            int start = Integer.parseInt(splitByColon[0]);
            int end = Integer.parseInt(splitByColon[1]);

            for(int value = start; value <= end; value++) {
                result.add(String.valueOf(value));
            }
        } else {
            result.add(str.trim());
        }
    }

    private boolean hasColon(String str) {
        return str.contains(":");
    }
}
