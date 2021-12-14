package common.input;

import common.StringOps;
import io.vavr.collection.Set;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public interface StructuredInput {

    String DEFAULT_SEPARATOR = ",";

    String asOneLine();

    Stream<String> asStreamOfLines();

    List<String> asList();

    <T> List<T> asList(Function<String, T> transformation);

    Set<String> asSet();

    <T> Set<T> asSet(Function<String, T> transformation);

    default Integer asInteger() {
        return Integer.parseInt(asOneLine());
    }

    default Long asLong() {
        return Long.parseLong(asOneLine());
    }

    default <T> List<T> asSeparatedValues(String separator, Function<String, T> mapper) {
        return StringOps.asList(asOneLine(), separator, mapper);
    }

    default List<String> asSeparatedValues() {
        return asSeparatedValues(DEFAULT_SEPARATOR, Function.identity());
    }

    default List<Integer> asSeparatedIntegers() {
        return asSeparatedValues(DEFAULT_SEPARATOR, Integer::parseInt);
    }

    default List<Long> asSeparatedLongs() {
        return asSeparatedValues(DEFAULT_SEPARATOR, Long::parseLong);
    }

    default List<Character> asCharacters() {
        return StringOps.toChars(asOneLine());
    }

}
