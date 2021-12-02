package common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class Input {

    private final int day;

    public Input(int day) {
        this.day = day;
    }

    private InputStream openResource() {
        var filename = String.format("inputs/%02d.txt", day);
        return this.getClass().getClassLoader().getResourceAsStream(filename);
    }

    private <T> T bufferedLineOperation(Function<BufferedReader, T> operation) {
        var reader = new BufferedReader(new InputStreamReader(openResource(), StandardCharsets.UTF_8));
        return operation.apply(reader);
    }

    public String asOneLine() {
        return bufferedLineOperation(ThrowingFunction.unchecked((BufferedReader::readLine)));
    }

    public Stream<String> asStreamOfLines() {
        return bufferedLineOperation(ThrowingFunction.unchecked(BufferedReader::lines));
    }

    public <T> List<T> asList(Function<String, T> transformation) {
        return asStreamOfLines().map(transformation).toList();
    }

}
