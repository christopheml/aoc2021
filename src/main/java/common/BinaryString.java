package common;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BinaryString implements Iterable<Character> {

    private final String repr;

    public BinaryString(String repr) {
        this.repr = repr;
    }

    public BinaryString(int[] digits, Function<Integer, Boolean> toBit) {
        this.repr = Arrays.stream(digits).mapToObj(d -> toBit.apply(d) ? "1" : "0").collect(Collectors.joining());
    }

    public BinaryString flip() {
        var sb = new StringBuilder();
        for (var c : repr.toCharArray()) {
            sb.append(c == '1' ? '0' : '1');
        }
        return new BinaryString(sb.toString());
    }

    public Character getDigit(int position) {
        return repr.charAt(position);
    }

    public int toInteger() {
        return Binary.parse(repr);
    }

    public int[] toArray() {
        return repr.chars().map(i -> i == '1' ? 1 : 0).toArray();
    }

    @Override
    public Iterator<Character> iterator() {
        return repr.chars().mapToObj(i -> (char) i).iterator();
    }

    @Override
    public String toString() {
        return repr;
    }

}
