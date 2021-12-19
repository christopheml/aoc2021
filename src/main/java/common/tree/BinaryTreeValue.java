package common.tree;

public final class BinaryTreeValue<T> implements BinaryTree<T> {

    private T value;
    private BinaryTreeNode<T> parent;

    public BinaryTreeValue(T value) {
        this.value = value;
    }

    public T value() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public BinaryTreeNode<T> parent() {
        return parent;
    }

    @Override
    public void setParent(BinaryTreeNode<T> parent) {
        this.parent = parent;
    }
}
