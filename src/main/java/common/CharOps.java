package common;

public class CharOps {

    public static int digitToInt(Character c) {
        if (c < '0' || c > '9') throw new IllegalArgumentException("Invalid digit '" + c + "'");
        return c - '0';
    }

}
