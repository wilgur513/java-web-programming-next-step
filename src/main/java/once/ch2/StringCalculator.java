package once.ch2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringCalculator {
    public int calculate(List<String> stringList) {
        return sum(stringList);
    }

    public int sum(List<String> split) {
        return split.stream().mapToInt(Integer::parseInt).sum();
    }
}
