package common;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import io.vavr.Tuple4;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternParsers {

    public static <T> T parse1(Pattern pattern, String text, Function<String, T> mapper) {
        var matcher = match(pattern, text);
        return mapper.apply(matcher.group(1));
    }

    public static <T, U> Tuple2<T, U> parse2(Pattern pattern, String text,
                                             Function<String, T> mapper1,
                                             Function<String, U> mapper2) {
        var matcher = match(pattern, text);
        return Tuple.of(
                mapper1.apply(matcher.group(1)),
                mapper2.apply(matcher.group(2))
        );
    }

    public static <T, U, V> Tuple3<T, U, V> parse3(Pattern pattern, String text,
                                                   Function<String, T> mapper1,
                                                   Function<String, U> mapper2,
                                                   Function<String, V> mapper3) {
        var matcher = match(pattern, text);
        return Tuple.of(
                mapper1.apply(matcher.group(1)),
                mapper2.apply(matcher.group(2)),
                mapper3.apply(matcher.group(3))
        );
    }

    public static <T, U, V, W> Tuple4<T, U, V, W> parse4(Pattern pattern, String text,
                                                         Function<String, T> mapper1,
                                                         Function<String, U> mapper2,
                                                         Function<String, V> mapper3,
                                                         Function<String, W> mapper4) {
        var matcher = match(pattern, text);
        return Tuple.of(
                mapper1.apply(matcher.group(1)),
                mapper2.apply(matcher.group(2)),
                mapper3.apply(matcher.group(3)),
                mapper4.apply(matcher.group(4))
        );
    }

    private static Matcher match(Pattern pattern, String text) {
        var matcher = pattern.matcher(text);
        if (!matcher.matches()) throw new IllegalStateException("Pattern " + pattern + " does not match " + text);
        return matcher;
    }

}
