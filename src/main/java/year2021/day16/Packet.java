package year2021.day16;

import io.vavr.collection.Seq;

public abstract sealed class Packet permits OperatorPacket, ValuePacket {

    public final int version;
    public final int id;

    protected Packet(int version, int id) {
        this.version = version;
        this.id = id;
    }

    public abstract Seq<Packet> children();

    public abstract long value();

}
