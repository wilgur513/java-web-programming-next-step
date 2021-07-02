package once.ch2;

public class StringCalculator {
    private NumberConvertor convertor;
    private StringSplitter splitter;


    public StringCalculator(NumberConvertor convertor, StringSplitter splitter) {
        this.convertor = convertor;
        this.splitter = splitter;
    }

    public int sum() {
        return convertor.convert(splitter.split()).stream().reduce(0, (a, b) -> a + b);
    }
}
