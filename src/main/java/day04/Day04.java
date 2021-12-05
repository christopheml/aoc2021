package day04;

import common.runners.Input;
import common.runners.Solution;
import common.StringOps;

import java.util.ArrayList;

public class Day04 extends Solution<Integer> {

    public Day04() {
        super(2021, 4);
    }

    public Integer partOne(Input input) {
        var groups = input.asGroups();

        var draws = StringOps.asList(groups.get(0).get(0), ",", Integer::valueOf);

        var cards = groups.subList(1, groups.size()).stream().map(
                group -> group.stream().map(line -> StringOps.asList(line.trim(), "\\s+", Integer::valueOf)).toList()
        ).map(BingoCard::new).toList();

        for (var draw: draws) {
            cards.forEach(c -> c.draw(draw));
            var winner = cards.stream().filter(BingoCard::isComplete).findFirst();
            if (winner.isPresent()) {
                return draw * winner.get().sumOfUnmarkedNumbers();
            }
        }
        return 0;
    }

    public Integer partTwo(Input input) {
        var groups = input.asGroups();

        var draws = StringOps.asList(groups.get(0).get(0), ",", Integer::valueOf);

        var cards = new ArrayList<>(groups.subList(1, groups.size()).stream().map(
                group -> group.stream().map(line -> StringOps.asList(line.trim(), "\\s+", Integer::valueOf)).toList()
        ).map(BingoCard::new).toList());

        BingoCard lastWinner = null;
        int lastWinningDraw = 0;
        for (var draw: draws) {
            cards.forEach(c -> c.draw(draw));
            var winners = cards.stream().filter(BingoCard::isComplete).toList();
            if (winners.size() == 1) {
                lastWinner = winners.get(0);
                lastWinningDraw = draw;
            }
            cards.removeIf(BingoCard::isComplete);
        }

        return lastWinner.sumOfUnmarkedNumbers() * lastWinningDraw;
    }

}
