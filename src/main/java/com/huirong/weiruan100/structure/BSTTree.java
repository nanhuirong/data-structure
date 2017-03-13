package com.huirong.weiruan100.structure;


import sun.reflect.generics.tree.Tree;
import com.huirong.weiruan100.tool.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by nanhuirong on 16-8-29.
 */
public class BSTTree {
    private TreeNode<Integer> root = null;
    public TreeNode<Integer> getRoot() {
        return root;
    }

    public void setRoot(TreeNode<Integer> root) {
        this.root = root;
    }

    //判断树是否为空
    public boolean isEmpty(){
        if (root == null){
            return true;
        }else {
            return false;
        }
    }

    //利用递归将关键字插入树中
    public TreeNode<Integer> insert(TreeNode<Integer> root, int item){
        if (root == null){
            return new TreeNode<Integer>(item, null, null);
        }else if (item < root.getItem()){
            root.left =  insert(root.left, item);
        }else if(item > root.getItem()){
            root.right = insert(root.right, item);
        }
        return root;
    }

    //前序遍历
    public void preOrder(TreeNode<Integer> root){
        if (root == null){
            return;
        }
        System.out.print(root.getItem() + ",");
        preOrder(root.getLeft());
        preOrder(root.getRight());
    }


    //前序遍历, 非递归, 当第一次接触到该节点时便进行访问
    public void preOrderWithoutRecursive(TreeNode<Integer> root){
        //借助栈实现树的前序非递归遍历
        LinkedList<TreeNode<Integer>> stack = new LinkedList<TreeNode<Integer>>();
        if (root == null){
            return;
        }
        TreeNode<Integer> node = root;
        while (!(node == null && stack.size() == 0)){
            //如果node不为空, 遍历所有的左子树
            while (node != null){
                System.out.print(node.getItem() + ",");
                stack.push(node);
                node = node.left;
            }
            if (stack.size() <= 0){
                return;
            }else {
                node = stack.pop();
                node = node.right;
            }
        }
    }

    //中序遍历, BST 的中序遍历是一个递增的序列
    public void inOrder(TreeNode<Integer> root){
        if (root == null){
            return;
        }
        inOrder(root.getLeft());
        System.out.print(root.getItem() + ",");
        inOrder(root.getRight());
    }


    //中序遍历的非递归实现, 在节点从栈中弹出时访问该节点
    public void inOrderWithoutRecursive(TreeNode<Integer> root){
        LinkedList<TreeNode<Integer>> stack = new LinkedList<TreeNode<Integer>>();
        if (root == null){
            return;
        }
        TreeNode<Integer> node = root;
        while (!(node == null && stack.size() == 0)){
            while (node != null){
                stack.push(node);
                node = node.left;
            }
            if (stack.size() <= 0){
                return;
            }else {
                node = stack.pop();
                System.out.print(node.getItem() + ",");
                node = node.right;
            }
        }
    }

    //后续遍历
    public void postOrder(TreeNode<Integer> root){
        if (root == null){
            return;
        }
        postOrder(root.getLeft());
        postOrder(root.getRight());
        System.out.print(root.getItem() + ",");
    }

    //后序遍历的非递归实现, 节点要两次入栈出栈, 当节点第二次出栈的时候访问该节点
    public void postOrderWithoutRecursive(TreeNode<Integer> root){
        if (root == null){
            return;
        }
        TreeNode<Integer> node = root;
        LinkedList<TreeNode<Integer>> stack = new LinkedList<TreeNode<Integer>>();
        while (!(node == null && stack.size() <= 0)){
            while (node != null){
                node.setFlag(true);
                stack.push(node);
                node = node.left;
            }
            if (stack.size() <= 0){
                return;
            }else {
                node = stack.pop();
                if (node.isFlag()){
                    node.setFlag(false);
                    stack.push(node);
                    node = node.right;
                }else {
                    System.out.print(node.item + ",");
                    node = null;
                }
            }
        }
    }

    //输入一颗BST, 将左右子树对换, 简称镜像
    public TreeNode<Integer> mirrorRecursion(TreeNode<Integer> root){
        if (root != null){
            TreeNode<Integer> left = root.getLeft();
            TreeNode<Integer> right = root.getRight();
            root.setLeft(mirrorRecursion(right));
            root.setRight(mirrorRecursion(left));
        }
        return root;
    }

