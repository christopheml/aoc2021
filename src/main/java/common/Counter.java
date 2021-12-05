package common;

public class Counter {

    private int value = 0;

    public int inc() {
        return ++value;
    }

    public int dec() {
        return --value;
    }

    public int get() {
        return value;
    }

}
