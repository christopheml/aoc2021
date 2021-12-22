package common.runners;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

import static java.lang.Integer.parseInt;

public class RunOne {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        var year = parseInt(args[0]);
        var day = parseInt(args[1]);
        var solution = Objects.requireNonNull(Solutions.getSolution(year, day))
                .getDeclaredConstructor()
                .newInstance();
        solution.run();
    }

}
