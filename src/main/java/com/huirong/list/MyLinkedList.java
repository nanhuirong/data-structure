package com.huirong.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by huirong on 17-5-22.
 */
public class MyLinkedList {
    private Node head;

    public void addTail(int value){
        Node node = new Node(value);
        if (head == null){
            head = node;
        }else {
            Node pointer = head;
            while (pointer.next != null){
                pointer = pointer.next;
            }
            pointer.next = node;
        }
    }

    //删除第一个含有某值的节点
    public void remove(int value){
        if (head == null){
            return;
        }
        Node temp = null;
        if (head.value == value){
            temp = head;
            head = head.next;
        }else {
            Node pointer = head;
            while (pointer.next != null && pointer.next.value != value){
                pointer = pointer.next;
            }
            if (pointer.next != null && pointer.next.value == value){
                temp = pointer.next;
                pointer.next = pointer.next.next;
            }
        }
        if (temp != null){
            temp = null;
        }
    }

    /**
     * 逆向输出链表
     * 1.利用栈实现该功能，后进先出，鲁棒性更好
     * 2.利用递归实现，问题是当链表很长是容易出现栈溢出
     */
    public void printList(){
        //递归
//        printList(head);
        //利用栈实现
        Stack<Node> stack = new Stack();
        Node pointer = head;
        while (pointer != null){
            stack.push(pointer);
            pointer = pointer.next;
        }
        while (!stack.isEmpty()){
            pointer = stack.pop();
            System.out.print(String.format("%d\t", pointer.value));
        }
    }
    private void printList(Node node){
        if (node != null){
            if (node.next != null){
                printList(node.next);
            }
            System.out.println(String.format("%d\t", node.value));
        }
    }

    //单向链表
    private class Node{
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    public static void main(String[] args){
        MyLinkedList list = new MyLinkedList();
        for (int i = 0; i < 10; i++){
            list.addTail(i);
        }
        list.remove(0);
        list.remove(9);
        list.remove(5);
        list.printList();

    }
}
