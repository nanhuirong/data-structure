package com.huirong.weiruan100;

import com.huirong.weiruan100.tool.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nanhuirong on 16-8-28.
 * 求二叉树中节点的最大距离
 */
public class test011 {
    private int maxLen = 0;
    public static void main(String[] args){
        int[] arrayA ={1,2,3,4,5,6,7};
        test011 demo = new test011();
        Node011<Integer> root = demo.createNode(arrayA);
        demo.printTree(root);
        System.out.println();
        demo.findMaxLen(root);
        System.out.println(demo.maxLen);
        int[] arrayB = {1,2,3,4,5,0,0,6,0,0,7,0,0,0,0,8,0,0,0,0,0,0,9};
        root = demo.createNode(arrayB);
        demo.printTree(root);
        System.out.println();
        demo.findMaxLen(root);
        System.out.println(demo.maxLen);
    }

    public void printTree(Node011<Integer> node){
        if (node == null) return;
        printTree(node.getLeft());
        System.out.print(node.getData() + ",");
        printTree(node.getRight());
    }

    public void findMaxLen(Node011<Integer> node){
        if (node == null) return;
        if (node.getLeft() == null){
            node.setMaxLeftLen(0);
        }
        if (node.getRight() == null){
            node.setMaxRightLen(0);
        }
        if (node.getLeft() != null){
//            System.out.println(node.getData());
            findMaxLen(node.getLeft());
        }
        if (node.getRight() != null){
//            System.out.println(node.getData());
            findMaxLen(node.getRight());
        }
        if (node.getLeft() != null){
            int temp = 0;
            Node011<Integer> left = node.getLeft();
            temp = Math.max(left.getMaxLeftLen(), left.getMaxRightLen());
            node.setMaxLeftLen(temp + 1);
        }
        if (node.getRight() != null){
            int temp = 0;
            Node011<Integer> right = node.getRight();
            temp = Math.max(right.getMaxLeftLen(), right.getMaxRightLen());
            node.setMaxRightLen(temp + 1);
        }
        if (maxLen < node.getMaxLeftLen() + node.getMaxRightLen()){
            maxLen = node.getMaxLeftLen() + node.getMaxRightLen();
        }
    }


    public Node011<Integer> createNode(int[] array){
        List<Node011<Integer>> nodeList= new ArrayList<Node011<Integer>>(array.length);
        for (int each: array){
            Node011<Integer> node = new Node011<Integer>(each);
            nodeList.add(node);
        }
        int lastRootIndex = nodeList.size() / 2 - 1;
        for (int i = 0; i <= lastRootIndex; i++){
            Node011<Integer> root = nodeList.get(i);
            Node011<Integer> left = nodeList.get(i * 2 + 1);
            if (left.getData() != 0){
                root.setLeft(left);
            }else {
                root.setLeft(null);
            }
            if (i * 2 + 2 < array.length){
                Node011<Integer> right = nodeList.get(i * 2 + 2);
                if (right.getData() != 0){
                    root.setRight(right);
                }else {
                    root.setRight(null);
                }
            }
        }
        Node011<Integer> root = nodeList.get(0);
        return root;

    }


}

class Node011<T>{
    private T data;
    private Node011<T> left;
    private Node011<T> right;
    private int maxLeftLen;
    private int maxRightLen;

    public Node011(T data) {
        this.data = data;
    }

    public Node011(T data, Node011<T> left, Node011<T> right, int maxLeftLen, int maxRightLen) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.maxLeftLen = maxLeftLen;
        this.maxRightLen = maxRightLen;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node011<T> getLeft() {
        return left;
    }

    public void setLeft(Node011<T> left) {
        this.left = left;
    }

    public Node011<T> getRight() {
        return right;
    }

    public void setRight(Node011<T> right) {
        this.right = right;
    }

    public int getMaxLeftLen() {
        return maxLeftLen;
    }

    public void setMaxLeftLen(int maxLeftLen) {
        this.maxLeftLen = maxLeftLen;
    }

    public int getMaxRightLen() {
        return maxRightLen;
    }

    public void setMaxRightLen(int maxRightLen) {
        this.maxRightLen = maxRightLen;
    }
}
