package year2015.day07;

public sealed abstract class CachedWireNode implements WireNode permits BinaryOperation, UnaryOperation {

    private Integer cachedValue;

    @Override
    public int getValue() {
        if (cachedValue == null) cachedValue = computeValue();
        return cachedValue;
    }

    protected abstract int computeValue();

}
