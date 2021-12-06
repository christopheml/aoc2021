package year2021.day02;

public record SubmarinePosition(int aim, int x, int depth) {

    public static final SubmarinePosition ORIGIN = new SubmarinePosition(0, 0, 0);

}
