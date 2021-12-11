package common;

import java.util.Collection;

public class IntCode {

    private final int[] memory;

    public IntCode(Collection<Integer> values) {
        memory = values.stream().mapToInt(Integer::intValue).toArray();
    }

    public void patch(int position, int value) {
        memory[position] = value;
    }

    public int run() {
        var ip = 0;
        while (true) {
            var opcode = memory[ip];
            if (opcode == 1) {
                memory[memory[ip + 3]] = memory[memory[ip + 1]] + memory[memory[ip + 2]];
                ip += 4;
            } else if (opcode == 2) {
                memory[memory[ip + 3]] = memory[memory[ip + 1]] * memory[memory[ip + 2]];
                ip += 4;
            } else if (opcode == 99) {
                return memory[0];
            }
        }
    }

}
