package com.huirong.weiruan100New.tool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by nanhuirong on 16-9-12.
 */
public class BSTTreeTool{
    private BSTTreeNode<Integer> root;

    public BSTTreeTool(BSTTreeNode<Integer> root) {
        this.root = root;
    }

    public BSTTreeTool() {
    }

    public boolean isEmpty(){
        return root == null ? true : false;
    }

    public void insert(Integer data){
        if (root == null){
            this.root = new BSTTreeNode<Integer>(data, null, null);
            return;
        }
        BSTTreeNode<Integer> tempNode = root;
        while (true){
            if (data < tempNode.getData()){
                if (tempNode.getLeft() == null){
                    tempNode.setLeft(new BSTTreeNode<Integer>(data, null, null));
                    break;
                }
                tempNode = tempNode.getLeft();
            }else if (data > tempNode.getData()){
                if (tempNode.getRight() == null){
                    tempNode.setRight(new BSTTreeNode<Integer>(data, null, null));
                    break;
                }
                tempNode = tempNode.getRight();
            }
        }
    }

    public void preOrder(){
        System.out.print("树"+ this.root.hashCode() + "递归前序遍历:");
        BSTTreeNode<Integer> node = root;
        preOrder(node);
        System.out.println("\n树"+ this.root.hashCode() + "遍历完毕");
    }
    private void preOrder(BSTTreeNode<Integer> node){
        if (node == null){
            return;
        }
        System.out.print(node.data + ",");
        preOrder(node.getLeft());
        preOrder(node.getRight());
    }

    /*
    ：输入一个整数和一棵二元树。
    从树的根结点开始往下访问一直到叶结点所经过的所有结点形成一条路径。
    打印出和与输入整数相等的所有路径。
    例如 输入整数22和如下二元树
        10
      /   /
    5    12
    / \
    4  7
    则打印出两条路径：10, 12和10, 5, 7。
     */
    public void printSumPath(int sum){
        BSTTreeNode<Integer> node = root;
        ArrayList<BSTTreeNode<Integer>> path = new ArrayList<BSTTreeNode<Integer>>();
        printSumPath(node, path, 0, sum);
    }
    private void printSumPath(BSTTreeNode<Integer> node, ArrayList<BSTTreeNode<Integer>> path, int currentSum, int sum){
        if (node == null){
            return;
        }
        currentSum += node.getData();
        path.add(node);
        boolean isLeaf = (node.getLeft() == null && node.getRight() == null);
        if (isLeaf && currentSum == sum){
            Iterator<BSTTreeNode<Integer>> iterator = path.iterator();
            while (iterator.hasNext()){
                System.out.print(iterator.next().getData() + "->");
            }
            System.out.println("null");
        }
        printSumPath(node.getLeft(), path, currentSum, sum);
        printSumPath(node.getRight(), path, currentSum, sum);
        path.remove(path.size() - 1);

    }

    /*
    判断整数序列是不是二元查找树的后序遍历结果
    题目：输入一个整数数组，判断该数组是不是某二元查找树的后序遍历的结果。
    如果是返回true，否则返回false。
    例如输入5、7、6、9、11、10、8，由于这一整数序列是如下树的后序遍历结果：
           8
        /  /
       6    10
     / /  / /
     5  7 9  11
    因此返回true。
    如果输入7、4、6、5，没有哪棵树的后序遍历的结果是这个序列，因此返回false
     */
    public boolean isBSTPostOrder(int[] array){
        if (array == null){
            return false;
        }
        return isBSTPostOrder(array, 0, array.length - 1);
    }
    private boolean isBSTPostOrder(int[] array, int start, int end){
        //跟节点为当前的end元素, 从右往左第一个小于跟节点的元素便是左子树
        int leftIndex = end - 1;
        while (array[leftIndex] > array[end]) leftIndex--;
        int rightIndex = leftIndex + 1;
        for (int i = start; i <= leftIndex; i++){
            if (array[i] >= array[end]){
                return false;
            }
        }
        for (int i = rightIndex; i < end; i++){
            if (array[i] <= array[end]){
                return false;
            }
        }
        boolean bLeft = true;
        if (leftIndex > 0){
            bLeft = isBSTPostOrder(array, start, leftIndex);
        }
        boolean bRight = true;
        if (rightIndex < end){
            bRight = isBSTPostOrder(array, rightIndex, end - 1);
        }
        return bLeft && bRight;
    }

