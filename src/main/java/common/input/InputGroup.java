package common.input;

import io.vavr.collection.List;
import io.vavr.collection.Set;
import io.vavr.collection.Stream;

import java.util.Arrays;
import java.util.function.Function;

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
        return lines;
    }

    @Override
    public <T> List<T> asList(Function<String, T> transformation) {
        return lines.map(transformation);
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
