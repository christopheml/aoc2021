package common.runners;

import java.lang.reflect.InvocationTargetException;

public class RunAll {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        for (var solutionClass : Solutions.ALL) {
            var solution = solutionClass.getDeclaredConstructor().newInstance();
            solution.run();
        }
    }

}
