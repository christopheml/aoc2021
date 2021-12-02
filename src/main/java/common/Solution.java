package common;

import java.util.function.Function;

public record Solution(int day, common.Solution.Part<?> partOne, common.Solution.Part<?> partTwo) {

    @FunctionalInterface
    public interface Part<T> extends Function<Input, T> {
    }

    public void run() {
        System.out.printf("Solution for day %02d%n", day);
        run(partOne, 1);
        run(partTwo, 2);
    }

    private void run(Part<?> part, int number) {
        var input = new Input(day);
        var result = part.apply(input);
        var representation = switch (result) {
            case String s -> s;
            case Long l -> Long.toString(l);
            case Integer i -> Integer.toString(i);
            default -> result.toString();
        };
        System.out.printf("\tPart %d: %s%n", number, representation);
    }

}
