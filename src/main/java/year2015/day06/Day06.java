package year2015.day06;

import common.grid.Point;
import common.grid2.MutableGrid;
import common.input.Input;
import common.runners.Solution;

import java.util.regex.Pattern;

public class Day06 extends Solution<Integer> {

    private static final Pattern lineFormat = Pattern.compile("^(turn on|turn off|toggle) (\\d+,\\d+) through (\\d+,\\d+)$");

    public Day06() {
        super(2015, 6);
    }

    private record Instruction(String command, Point start, Point end) {
    }

    @FunctionalInterface
    private interface LightUpdate {
        int update(int value);
    }

    private Instruction parse(String line) {
        var matcher = lineFormat.matcher(line);
        if (!matcher.matches()) throw new IllegalArgumentException("Invalid line: " + line);
        return new Instruction(
                matcher.group(1),
                Point.fromString(matcher.group(2)),
                Point.fromString(matcher.group(3))
        );
    }

    private void turnLightsOn(MutableGrid<Integer> grid, Input input, LightUpdate on, LightUpdate off, LightUpdate toggle) {
        input.asStream()
                .map(this::parse)
                .forEach(instruction -> grid.updateRegion(instruction.start,
                        instruction.end,
                        value -> switch (instruction.command) {
                            case "turn on" -> on.update(value);
                            case "turn off" -> off.update(value);
                            case "toggle" -> toggle.update(value);
                            default -> throw new IllegalStateException("Unexpected value: " + instruction);
                        }));
    }

    @Override
    public Integer partOne(Input input) {
        var grid = MutableGrid.init(1000, 1000, 0, Integer.class);
        turnLightsOn(grid, input, i -> 1, i -> 0, i -> 1 - i);
        return grid.count(value -> value > 0);
    }

    @Override
    public Integer partTwo(Input input) {
        var grid = MutableGrid.init(1000, 1000, 0, Integer.class);
        turnLightsOn(grid, input, i -> i + 1, i -> Math.max(0, i - 1), i -> i + 2);
        return grid.elements().sum().intValue();
    }

}
