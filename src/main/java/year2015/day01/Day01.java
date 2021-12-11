package year2015.day01;

import common.runners.Input;
import common.runners.Solution;
import io.vavr.Tuple2;
import io.vavr.collection.Stream;

public class Day01 extends Solution<Integer> {

    public Day01() {
        super(2015, 1);
    }

    @Override
    public Integer partOne(Input input) {
        var directions = Stream.ofAll(input.asOneLine().toCharArray());
        return directions.foldLeft(0, (total, c) -> total + (c == '(' ? 1 : -1));
    }

    @Override
    public Integer partTwo(Input input) {
        var directions = Stream.ofAll(input.asOneLine().toCharArray());
        return directions.foldLeft(new Tuple2<>(0, 0), (result, c) -> {
            if (result._2 < 0) return result;
            else return new Tuple2<>(result._1 + 1, result._2 + (c == '(' ? 1 : -1));
        })._1;
    }

}