    //输出一颗BST, 将左右子树对换, 采用非递归的方式
    public TreeNode<Integer> mirror(TreeNode<Integer> root){
        if (root != null){
            //利用栈进行辅助操作
            LinkedList<TreeNode<Integer>> stack = new LinkedList<TreeNode<Integer>>();
            //压入跟节点
            stack.push(root);
            while (!stack.isEmpty()){
                TreeNode<Integer> top = stack.pop();
                TreeNode<Integer> left = top.left;
                TreeNode<Integer> right = top.right;
                if (left != null){
                    stack.push(left);
                }
                if (right != null){
                    stack.push(right);
                }
                top.left = right;
                top.right = left;
            }
        }
        return root;
    }

    //BST树层次遍历
    public void printByLevel(TreeNode<Integer> root){
        if (root != null){
            //利用队列进行辅助操作
            LinkedList<TreeNode<Integer>>  queue = new LinkedList<TreeNode<Integer>>();
            queue.addLast(root);
            while (!queue.isEmpty()){
                TreeNode<Integer> node = queue.poll();
                System.out.print(node.getItem() + ",");
                TreeNode<Integer> left = node.left;
                TreeNode<Integer> right = node.right;
                if (left != null){
                    queue.addLast(left);
                }
                if (right != null){
                    queue.addLast(right);
                }
            }
        }
    }

    //树的深度递归
    public int getDepth(TreeNode<Integer> root){
        if (root == null){
            return 0;
        }
        int left = getDepth(root.getLeft());
        int right = getDepth(root.getRight());
        return 1 + Math.max(left, right);
    }

    //树的宽度, 使用队列进行层次遍历, 在上层遍历完成后, 下层的所有节点已经放入队列中, 此时队列中的元素个数就是下一层的宽度
    public int getWidth(TreeNode<Integer> root){
        if (root == null) return 0;
        LinkedList<TreeNode<Integer>> queue = new LinkedList<TreeNode<Integer>>();
        int maxWidth = 1;
        queue.addLast(root);
        while (true){
            //当前层的宽度
            int width = queue.size();
            if (width == 0){
                break;
            }
            while (width > 0){
                TreeNode<Integer> node = queue.poll();
                width--;
                if (node.getLeft() != null){
                    queue.addLast(node.left);
                }
                if (node.getRight() != null){
                    queue.addLast(node.right);
                }
            }
            maxWidth = Math.max(maxWidth, queue.size());
        }
        return maxWidth;
    }


    //测试
    public static void main(String[] args){
        BSTTree tree = new BSTTree();
        tree.root = tree.insert(tree.root, 4);
        tree.root = tree.insert(tree.root, 2);
        tree.root = tree.insert(tree.root, 6);
        tree.root = tree.insert(tree.root, 1);
        tree.root = tree.insert(tree.root, 3);
        tree.root = tree.insert(tree.root, 5);
        tree.root = tree.insert(tree.root, 7);
//        System.out.print("前序遍历:\t");
//        tree.preOrder(tree.root);
//        System.out.print("\n前序遍历(非递归):\t");
//        tree.preOrderWithoutRecursive(tree.root);
//        System.out.println();
//        System.out.print("中序遍历:\t");
//        tree.inOrder(tree.root);
//        System.out.print("\n中序遍历(非递归):\t");
//        tree.inOrderWithoutRecursive(tree.root);
//        System.out.println();
//        System.out.print("后序遍历:\t");
//        tree.postOrder(tree.root);
//        System.out.print("\n后序遍历(非递归):\t");
//        tree.postOrderWithoutRecursive(tree.root);
//        System.out.println();
//        tree.root = tree.mirrorRecursion(tree.root);
//        System.out.print("前序遍历:\t");
//        tree.preOrder(tree.root);
//        System.out.println();
//        System.out.print("中序遍历:\t");
//        tree.inOrder(tree.root);
//        System.out.println();
//        System.out.print("后序遍历:\t");
//        tree.postOrder(tree.root);
//        System.out.println();
        //测试树的深度
        System.out.println("递归查找树的深度:\t" + tree.hashCode() + "\t" + tree.getDepth(tree.getRoot()));
        System.out.println("BST的宽度:\t" + tree.hashCode() + "\t" + tree.getWidth(tree.root));


    }
}



