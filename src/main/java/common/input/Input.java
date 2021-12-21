package common.input;

import io.vavr.collection.List;
import io.vavr.collection.Set;
import io.vavr.collection.Stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;

public class Input implements StructuredInput {

    private final List<String> lines;

    public Input(int year, int day) {
        lines = readLines(String.format("inputs/%d/%02d.txt", year, day));
    }

    private static final class InvalidInputFile extends RuntimeException {
        public InvalidInputFile(String filename) {
            super("Can't read " + filename);
        }

        public InvalidInputFile(String filename, Throwable t) {
            super("Can't read " + filename, t);
        }
    }

    private List<String> readLines(String filename) {
        try (var reader = new BufferedReader(new InputStreamReader(openResource(filename), StandardCharsets.UTF_8))) {
            return Stream.ofAll(reader.lines()).toList();
        } catch (IOException e) {
            throw new InvalidInputFile(filename, e);
        }
    }

    private InputStream openResource(String filename) {
        return Optional
                .ofNullable(this.getClass().getClassLoader().getResourceAsStream(filename))
                .orElseThrow(() -> new InvalidInputFile(filename));
    }

    @Override
    public String asOneLine() {
        return lines.head();
    }

    @Override
    public Stream<String> asStreamOfLines() {
        return lines.toStream();
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

    public List<InputGroup> asGroups() {
        var groups = new ArrayList<InputGroup>();
        var currentGroup = new ArrayList<String>();
        for (var line : lines) {
            if ("".equals(line)) {
                groups.add(new InputGroup(List.ofAll(currentGroup)));
                currentGroup = new ArrayList<>();
            } else {
                currentGroup.add(line);
            }
        }
        if (!currentGroup.isEmpty()) groups.add(new InputGroup(List.ofAll(currentGroup)));
        return List.ofAll(groups);
    }

}
