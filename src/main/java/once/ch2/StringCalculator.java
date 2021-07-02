package once.ch2;

import java.util.Arrays;

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

    public int sum(String[] split) {
        return Arrays.stream(split).mapToInt(Integer::parseInt).sum();
    }

    private String[] split(String str) {
        return str.split(",");
    }
}
