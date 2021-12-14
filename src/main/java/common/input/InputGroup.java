package common.input;

import io.vavr.collection.Set;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class InputGroup implements StructuredInput {

    private final io.vavr.collection.List<String> lines;

    public InputGroup(io.vavr.collection.List<String> lines) {
        this.lines = lines;
    }

    @Override
    public String asOneLine() {
        return lines.head();
    }

    @Override
    public Stream<String> asStreamOfLines() {
        return null;
    }

    @Override
    public List<String> asList() {
        return lines.toJavaList();
    }

    @Override
    public <T> List<T> asList(Function<String, T> transformation) {
        return lines.map(transformation).toJavaList();
    }

    @Override
    public Set<String> asSet() {
        return lines.toSet();
    }

    @Override
    public <T> Set<T> asSet(Function<String, T> transformation) {
        return lines.map(transformation).toSet();
    }

    public static InputGroup of(String... lines) {
        return new InputGroup(io.vavr.collection.List.ofAll(Arrays.asList(lines)));
    }

}
