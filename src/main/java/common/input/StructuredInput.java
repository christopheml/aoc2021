package common.input;

import common.StringOps;
import io.vavr.collection.List;
import io.vavr.collection.Set;
import io.vavr.collection.Stream;

import java.util.function.Function;

public interface StructuredInput {

    String DEFAULT_SEPARATOR = ",";

    String asOneLine();

    Stream<String> asStream();

    List<String> asList();

    Set<String> asSet();

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
