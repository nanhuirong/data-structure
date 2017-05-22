package com.huirong.tree;


import com.sun.javafx.sg.prism.NGShape;

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
     * 非插入节点
     * @param key
     */
    public void insert(E key){
        if (root == null){
            root = new Node(key);
        }
        //记录父节点
        Node pre = null, pointer = root;
        while (pointer != null){
            pre = pointer;
            if (order(pointer.getValue(), key)){
                pointer = pointer.left;
            }else if(order(key, pointer.getValue())){
                pointer = pointer.right;
            }else {
                return;
            }
        }
        if (order(pre.getValue(), key)){
            pre.setLeft(new Node(key));
        }else {
            pre.setRight(new Node(key));
        }
    }


    public void insertRec(E key){
        root = insertRec(key, root);

    }
    private Node insertRec(E key, Node node){
        if (node == null){
            node = new Node(key);
        }else if (order(key, node.getValue())){
            node.right = insertRec(key, node.getRight());
        }else if (order(node.getValue(), key)){
            node.left = insertRec(key, node.getLeft());
        }
        return node;
    }

    /**
     * 寻找中序遍历的后继
     * 如果存在右子树，则寻找右子树最小的节点
     * 如果不存在右子树，则寻找父节点第一个比他大的元素
     *      如果存在parent节点，
     *      如果不存在parent节点，
     * @param elem
     * @return
     */
    public Node successor(E elem){
       Node pointer = root, succ = null;
       int flag = compare(elem, pointer.getValue());
       while (pointer!= null && flag != 0){
           if (flag > 0){
               pointer = pointer.getRight();
           }else if (flag < 0){
               //succ记录最近一次比elem大的节点
               succ = pointer;
               pointer = pointer.getLeft();
           }
           if (pointer != null){
               flag = compare(elem, pointer.getValue());
           }
       }
       pointer = pointer.getRight();
       if (pointer != null){
           //右子树不为空
           return min(pointer);
       }else {
           //右子树为空
           return succ;
       }
    }

    //
    public Node predecessor(E elem){
        Node pointer = root, predecessor = null;
        int flag = compare(elem, pointer.getValue());
        while (pointer!= null && flag != 0){
            if (flag > 0){
                predecessor = pointer;
                pointer = pointer.getRight();
            }else if (flag < 0){
                pointer = pointer.getLeft();
            }
            if (pointer != null){
                flag = compare(elem, pointer.getValue());
            }
        }
        pointer = pointer.getLeft();
        if (pointer != null){
            return max(pointer);
        }else {
            return predecessor;
        }
    }

    /**
     * 删除（分三种情况）
     * 1.叶子节点：直接删除
     * 2.只有一颗子树，将子数提升到该位置
     * 3.两个子树，用直接前驱或者后继都可以
     * @return
     */
    public void remove(E elem){
        Node parent = null, pointer = root;
        //-1代表左子树，+1代表右子树
        int direction = 0;
        if (pointer == null)
            return;
        int flag = compare(elem, pointer.getValue());
        while (flag != 0 && pointer != null){
            if (flag > 0){
                parent = pointer;
                direction = 1;
                pointer = pointer.getRight();
            }else {
                parent = pointer;
                direction = -1;
                pointer = pointer.getLeft();
            }
            if (pointer != null){
                flag = compare(elem, pointer.getValue());
            }
        }
        //如果没有找到该节点，返回空
        if (pointer == null){
            return;
        }

        if (pointer.getLeft() == null && pointer.getRight() == null){
            if (pointer == root){
                root = null;
            }else {
                if (direction == 1){
                    parent.setRight(null);
                }else if (direction == -1){
                    parent.setLeft(null);
                }
            }
        }else if (pointer.getLeft() == null && pointer.getRight() != null){
            if (pointer == root){
                root = root.getRight();
            }else {
                if (direction == 1){
                    parent.setRight(pointer.getRight());
                }else if (direction == -1){
                    parent.setLeft(pointer.getRight());
                }
            }
        }else if (pointer.getLeft() != null && pointer.getRight() == null){
            if (pointer == root){
                root = root.getLeft();
            }else {
                if (direction == 1){
                    parent.setRight(pointer.getLeft());
                }else if (direction == -1){
                    parent.setLeft(pointer.getLeft());
                }
            }
        }else {
            Node rightParent = pointer, rightPointer = pointer.getRight();

            while (rightPointer.getLeft() != null){
                rightParent = rightPointer;
                rightPointer = rightPointer.getLeft();
            }
            if (rightParent == pointer){
                rightPointer.setLeft(pointer.getLeft());
                if (pointer == root){
                    root = rightPointer;
                }else {
                    if (direction == 1){
                        parent.setRight(rightPointer);
                    }else if (direction == -1){
                        parent.setLeft(rightPointer);
                    }
                }
                return;
            }
            if (rightPointer.getRight() != null){
                rightParent.setLeft(rightPointer.getRight());
            }else {
                rightParent.setLeft(null);
            }
//            rightParent.setLeft(null);
            if (pointer == root){
                rightPointer.setLeft(root.getLeft());
                rightPointer.setRight(root.getRight());
                root = rightPointer;
            }else {
                rightPointer.setLeft(pointer.getLeft());
                rightPointer.setRight(pointer.getRight());
                if (direction == 1){
                    parent.setRight(rightPointer);
                }else if (direction == -1){
                    parent.setLeft(rightPointer);
                }
            }
        }
    }



    /**
     * 前序递归遍历
     */
    public void preOrder(){
        String result = preOrder(root);
        System.out.println("前序递归遍历\t" + result.substring(0, result.length() - 1));
    }
    private String preOrder(Node node){
        if (node == null){
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(node.getValue()).append(",");
        builder.append(preOrder(node.getLeft()));
        builder.append(preOrder(node.getRight()));
        return builder.toString();
    }

    /**
     * 中序递归遍历
     * BST的后序遍历恰好对应升序排列
     */
    public void inOrder(){
        String result = inOrder(root);
        if (result.length() > 0)
            System.out.println("中序递归遍历\t" + result.substring(0, result.length() - 1));
    }

    private String inOrder(Node node){
        if (node == null){
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(inOrder(node.getLeft()));
        builder.append(node.getValue()).append(",");
        builder.append(inOrder(node.getRight()));
        return builder.toString();
    }

    /**
     * 后序递归遍历
     */
    public void postOrder(){
        String result = postOrder(root);
        System.out.println("后序递归遍历\t" + result.substring(0, result.length() - 1));
    }

    private String postOrder(Node node){
        if (node == null){
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(postOrder(node.getLeft()));
        builder.append(postOrder(node.getRight()));
        builder.append(node.getValue() + ",");
        return builder.toString();
    }


    /**
     * 查找
     * @param elem
     * @return
     */
    public Node search(E elem){
        Node pointer = root;
        int flag = compare(elem, pointer.getValue());
        while (pointer != null && flag != 0){
            if (flag > 0){
                pointer = pointer.getRight();
            }else {
                pointer = pointer.getLeft();
            }
            if (pointer != null){
                flag = compare(elem, pointer.getValue());
            }
        }
        return pointer;
    }

    public Node max(){
        return max(root);
    }

    private Node max(Node node){
        Node pre = node;
        Node pointer = node.getRight();
        while (pointer != null){
            pre = pointer;
            pointer = pointer.getRight();
        }
        return pre;
    }

    public Node min(){
        return min(root);
    }

    public Node min(Node node){
        Node pre = node, pointer = node.getLeft();
        while (pointer != null){
            pre = pointer;
            pointer = pointer.getLeft();
        }
        return pre;
    }





    private boolean order(E elem1, E elem2){
        return comparator.compare(elem1, elem2) > 0 ? true : false;
    }

    private int compare(E elem1, E elem2){
        return comparator.compare(elem1, elem2);
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
