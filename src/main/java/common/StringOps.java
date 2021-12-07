package common;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class StringOps {

    public static <T> List<T> asList(String text, String separator, Function<String, T> mapper) {
        return Arrays.stream(text.split(separator)).map(mapper).toList();
    }

    public static List<String> asList(String text, String separator) {
        return asList(text, separator, Function.identity());
    }

}
