package common.input;

import io.vavr.collection.List;
import io.vavr.collection.Set;
import io.vavr.collection.Stream;

import java.util.Arrays;

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
    public Stream<String> asStream() {
        return lines.toStream();
    }

    @Override
    public List<String> asList() {
        return lines;
    }

    @Override
    public Set<String> asSet() {
        return lines.toSet();
    }

    public static InputGroup of(String... lines) {
        return new InputGroup(io.vavr.collection.List.ofAll(Arrays.asList(lines)));
    }

}
