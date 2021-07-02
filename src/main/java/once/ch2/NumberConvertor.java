package once.ch2;

import java.util.ArrayList;
import java.util.List;

public class NumberConvertor {
    public List<Integer> convert(List<String> stringList) {
        List<Integer> result = new ArrayList<>();

        for(String str : stringList) {
            if(isNegativeString(str)) {
                throw new RuntimeException();
            }

            result.add(toInt(str));
        }

        return result;
    }

    private int toInt(String str) {
        return Integer.parseInt(str);
    }

    private boolean isNegativeString(String str) {
        return toInt(str) < 0;
    }
}
