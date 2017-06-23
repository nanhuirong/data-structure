package com.huirong.tree;

/**
 * Created by huirong on 17-6-1.
 * 二叉排序树
 */
public class BinarySortTreeImpl {

    /**
     * 24.二叉排序树的后序遍历序列
     *    题目：给定整数数组，判断该数组是某个二叉搜索树的后序遍历结果
     *          假设输入的数据不存在相同的数字
     *
     *    思路：后序遍历的特点 1）根节点位于最后
     *                       2）如果首节点大于根节点则不存在左子树
     *
     *    扩展：前序遍历思路一样
     *
     */
    public boolean isPostOrderOfBST(int[] sequence){
        if (sequence == null || sequence.length == 0){
            return false;
        }
        return isPostOrderOfBST(sequence, 0, sequence.length - 1);
    }

    private boolean isPostOrderOfBST(int[] sequence, int start, int end){
        int root = sequence[end];
        //BST中左子树的节点小于根节点
        int leftIndex = start;
        for (; leftIndex < end; leftIndex++){
            if (sequence[leftIndex] > root){
                break;
            }
        }
        //BST中右子树节点大于跟节点
        int rightIndex = leftIndex;
        for (; rightIndex < end; rightIndex++){
            if (sequence[rightIndex] < root){
                return false;
            }
        }
        boolean left = isPostOrderOfBST(sequence, start, leftIndex - 1);
        boolean right = isPostOrderOfBST(sequence, leftIndex, end - 1);

        return left && right;
    }

    /**
     * 27.二叉排序树与双向链表
     * 题目：输入一颗二叉排序树，将该二叉排序树转化为一个排序的双向链表，要求不能创建新的节点
     *      只能调整树中节点的指向
     *      10
     *      /\
     *     6 14     ====>
     *    /\  /\
     *   4 8 12 16
     * 思路：将根节点与其直接前驱和直接后继相连
     *      利用中序遍历
     */
    public Node convert(Node root){
        Node lastNodeInList = null;
        convertNode(root, lastNodeInList);
        //lastNodeInList指向双向链表的表尾
        Node head = lastNodeInList;
        while (head != null && head.left != null){
            head = head.left;
        }
        return head;
    }
    private void convertNode(Node root, Node lastNodeInList){
        if (root == null)
            return;
        Node current = root;
        if (current.left != null){
            convertNode(current.left, lastNodeInList);
        }
        current.left = lastNodeInList;
        if (lastNodeInList != null)
            lastNodeInList.right = current;
        lastNodeInList = current;
        if (current.right != null){
            convertNode(current.right, lastNodeInList);
        }
    }


    private class Node{
        int value;
        Node left;
        Node right;

        public Node(int value){
            this.value = value;
            left = null;
            right = null;
        }

    }
}
