package once.ch2;

import java.util.ArrayList;
import java.util.List;

public class NumberConvertor {

    public List<Integer> convert(List<String> stringList) {
        List<Integer> result = new ArrayList<>();

        for(String str : stringList) {
            result.add(Integer.parseInt(str));
        }

        return result;
    }
}
