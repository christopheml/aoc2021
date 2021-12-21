package common;

import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Stream;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;

public class StringOps {

    public static <T> Stream<T> asStream(String text, String separator, Function<String, T> mapper) {
        return Stream.ofAll(Arrays.stream(text.split(separator))).map(mapper);
    }

    public static <T> List<T> asList(String text, String separator, Function<String, T> mapper) {
        return asStream(text, separator, mapper).toList();
    }

    public static List<String> asList(String text, String separator) {
        return asList(text, separator, Function.identity());
    }

    public static List<Character> toChars(String text) {
        return List.ofAll(text.chars().mapToObj(c -> (char) c));
    }

    private static boolean forAllChars(String text, Predicate<Character> predicate) {
        for (int i = 0; i < text.length(); i++) {
            if (!predicate.test(text.charAt(i))) return false;
        }
        return true;
    }

    public static boolean isUpperCase(String text) {
        return forAllChars(text, Character::isUpperCase);
    }

    public static boolean isLowerCase(String text) {
        return forAllChars(text, Character::isLowerCase);
    }

    public static Tuple2<String, String> toTuple(String text) {
        var parts = text.split(" -> ");
        return new Tuple2<>(parts[0], parts[1]);
    }

    public static <T> Tuple2<T, T> toTuple(String text, Function<String, T> mapper) {
        var parts = text.split(" -> ");
        return new Tuple2<>(mapper.apply(parts[0]), mapper.apply(parts[1]));
    }

}
