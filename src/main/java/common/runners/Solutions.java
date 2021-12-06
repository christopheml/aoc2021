package common.runners;

import day01.Day01;
import day02.Day02;
import day03.Day03;
import day04.Day04;
import day05.Day05;
import day06.Day06;

import java.util.List;

public class Solutions {

    public static final List<Class<? extends Solution<?>>> ALL = List.of(
            Day01.class,
            Day02.class,
            Day03.class,
            Day04.class,
            Day05.class,
            Day06.class
    );

}
