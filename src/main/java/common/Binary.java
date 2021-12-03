package common;

public class Binary {

    public static int parse(String repr) {
        return Integer.parseInt(repr, 2);
    }

    public static String asString(int repr) {
        return Integer.toBinaryString(repr);
    }

    public static String asString(int repr, int fixedSize) {
        var binaryString = Integer.toBinaryString(repr);
        if (binaryString.length() >= fixedSize) return binaryString.substring(binaryString.length() - fixedSize);
        return "0".repeat(fixedSize - binaryString.length()) + binaryString;
    }

    public static boolean isBitSet(int number, int bitPosition) {
        return (number & (1L << bitPosition)) > 0;
    }

    public static int flipAllBits(int number) {
        return number ^ ((1 << 31) - 1);
    }

    public static int truncate(int number, int bits) {
        return number & ((1 << (bits)) - 1);
    }

}
