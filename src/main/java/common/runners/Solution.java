package common.runners;

import common.Timer;

import java.util.function.Function;

public abstract class Solution<T> {

    private final int year;
    private final int day;

    public Solution(int year, int day) {
        this.year = year;
        this.day = day;
    }

    public abstract T partOne(Input input);
    public abstract T partTwo(Input input);

    public void run() {
        System.out.printf("Solution for %d day %02d%n", year, day);
        run(this::partOne, 1);
        run(this::partTwo, 2);
    }

    private void run(Function<Input, T> part, int number) {
        var input = new Input(day);
        var timer = new Timer();
        timer.start();
        var result = part.apply(input);
        var time = timer.stop();
        var representation = switch (result) {
            case String s -> s;
            case Long l -> Long.toString(l);
            case Integer i -> Integer.toString(i);
            default -> result.toString();
        };
        System.out.printf("\tPart %d (time: %dms): %s%n", number, time, representation);
    }

}
