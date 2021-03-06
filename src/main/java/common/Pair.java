package common;

public class Pair<T, U> {

    private T first;
    private U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T first() {
        return first;
    }

    public U second() {
        return second;
    }

    public void setFirst(T value) {
        first = value;
    }

    public void setSecond(U value) {
        second = value;
    }

}
