package common.tree;

public final class BinaryTreeNode<T> implements BinaryTree<T> {

    private BinaryTreeNode<T> parent;
    private BinaryTree<T> left;
    private BinaryTree<T> right;

    public BinaryTreeNode(BinaryTree<T> left, BinaryTree<T> right) {
        this.left = left;
        this.right = right;
        left.setParent(this);
        right.setParent(this);
    }

    public BinaryTree<T> left() {
        return left;
    }

    public BinaryTree<T> right() {
        return right;
    }

    public void replace(BinaryTree<T> node, BinaryTree<T> other) {
        other.setParent(this);
        if (left == node) {
            left = other;
        } else if (right == node) {
            right = other;
        } else throw new IllegalStateException("Illegal replacement");
    }

    public boolean isLeft() {
        return parent() != null && parent().left == this;
    }

    public boolean isRight() {
        return parent() != null && parent().right == this;
    }

    @Override
    public BinaryTreeNode<T> parent() {
        return parent;
    }

    @Override
    public void setParent(BinaryTreeNode<T> parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "[" + left.toString() + "," + right.toString() + "]";
    }

}
