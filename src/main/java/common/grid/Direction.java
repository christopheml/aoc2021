package common.grid;

import java.util.Arrays;

public enum Direction {
    UP("U"), RIGHT("R"), DOWN("D"), LEFT("L");

    private final String representation;

    Direction(String representation) {
        this.representation = representation;
    }

    public static Direction fromString(String text) {
        return Arrays.stream(values()).filter(d -> d.representation.equals(text)).findFirst().orElseThrow();
    }
}
