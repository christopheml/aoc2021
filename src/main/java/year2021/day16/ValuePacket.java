package year2021.day16;

import io.vavr.collection.List;
import io.vavr.collection.Seq;

public final class ValuePacket extends Packet {

    private final long value;

    public ValuePacket(int version, int id, long value) {
        super(version, id);
        this.value = value;
    }

    @Override
    public Seq<Packet> children() {
        return List.empty();
    }

    @Override
    public long value() {
        return value;
    }

}
