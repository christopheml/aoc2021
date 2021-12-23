package year2015.day08;

import common.input.Input;
import common.runners.Solution;

import java.util.regex.Pattern;

public class Day08 extends Solution<Integer> {

    private static final Pattern singleCharacter = Pattern.compile("\\\\\\\\|\\\\\"|\\\\x[a-f0-9]{2}|[a-z0-9]");

    public Day08() {
        super(2015, 8);
    }

    private int inMemoryLength(String text) {
        var matcher = singleCharacter.matcher(text.substring(1, text.length() - 1));
        int length = 0;
        while (matcher.find()) length++;
        return length;
    }

    private int encodedLength(String text) {
        int extra = 2;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '\\' || text.charAt(i) == '"') extra++;
        }
        return text.length() + extra;
    }

    @Override
    public Integer partOne(Input input) {
        return input.asStream()
                .map(text -> text.length() - inMemoryLength(text))
                .sum()
                .intValue();
    }

    @Override
    public Integer partTwo(Input input) {
        return input.asStream()
                .map(text -> encodedLength(text) - text.length())
                .sum()
                .intValue();
    }

}
