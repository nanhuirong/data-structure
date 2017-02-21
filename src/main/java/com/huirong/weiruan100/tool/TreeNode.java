package com.huirong.weiruan100.tool;

/**
 * Created by nanhuirong on 16-8-28.
 */
public class TreeNode<T> {
    public T item;
    public boolean flag; //借助栈实现二叉树的后序遍历时用到

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public TreeNode<T> left;
    public TreeNode<T> right;

    public TreeNode() {
    }

    public TreeNode(T item, TreeNode<T> left, TreeNode<T> right) {
        this.item = item;
        this.left = left;
        this.right = right;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }
}
