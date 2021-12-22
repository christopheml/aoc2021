package year2015.day05;

import common.input.Input;
import common.runners.Solution;

import java.util.regex.Pattern;

public class Day05 extends Solution<Integer> {

    private static final Pattern vowels = Pattern.compile("[aeiou]");
    private static final Pattern forbidden = Pattern.compile("ab|cd|pq|xy");
    private static final Pattern surrounding = Pattern.compile("([a-z])[a-z]\\1");
    private static final Pattern repeatingLetter = Pattern.compile("([a-z])\\1");
    private static final Pattern repeatingPair = Pattern.compile("([a-z]{2})[a-z]*\\1");

    public Day05() {
        super(2015, 5);
    }

    private boolean hasThreeVowels(String string) {
        var matcher = vowels.matcher(string);
        int count = 0;
        while (matcher.find() && count < 3) count++;
        return count == 3;
    }

    private boolean hasRepeatedLetter(String string) {
        return repeatingLetter.matcher(string).find();
    }

    private boolean hasForbiddenStrings(String string) {
        return forbidden.matcher(string).find();
    }

    private boolean hasSurroundingPair(String string) {
        return surrounding.matcher(string).find();
    }

    private boolean hasRepeatingPair(String string) {
        return repeatingPair.matcher(string).find();
    }

    @Override
    public Integer partOne(Input input) {
        return input.asStream()
                .reject(this::hasForbiddenStrings)
                .filter(this::hasThreeVowels)
                .count(this::hasRepeatedLetter);
    }

    @Override
    public Integer partTwo(Input input) {
        return input.asStream()
                .filter(this::hasSurroundingPair)
                .count(this::hasRepeatingPair);
    }

}
