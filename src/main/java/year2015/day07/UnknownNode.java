package year2015.day07;

import io.vavr.collection.Map;

public final class UnknownNode implements WireNode {

    private final String name;
    private WireNode boundNode = null;

    public UnknownNode(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    @Override
    public void bind(Map<String, WireNode> nodes) {
        if (boundNode == null) {
            boundNode = nodes.get(name).getOrElseThrow(() -> new IllegalStateException("No node found with name " + name));
        }
    }

    @Override
    public int getValue() {
        if (boundNode == null) throw new UnsupportedOperationException("Unbound variable " + name);
        return boundNode.getValue();
    }

    @Override
    public String toString() {
        return "'" + name + "'";
    }
}
