package day02;

public record SubmarinePosition(int aim, int x, int depth) {

    public static final SubmarinePosition ORIGIN = new SubmarinePosition(0, 0, 0);

    public SubmarinePosition combine(SubmarinePosition next) {
        if (next.aim != 0) {
            return new SubmarinePosition(aim + next.aim, x, depth);
        } else {
            return new SubmarinePosition(aim, x + next.x, depth + (aim * next.x));
        }
    }

}
