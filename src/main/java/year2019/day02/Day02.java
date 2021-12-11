package year2019.day02;

import common.IntCode;
import common.StringOps;
import common.runners.Input;
import common.runners.Solution;

public class Day02 extends Solution<Integer> {
    public Day02() {
        super(2019, 2);
    }

    @Override
    public Integer partOne(Input input) {
        var code = new IntCode(StringOps.asList(input.asOneLine(), ",", Integer::parseInt));
        code.patch(1, 12);
        code.patch(2, 2);
        return code.run();
    }

    @Override
    public Integer partTwo(Input input) {
        var source = StringOps.asList(input.asOneLine(), ",", Integer::parseInt);
        for (int noun = 0; noun < 100; ++noun) {
            for (int verb = 0; verb < 100; ++verb) {
                var code = new IntCode(source);
                code.patch(1, noun);
                code.patch(2, verb);
                var result = code.run();
                if (result == 19690720) return noun * 100 + verb;
            }
        }

        return null;
    }

}
