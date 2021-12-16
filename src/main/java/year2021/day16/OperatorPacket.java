package year2021.day16;

import io.vavr.collection.Seq;

public final class OperatorPacket extends Packet {

    private final Seq<Packet> children;

    public OperatorPacket(int version, int id, Seq<Packet> children) {
        super(version, id);
        this.children = children;
    }

    @Override
    public Seq<Packet> children() {
        return children;
    }

    @Override
    public long value() {
        return switch (id) {
            case 0 -> children().map(Packet::value).reduceLeft(Math::addExact);
            case 1 -> children.map(Packet::value).reduceLeft(Math::multiplyExact);
            case 2 -> children.map(Packet::value).min().get();
            case 3 -> children.map(Packet::value).max().get();
            case 5 -> children.get(0).value() > children.get(1).value() ? 1L : 0L;
            case 6 -> children.get(0).value() < children.get(1).value() ? 1L : 0L;
            case 7 -> children.get(0).value() == children.get(1).value() ? 1L : 0L;
            case default -> throw new IllegalStateException("Unexpected packet with type " + id);
        };
    }
}
