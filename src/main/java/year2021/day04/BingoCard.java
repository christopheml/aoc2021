package year2021.day04;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BingoCard {

    private final boolean[][] marked;
    private final int[][] numbers;

    public BingoCard(List<List<Integer>> lines) {
        marked = new boolean[5][5];
        numbers = new int[5][5];

        for (int y = 0; y < 5; ++y) {
            for (int x = 0; x < 5; ++x) {
                numbers[y][x] = lines.get(y).get(x);
            }
        }
    }

    public boolean isComplete() {
        for (int i = 0; i < 5; ++i) {
            if (checkLine(i)) return true;
            if (checkColumn(i)) return true;
        }
        return false;
    }

    public int sumOfUnmarkedNumbers() {
        var sum = 0;
        for (int y = 0; y < 5; ++y) {
            for (int x = 0; x < 5; ++x) {
                if (!marked[y][x]) sum += numbers[y][x];
            }
        }
        return sum;
    }

    public void draw(int number) {
        for (int y = 0; y < 5; ++y) {
            for (int x = 0; x < 5; ++x) {
                if (numbers[y][x] == number) marked[y][x] = true;
            }
        }
    }

    public boolean checkLine(int i) {
        return marked[i][0] && marked[i][1] && marked[i][2] && marked[i][3] && marked[i][4];
    }

    public boolean checkColumn(int i) {
        return marked[0][i] && marked[1][i] && marked[2][i] && marked[3][i] && marked[4][i];
    }

    @Override
    public String toString() {
        return Arrays.stream(numbers).map(line -> Arrays.stream(line).mapToObj(i -> String.format("%1$2d", i)).collect(Collectors.joining(" "))).collect(Collectors.joining("\n"));
    }
}
