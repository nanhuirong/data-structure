package com.huirong.tree;

import java.util.*;

/**
 * Created by huirong on 17-5-22.
 * 二叉树
 *  1.重建二叉树
 *  2.遍历 递归、非递归 前序、中序、后序 宽度优先遍历
 *  3.树的深度
 *  4.树的子结构
 *  5.为某个value的路径
 *
 * BST 二叉排序树、二叉搜索树
 *  1.树中两个节点的公共父节点
 *  2.BST于双向链表
 *
 * 特列
 *  1.堆
 *  2.红黑树
 */
public class BinaryTree {

//    public Node buildTree(int[] array){
//        if (array == null || array.length == 0){
//            return null;
//        }
//        Node root = null, pointer = root;
//        for (int i = 0; i < array.length; i++){
//            if (root == null && array[i] != 0){
//                root = new Node(array[i]);
//            }
//            if (array)
//        }
//
//    }

    /**
     * 重建二叉树
     * 已知二叉树的中序遍历 + 先序遍历|后序遍历可以重构整颗二叉树
     * 1.先序遍历 + 中序遍历
     *      先序遍历的第一个节点是根节点
     *      中序遍历中跟节点前面对应左子树，后面对应右子树
     *      中序遍历根节点前面节点个数对应前序遍历根节点后面的节点都为左子树，剩下为右子树
     * 2.后序遍历 + 中序遍历
     *      后序遍历最后一个节点对应跟节点
     *      剩下两条与前面对应
     *
     *        1
     *       / \
     *      2   3
     *     /   / \
     *    4   5   6
     *    \      /
     *     7    8
     */
    public Node construct(int[] preOrder, int[] inOrder){
        if(preOrder == null ||
                inOrder == null ||
                preOrder.length != inOrder.length ||
                preOrder.length <= 0){
           return null;
        }else {
            return construct(preOrder, 0, preOrder.length - 1,
                    inOrder, 0, inOrder.length - 1);
        }
    }

    private Node construct(int[] preOrder, int preStart, int preEnd,
                           int[] inOrder, int inStart, int inEnd){
         Node root = new Node(preOrder[preStart]);
        if (preStart == preEnd && preOrder[preStart] == inOrder[inStart]){
            if(preOrder[preStart] == inOrder[inStart])
                return root;
            else {
                throw new RuntimeException("");
            }
        }
        //在中序遍历中找到跟节点的值
        int index = inStart;
        while (index <= inEnd && inOrder[index] != preOrder[preStart])
            index++;
        if (index > inEnd){
            throw new RuntimeException("");
        }
        int leftLen = index - inStart;
        if (leftLen > 0){
            root.left = construct(preOrder, preStart + 1, preStart + leftLen,
                    inOrder, inStart, inStart + leftLen - 1);
        }
        if (leftLen < inEnd - inStart){
            root.right = construct(preOrder, preStart + leftLen + 1, preEnd,
                    inOrder, index + 1, inEnd);
        }
       return root;
    }

    /**
     * 18树的子结构
     * 输入二叉树A与B，判断B是否是A的子结构
     *           8            8
     *          /\           /\
     *         8  7         9  2
     *        /\
     *       9  2
     *          /\
     *         4  7
     */
    public boolean hasSubtree(Node root1, Node root2){
        boolean result = false;
        if (root1 != null && root2!= null){
            if (root1.value == root2.value){
                result = doesTree1HaveTree2(root1, root2);
            }
            if (!result){
                result = hasSubtree(root1.left, root2);
            }
            if (!result){
                result = hasSubtree(root1.right, root2);
            }
        }
        return result;
    }

    private boolean doesTree1HaveTree2(Node root1, Node root2){
        if (root2 == null){
            return true;
        }
        if (root1 == null){
            return false;
        }
        if (root1.value != root2.value){
            return false;
        }
        return doesTree1HaveTree2(root1.left, root2.left) &&
                doesTree1HaveTree2(root1.right, root2.right);
    }

    /**
     * 19   二叉树的镜像(递归解法)
     *  输入二叉树，输出他的镜像
     *       8          8
     *      /\         /\
     *     6  10      10 6
     *    /\  /\      /\ /\
     *   5 7 9 11   11 9 7 5
     *   思路：先序遍历，如果节点存在子节点，交换子节点，当交换完所有非叶子节点的子节点则得到镜像
     */
    private  void mirrorTree(Node root){
        if (root == null || (root.left == null && root.right == null)){
            return;
        }
        Node temp = root.left;
        root.left = root.right;
        root.right = temp;
        if (root.left != null){
            mirrorTree(root.left);
        }
        if (root.right != null){
            mirrorTree(root.right);
        }
    }

    /**
     * 23   二叉树的层次遍历
     *      借助队列实现二叉树的层次遍历
     *
     *      扩展：图的广度优先遍历
     */
    public void printFromTopToBottom(Node root){
        if (root == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (queue.size() != 0){
            Node pointer = queue.poll();
            System.out.print(pointer.value);
            if (pointer.left != null){
                queue.offer(pointer.left);
            }
            if (pointer.right != null){
                queue.offer(pointer.right);
            }
        }
    }

    /**
     * 25.二叉树中和为某个值的路径
     * 题目：输入一颗二叉树和一个值，打印出二叉树中和为该值的所有路径
     *      说明：从根节点往下到叶子节点形成一条路径
     *
     *      10
     *      /\
     *     5 12      和   22
     *    /\
     *   4 7
     *   两条路径：10->12    10->5->7
     * 思路：采用前序遍历
     */
    public void findPath(Node root, int sum){
        if (root == null){
            return;
        }
//        Stack<Node> stack = new Stack<>();
        List<Node> path = new ArrayList<>();
        int currentSum = 0;
        findPath(root, sum, path, currentSum);
    }
    private void findPath(Node root, int sum, List<Node> path, int currentSum){
        currentSum += root.value;
        path.add(root);
        boolean isLeaf = root.left == null && root.right == null;
        if (currentSum == sum && isLeaf){
            System.out.print("输出一条路径");
            for (int i = 0; i < path.size(); i++){
                System.out.print(path.get(i).value + " ");
            }
            System.out.println();
        }
        //如果不是叶子节点，遍历左子树
        if (root.left != null){
            findPath(root.left, sum, path, currentSum);
        }
        if (root.right != null){
            findPath(root.right, sum, path, currentSum);
        }
        //在返回到父节点之前，在路径上删除当前节点
        currentSum -= root.value;
        path.remove(path.size() - 1);
    }



    private class Node{
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
            left = null;
            right = null;
        }
    }


    public static void main(String[] args) {

    }
}
