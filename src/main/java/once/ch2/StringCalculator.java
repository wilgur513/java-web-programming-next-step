package once.ch2;

import java.util.Arrays;

public class StringCalculator {
    public int calculate(String str) {
        if(str.isEmpty()) {
            return 0;
        }

        return Arrays.stream(str.split(",")).mapToInt(s -> Integer.parseInt(s)).sum();
    }
}
