package year2021.day16;

import common.StringOps;
import common.input.Input;
import common.runners.Solution;
import io.vavr.collection.Vector;
import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;

public class Day16 extends Solution<Long> {

    public Day16() {
        super(2021, 16);
    }

    private String toBinary(char digit) {
        return StringUtils.leftPad(
                new BigInteger(Character.toString(digit), 16).toString(2), 4, '0');
    }

    private long longValue(String bits) {
        return new BigInteger(bits, 2).longValue();
    }

    private int intValue(String bits) {
        return new BigInteger(bits, 2).intValue();
    }

    public Vector<Packet> parse(ConsumableString bits, long packetLimit) {
        var packets = Vector.<Packet>empty();
        while (bits.length() > 10 && (packetLimit == 0 || packets.size() < packetLimit)) {
            // New packet
            var version = intValue(bits.take(3).toString());
            var type = intValue(bits.take(3).toString());
            if (type == 4) {
                // Value packet
                var valueBits = new StringBuilder();
                while (true) {
                    var extraBits = bits.take(5).toString();
                    valueBits.append(extraBits.substring(1));
                    if (extraBits.charAt(0) == '0') break;
                }
                var value = longValue(valueBits.toString());
                packets = packets.append(new ValuePacket(version, type, value));
            } else {
                // Operator packet
                boolean bitSized = bits.take(1).toString().equals("0");
                if (bitSized) {
                    var subpacketSize = intValue(bits.take(15).toString());
                    var children = parse(bits.take(subpacketSize), 0);
                    packets = packets.append(new OperatorPacket(version, type, children));
                } else {
                    // Subpackets
                    var subpacketCount = intValue(bits.take(11).toString());
                    var children = parse(bits, subpacketCount);
                    packets = packets.append(new OperatorPacket(version, type, children));
                }
            }
        }
        return packets;
    }

    public long sumOfVersions(Packet packet) {
        return packet.version + packet.children().map(this::sumOfVersions).sum().longValue();
    }

    @Override
    public Long partOne(Input input) {
        var bits = new ConsumableString(StringOps.toChars(input.asOneLine())
                .map(this::toBinary)
                .mkString());

        var root = parse(bits, 0).head();

        return sumOfVersions(root);
    }

    @Override
    public Long partTwo(Input input) {
        var bits = new ConsumableString(StringOps.toChars(input.asOneLine())
                .map(this::toBinary)
                .mkString());

        var root = parse(bits, 0).head();

        return root.value();
    }

}
