package book.ch2;

public class StringCalculator {
    public int add(String text) {
        if(isBlank(text)) {
            return 0;
        }

        return sum(toInts(split(text)));
    }

    private boolean isBlank(String text) {
        return text == null || text.isEmpty();
    }

    private String[] split(String text) {
        return text.split(",|:");
    }

    private int[] toInts(String[] values) {
        int[] numbers = new int[values.length];
        for(int i = 0; i < values.length; i++) {
            numbers[i] = Integer.parseInt(values[i]);
        }
        return numbers;
    }

    private int sum(int[] values) {
        int sum = 0;
        for(int value : values) {
            sum += value;
        }
        return sum;
    }
}
