package once.ch2;

import java.util.ArrayList;
import java.util.List;

public class NumberConvertor {
    public List<Integer> convert(List<String> stringList) {
        List<Integer> result = new ArrayList<>();

        for(String str : stringList) {
            result.add(convert(str));
        }

        return result;
    }

    private int convert(String str) {
        if(isNegative(toInt(str))) {
            throw new RuntimeException();
        }

        return toInt(str);
    }

    private int toInt(String str) {
        return Integer.parseInt(str);
    }

    private boolean isNegative(int value) {
        return value < 0;
    }
}
