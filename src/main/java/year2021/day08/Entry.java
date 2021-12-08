package year2021.day08;

import io.vavr.collection.List;

import java.util.Arrays;


public record Entry(List<String> patterns, List<String> digits) {

    public static Entry fromString(String line) {
        var parts = line.split(" \\| ");
        return new Entry(
                List.ofAll(Arrays.asList(parts[0].split(" "))),
                List.ofAll(Arrays.asList(parts[1].split(" ")))
        );
    }

}
