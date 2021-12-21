package year2021.day06;

import io.vavr.collection.List;

import java.util.Arrays;

public class LanternFishPopulation {

    private long[] counts;

    public LanternFishPopulation(List<Integer> timers) {
        counts = new long[9];
        timers.forEach(timer -> counts[timer]++);
    }

    public void tick() {
        var next = new long[9];
        for (int i = 1; i < 9; ++i) {
            next[i - 1] = counts[i];
        }
        next[6] += counts[0]; // reset
        next[8] = counts[0]; // spawn
        counts = next;
    }

    public long count() {
        return Arrays.stream(counts).sum();
    }

}
