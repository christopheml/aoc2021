package year2021.day08;

public class Digit {

    public static boolean isTrivial(String digit) {
        var size = digit.length();
        return size == 2 || size == 4 || size == 3 || size == 7;
    }

}
