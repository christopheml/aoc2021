package year2015.day06;

import common.grid.Point;
import common.grid2.MutableGrid;
import common.input.Input;
import common.runners.Solution;
import io.vavr.Tuple;
import io.vavr.Tuple3;
import io.vavr.collection.Stream;

import java.util.function.BiFunction;
import java.util.regex.Pattern;

public class Day06 extends Solution<Integer> {

    private static final Pattern lineFormat = Pattern.compile("^(turn on|turn off|toggle) (\\d+,\\d+) through (\\d+,\\d+)$");

    public Day06() {
        super(2015, 6);
    }

    private int updateOnOff(String instruction, int value) {
        return switch (instruction) {
            case "turn on" -> 1;
            case "turn off" -> 0;
            case "toggle" -> value == 1 ? 0 : 1;
            default -> throw new IllegalStateException("Unexpected value: " + instruction);
        };
    }

    private int updateRange(String instruction, int value) {
        return switch (instruction) {
            case "turn on" -> value + 1;
            case "turn off" -> Math.max(0, value - 1);
            case "toggle" -> value + 2;
            default -> throw new IllegalStateException("Unexpected value: " + instruction);
        };
    }

    private Tuple3<String, Point, Point> parse(String line) {
        var matcher = lineFormat.matcher(line);
        if (!matcher.matches()) throw new IllegalArgumentException("Invalid line: " + line);
        return Tuple.of(
                matcher.group(1),
                Point.fromString(matcher.group(2)),
                Point.fromString(matcher.group(3))
        );
    }

    private void turnLightsOn(MutableGrid<Integer> grid, Input input, BiFunction<String, Integer, Integer> update) {
        input.asStream().map(this::parse).forEach(step -> {
            Stream.rangeClosed(step._2.y(), step._3.y()).forEach(y -> {
                Stream.rangeClosed(step._2.x(), step._3.x()).forEach(x -> {
                    grid.update(new Point(x, y), i -> update.apply(step._1, i));
                });
            });
        });

    }

    @Override
    public Integer partOne(Input input) {
        var grid = MutableGrid.init(1000, 1000, 0, Integer.class);
        turnLightsOn(grid, input, this::updateOnOff);
        return grid.count(value -> value > 0);
    }

    @Override
    public Integer partTwo(Input input) {
        var grid = MutableGrid.init(1000, 1000, 0, Integer.class);
        turnLightsOn(grid, input, this::updateRange);
        return grid.elements().sum().intValue();
    }

}
