package common.runners;

import java.lang.reflect.InvocationTargetException;

public class RunLast {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        var solutionClass = Solutions.ALL.get(Solutions.ALL.size() - 1);
        var solution = solutionClass.getDeclaredConstructor().newInstance();
        solution.run();
    }

}
