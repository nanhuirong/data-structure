package com.huirong.tree;

import org.w3c.dom.Node;

import java.util.Comparator;

/**
 * Created by huirong on 17-4-16.
 * 二叉排序树
 */
public class BinarySortTree<E> {
    private final Comparator comparator;
    private Node root = null;


    public BinarySortTree(Comparator comparator) {
        this.comparator = comparator;
    }

    /**
     * 插入节点
     * @param key
     */
    public void insert(E key){
        Node current = root, pre = null;
        while (current != null){
            pre = current;
            if (order(key, current.getValue())){
                current = current.getRight();
            }else if (order(current.getValue(), key)){
                current = current.getLeft();
            }else {
                return;
            }
        }
        if (root == null){
            root = new Node(key);
        }
        if (order(pre.getValue(), key)){
            pre.setRight(new Node(key));
        }else {
            pre.setLeft(new Node(key));
        }
    }

    /**
     * 前序非递归遍历
     * @param node
     */
    public void preOrder(Node node){
        if (node == null){
            return;
        }
        System.out.print(node.getValue() + ",");
        preOrder(node.getLeft());
        preOrder(node.getRight());
    }

    /**
     * 中序非递归遍历
     * @param node
     */
    public void InOrder(Node node){
        
    }


    private boolean order(E elem1, E elem2){
        return comparator.compare(elem1, elem2) > 0 ? true : false;
    }

    private class Node{
        private E value;
        private Node left;
        private Node right;

        public Node() {
        }

        public Node(E value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

        public Node(E value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}