    /*
    题目：输入一颗二元查找树，将该树转换为它的镜像，
    即在转换后的二元查找树中，左子树的结点都大于右子树的结点。
    用递归和循环两种方法完成树的镜像转换。
    例如输入：
      8
      / /
     6 10
    // //
    5 7 9 11

    输出：
         8
     / /
    10 6
    // //
    11 9 7 5
     */
    //递归形态借助的是树的后续遍历
    public void mirrorRecursion(){
        BSTTreeNode<Integer> node = this.root;
        mirrorRecursion(node);
    }
    private void mirrorRecursion(BSTTreeNode<Integer> node){
        if (node == null){
            return;
        }
        mirrorRecursion(node.getLeft());
        mirrorRecursion(node.getRight());
        BSTTreeNode<Integer> temp = node.getLeft();
        node.setLeft(node.getRight());
        node.setRight(temp);
    }

    //非递归形态, 借助栈实现
    public void mirror(){
        if (this.root == null){
            return;
        }
        BSTTreeNode<Integer> node = this.root;
        LinkedList<BSTTreeNode<Integer>> stack = new LinkedList<BSTTreeNode<Integer>>();
        stack.push(node);
        while (!stack.isEmpty()){
            node = stack.pop();
            if (node.getLeft() != null){
                stack.push(node.getLeft());
            }
            if (node.getRight() != null){
                stack.push(node.getRight());
            }
            BSTTreeNode<Integer> temp = node.getLeft();
            node.setLeft(node.getRight());
            node.setRight(temp);
        }
    }

    /*
    第16题（树）：
    题目（微软）：
    输入一颗二元树，从上往下按层打印树的每个结点，同一层中按照从左往右的顺序打印。
    例如输入
       8
     / /
     6 10
    / / / /
    5 7 9 11
    输出8 6 10 5 7 9 11。
     */
    //树的层次遍历, 借助队列实现
    public void foreachByLevel(){
        System.out.print("树" + this.root.hashCode() + "的层次遍历:");
        BSTTreeNode<Integer> node = this.root;
        LinkedList<BSTTreeNode<Integer>> queue = new LinkedList<BSTTreeNode<Integer>>();
        queue.addLast(node);
        while (!queue.isEmpty()){
            node = queue.poll();
            System.out.print(node.getData() + ",");
            if (node.getLeft() != null){
                queue.addLast(node.getLeft());
            }
            if (node.getRight() != null){
                queue.addLast(node.getRight());
            }
        }
        System.out.print("\n树" + this.root.hashCode() + "的层次遍历完毕\n");
    }

    public static void main(String[] args){
        BSTTreeTool tree = new BSTTreeTool();
//        System.out.println(tree.isEmpty());
//        tree.insert(4);
//        tree.insert(2);
//        tree.insert(6);
//        tree.insert(1);
//        tree.insert(3);
//        tree.insert(5);
//        tree.insert(7);
//        tree.preOrder();

        //测试printSumPath
//        tree.insert(10);
//        tree.insert(5);
//        tree.insert(12);
//        tree.insert(4);
//        tree.insert(7);
//        tree.printSumPath(22);

        //测试数组元素是否是BST的后序遍历
//        int[][] array = {
//                {5, 7, 6, 9, 10, 8},
//                {2, 3, 4, 6, 5},
//                {5, 6, 7},
//                {5, 7, 6},
//                {1, 3, 2, 5, 7, 6, 4, 9, 13, 15, 14, 12, 8},
//                {7, 4, 6, 5}
//        };
//        for (int[] arr: array){
//            System.out.println(Arrays.toString(arr) + "\t" + tree.isBSTPostOrder(arr));
//        }

        //BST树的镜像
        tree.insert(8);
        tree.insert(6);
        tree.insert(10);
        tree.insert(5);
        tree.insert(7);
        tree.insert(9);
        tree.insert(11);
        tree.mirrorRecursion();
        tree.preOrder();
        tree.mirrorRecursion();
        tree.preOrder();
        tree.mirror();
        tree.preOrder();
        tree.mirror();
        tree.preOrder();
        //测试树的层次遍历
        tree.foreachByLevel();


    }

    private class BSTTreeNode<T>{
        private T data;
        private boolean flag; //非递归遍历时用到
        private BSTTreeNode<T> left;
        private BSTTreeNode<T> right;

        public BSTTreeNode(T data, BSTTreeNode<T> left, BSTTreeNode<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public BSTTreeNode<T> getLeft() {
            return left;
        }

        public void setLeft(BSTTreeNode<T> left) {
            this.left = left;
        }

        public BSTTreeNode<T> getRight() {
            return right;
        }

        public void setRight(BSTTreeNode<T> right) {
            this.right = right;
        }
    }
}
