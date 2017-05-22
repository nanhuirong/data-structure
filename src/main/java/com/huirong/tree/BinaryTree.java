package com.huirong.tree;

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
    private Node root;

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
}
