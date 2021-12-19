package common.tree;

public sealed interface BinaryTree<T> permits BinaryTreeNode, BinaryTreeValue {

    BinaryTreeNode<T> parent();

    void setParent(BinaryTreeNode<T> parent);

    default BinaryTreeNode<T> root() {
        var current = this;
        while (current.parent() != null) current = current.parent();
        return (BinaryTreeNode<T>) current;
    }

}
