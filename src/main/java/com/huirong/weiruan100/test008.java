package com.huirong.weiruan100;


import weiruan100.tool.Node;

import java.util.Arrays;

/**
 * Created by nanhuirong on 16-8-28.
 用一种算法来颠倒一个链接表的顺序。
 */
public class test008 {

    public static void main(String[] args){
        test008 demo = new test008();
        Node<Integer>  head = null;
        head = demo.initLinkedList();
//        System.out.print((head == null)  + "\n");
//        System.out.println("-------------");
        demo.printLinkedList(head);
        head = demo.reverse(head);
        demo.printLinkedList(head);
        head = demo.reverseNorecurisve(head);
//        System.out.println(head== null);
        demo.printLinkedList(head);

        String str = "nanhuirong";
        System.out.println(demo.reverseString(str));


    }


    public Node<Integer> initLinkedList(){
        Node<Integer> head;
        Node<Integer> pointer = null;
        Node<Integer> temp = new Node<Integer>(1, null);
        head = temp;
        pointer = head;

        temp = new Node<Integer>(2, null);
        pointer.next = temp;
        pointer = pointer.next;
        temp = new Node<Integer>(3, null);
        pointer.next = temp;
        pointer = pointer.next;
        temp = new Node<Integer>(3, null);
        pointer.next = temp;
        pointer = pointer.next;
        temp = new Node<Integer>(4, null);
        pointer.next = temp;
        pointer = pointer.next;
        temp = new Node<Integer>(5, null);
        pointer.next = temp;
        pointer = pointer.next;
        temp = new Node<Integer>(6, null);
        pointer.next = temp;
        pointer = pointer.next;
        temp = new Node<Integer>(7, null);
        pointer.next = temp;
        pointer = pointer.next;
        temp = new Node<Integer>(8, null);
        pointer.next = temp;
        pointer = pointer.next;
        return head;
    }
    public void printLinkedList(Node<Integer> head){
        System.out.print("[");
        Node<Integer> pointer = head;
        while (pointer.next != null){
            System.out.print(pointer.item + "->");
            pointer = pointer.next;
        }
        System.out.print(pointer.item + "]\n");
    }

    //颠倒LinkedList的顺序, 递归
    public Node<Integer> reverse(Node<Integer> head){
        if (head == null){
            return null;
        }
        if (head.next == null){
            return head;
        }
        Node<Integer> pointer = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return pointer;
    }
    //颠倒LinkedList的顺序, 无递归
    public Node<Integer> reverseNorecurisve(Node<Integer> head){
        if (head == null){
            return null;
        }
        Node<Integer> pointer = head;
        Node<Integer> newHead = null;
        while (pointer != null){
            Node<Integer> temp = pointer;
            pointer = pointer.next;
            if (newHead == null){
                temp.next = null;
                newHead = temp;
            }else {
                temp.next = newHead;
                newHead = temp;
            }
        }
        return newHead;
    }

    //颠倒一个字符串
    public String reverseString(String str){
        char[] array = str.toCharArray();
        reverseStringWithLen(array, array.length);
//        System.out.println(Arrays.toString(array));
        return String.valueOf(array);
    }
    public void reverseStringWithLen(char[] array, int length){
        int head = 0;
        System.out.println(length);
        int tail = length - 1;
        while (head <= tail){
            char temp = array[head];
            array[head] = array[tail];
            array[tail] = temp;
            head++;
            tail--;
        }
    }


}
