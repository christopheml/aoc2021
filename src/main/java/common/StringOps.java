package common;

import jdk.jshell.spi.ExecutionControl;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StringOps {

    public static <T> Stream<T> asStream(String text, String separator, Function<String, T> mapper) {
        return Arrays.stream(text.split(separator)).map(mapper);
    }

    public static <T> List<T> asList(String text, String separator, Function<String, T> mapper) {
        return asStream(text, separator, mapper).toList();
    }

    public static List<String> asList(String text, String separator) {
        return asList(text, separator, Function.identity());
    }

    public static List<Character> toChars(String text) {
        return text.chars().mapToObj(c -> (char) c).toList();
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

}
