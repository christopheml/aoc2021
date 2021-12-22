package year2015.day04;

import common.input.Input;
import common.runners.Solution;
import org.apache.commons.codec.digest.DigestUtils;

public class Day04 extends Solution<Integer> {

    public Day04() {
        super(2015, 4);
    }

    @Override
    public Integer partOne(Input input) {
        return findHash2(input.asOneLine(), "00000");
    }

    @Override
    public Integer partTwo(Input input) {
        return findHash2(input.asOneLine(), "000000");
    }

    private int findHash2(String key, String prefix) {
        int i = 0;
        while (true) {
            if (DigestUtils.md5Hex(key + i).startsWith(prefix)) return i;
            i++;
        }
    }

}
