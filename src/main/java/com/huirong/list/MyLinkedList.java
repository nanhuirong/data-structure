package com.huirong.list;

import java.util.Stack;

/**
 * Created by huirong on 17-5-22.
 */
public class MyLinkedList {

    public Node addTail(Node head, int value){
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
        return head;
    }

    /**
     *  删除第一个含有某值
     * @param value
     */
    public void remove(Node head,int value){
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
    public void printReserveList1(Node head){
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
    private void printReserveList2(Node head){
        if (head != null){
            if (head.next != null){
                printReserveList2(head.next);
            }
            System.out.print(String.format("%d\t", head.value));
        }
    }

    public void printList(Node head){
        Node pointer = head;
        while (pointer != null){
            System.out.print(pointer.value + " ");
            pointer = pointer.next;
        }
    }

    /**
     * 给定单向链表的头指针和一个节点指针，在O（1）时间内删除该节点
     * 该方法必须考虑删除节点在链表中
     */
    public void deleteNode(Node head,Node node){
        if (head == null || node == null){
            return;
        }
        //要删除的节点不是尾部节点
        if (node.next != null){
            node.value = node.next.value;
            node.next = node.next.next;
        }else if (head == node){
            //删除节点是头节点
            head = null;
        }else {
            //删除节点是尾部节点
            Node pointer = head;
            while (pointer.next != node){
                pointer = pointer.next;
            }
            pointer.next = null;
        }
    }

    /**
     * 链表的倒数第k个节点
     *  引伸题目：
     *  求链表的中间节点
     *      定义两个指针，同时从头开始，第一个指针一次走一步，第二个指针一次走两步
     *  判断单链表是否存在环
     *      定义两个指针，从头开始，第一个指针一次走一步，第二个指针一次走两步，如果相遇则存在环
     *
     */
    public Node findKthToTail(Node head,int k){
        if (head == null || k <= 0){
            return null;
        }
        Node pointer1 = head, pointer2 = null;
        for (int i = 0; i < k - 1; i++){
            if (pointer1.next != null){
                pointer1 = pointer1.next;
            }else {
                return null;
            }
        }
        pointer2 = head;
        while (pointer1.next != null){
            pointer1 = pointer1.next;
            pointer2 = pointer2.next;
        }
        return pointer2;
    }

    /**
     * 反转链表
     */
    public void reserveList(Node head){
        if (head == null){
            return;
        }
        Node newHead = null, pointer = head, temp = null;
        while (pointer != null){
            temp = pointer.next;
            if (newHead == null){
                newHead = pointer;
                newHead.next = null;
            }else {
                pointer.next = newHead;
                newHead = pointer;
            }
            pointer = temp;
        }
        head = newHead;
    }

    /**
     * 升序链表合并
     */
    public Node merge(Node node1, Node node2){
        if (node1 == null){
            return node2;
        }else if (node2 == null){
            return node1;
        }
        Node mergeHead = null;
        if (node1.value < node2.value){
            mergeHead = node1;
            mergeHead.next = merge(node1.next, node2);
        }else {
            mergeHead = node2;
            mergeHead.next = merge(node1, node2.next);
        }
        return mergeHead;
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
        Node head = null;
        for (int i = 0; i < 10; i++){
            list.addTail(head, i);
        }
//        list.printList(head);
//        list.reserveList(head);
//        System.out.println();
//        list.printList(head);
        Node head1 = null;
        for (int i = 0; i < 10; i = i + 2){
            head1 = list.addTail(head1, i);
        }
        list.printList(head1);
        System.out.println();
        Node head2 = null;
        for (int i = 1; i < 10; i = i + 2){
            head2 = list.addTail(head2, i);
        }
        list.printList(head2);
        Node head3 = list.merge(head1, head2);
        System.out.println();
        list.printList(head3);

    }
}
