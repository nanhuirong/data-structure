package com.huirong.weiruan100;

import java.io.*;

/**
 * Created by nanhuirong on 16-7-29.
 1.把二元查找树转变成排序的双向链表
 题目：
 输入一棵二元查找树，将该二元查找树转换成一个排序的双向链表。
 要求不能创建任何新的结点，只调整指针的指向。
 10
 / \
 6  14
 / \ / \
 4  8 12 16
 转换成双向链表
 4=6=8=10=12=14=16。
/**
 * 二元查找树节点(双向链表节点)
 */
class BSNode implements Serializable{
    private static final long serialVersionUID = 6136767364555910395L;
    private int m_value;
    private BSNode m_pLeft;
    private BSNode mp_Right;

    public BSNode(int m_value) {
        this.m_value = m_value;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getM_value() {
        return m_value;
    }

    public void setM_value(int m_value) {
        this.m_value = m_value;
    }

    public BSNode getM_pLeft() {
        return m_pLeft;
    }

    public void setM_pLeft(BSNode m_pLeft) {
        this.m_pLeft = m_pLeft;
    }

    public BSNode getMp_Right() {
        return mp_Right;
    }

    public void setMp_Right(BSNode mp_Right) {
        this.mp_Right = mp_Right;
    }
}

/**
 * 双向链表
 */

class BSDoubleList{
    private BSNode head;

    public BSNode getHead() {
        return head;
    }

    public void setHead(BSNode head) {
        this.head = head;
    }

    public synchronized void print(){
        if (this.head != null){
            System.out.println("HashCode:\t" + this.hashCode() + "\t链表正向:");
            while (true){
                if (this.head.getMp_Right() != null){
                    System.out.print(this.head.getMp_Right().getM_value() + " ");
                    this.head = this.head.getMp_Right();
                }else {
                    break;
                }
            }
            System.out.println();
            System.out.println("HashCode:\t" + this.hashCode() + "\t链表逆向:");
            while (true){
                if (this.head != null){
                    System.out.print(this.head.getM_value() + " ");
                    this.head = this.head.getM_pLeft();
                }else {
                    break;
                }
            }
        }else {
            System.out.println("HashCode:\t" + this.hashCode() + "\t空链表:\n");
        }
    }
}

class BSTree implements Cloneable, Serializable{
    private static final long serialVersionUID = -7240326774488306261L;
    private BSNode m_root; //跟节点
    private BSNode tempListNode;//当前扫描节点的前驱
    private BSNode tempListHead;

    public BSNode getM_root() {
        return m_root;
    }

    public void setM_root(BSNode m_root) {
        this.m_root = m_root;
    }

    /**
     * 深拷贝
     */
    public Object clone(){
        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            oos.close();
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            Object obj = ois.readObject();
            ois.close();
            return (BSTree) obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //增加字节点
    public synchronized void addNode(BSNode node){
        if (this.m_root == null){
            this.m_root = node;
            return;
        }
        BSNode tempNode = this.m_root;
        while (true){
            if (node.getM_value() > tempNode.getM_value()){
                //大于当前节点
                if (tempNode.getMp_Right() == null){
                    tempNode.setMp_Right(node);
                    return;
                }else {
                    tempNode = tempNode.getMp_Right();
                    continue;
                }
            }else if (node.getM_value() < tempNode.getM_value()){
                if (tempNode.getM_pLeft() == null){
                    tempNode.setM_pLeft(node);
                    return;
                }else {
                    tempNode = tempNode.getM_pLeft();
                    continue;
                }
            }else {
                //等于父节点
                return;
            }
        }
    }

    //生成双向链表
    public synchronized BSDoubleList toDoubleList(){
        BSTree tempTree = (BSTree) this.clone(); // 临时树, 垃圾回收
        if (null != tempTree){
            changeTreeToDoubleList(tempTree.m_root);
        }
        BSDoubleList list = new BSDoubleList();
        list.setHead(tempListHead);
        return list;
    }
    private void changeTreeToDoubleList(BSNode node){
        if (null == node){
            return;
        }
        if (null != node.getM_pLeft()){
            changeTreeToDoubleList(node.getM_pLeft());
        }
        //转换,
        node.setM_pLeft(tempListNode);
        //如果当前temp的节点为空, 证明当前节点为表头
        if (null == tempListNode){
            tempListHead = node;
        }else {
            tempListNode.setMp_Right(node);
        }
        tempListNode = node;
        if (null != node.getMp_Right()){
            changeTreeToDoubleList(node.getMp_Right());
        }
    }


    /**
     * 打印中序遍历
     */
    public synchronized void print(){
        if (this.m_root == null){
            System.out.println("HashCode:\t" + this.hashCode() + "\t空树");
            return;
        }
        System.out.print("HashCode:\t" + this.hashCode() + "树");
        print(this.m_root);
        System.out.println("--------------------");
    }

    public void print(BSNode node){
        if (node != null){
            print(node.getM_pLeft());
            System.out.println(node.getM_value() + "\t");
            print(node.getMp_Right());
        }
    }

}

public class test001 {
    public static void main(String[] args){
        BSTree tree = new BSTree();
        tree.addNode(new BSNode(10));
        tree.addNode(new BSNode(6));
        tree.addNode(new BSNode(14));
        tree.addNode(new BSNode(4));
        tree.addNode(new BSNode(8));
        tree.addNode(new BSNode(12));
        tree.addNode(new BSNode(16));
        tree.print();
        BSDoubleList list = tree.toDoubleList();
        list.print();
        System.out.println("原来的树");
        tree.print();
    }
}
