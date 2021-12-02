package common;

public record Point(int x, int y) {

    public static final Point ORIGIN = new Point(0, 0);

}
