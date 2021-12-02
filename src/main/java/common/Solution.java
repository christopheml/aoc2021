package common;

public record Solution<T, U>(int day, Part<T> partOne, Part<U> partTwo) {

    public void run() {
        System.out.printf("Solution for day %02d%n", day);
        run(partOne, 1);
        run(partTwo, 2);
    }

    private void run(Part<?> part, int number) {
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
