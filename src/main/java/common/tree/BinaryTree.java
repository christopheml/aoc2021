package common.tree;

public sealed interface BinaryTree<T> permits BinaryTreeNode, BinaryTreeValue {

    BinaryTreeNode<T> parent();

    void setParent(BinaryTreeNode<T> parent);

}
