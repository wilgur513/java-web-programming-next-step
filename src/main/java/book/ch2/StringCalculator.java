package book.ch2;

public class StringCalculator {
    public int add(String text) {
        if(text == null || text.isEmpty()) {
            return 0;
        }

        String[] values = text.split(",");
        return sum(toInts(values));
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
