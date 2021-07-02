package once.ch2;

import java.util.List;

public class StringCalculator {
    public int calculate(List<String> stringList) {
        return sum(stringList);
    }

    public int sum(List<String> split) {
        return split.stream().mapToInt(Integer::parseInt).sum();
    }
}
