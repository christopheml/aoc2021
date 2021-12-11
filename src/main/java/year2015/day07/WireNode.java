package year2015.day07;

import io.vavr.collection.Map;

public sealed interface WireNode permits CachedWireNode, Constant, UnknownNode {

    int getValue();

    void bind(Map<String, WireNode> nodes);

}
