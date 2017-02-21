package com.huirong.weiruan100.tool;

/**
 * Created by nanhuirong on 16-8-28.
 */
public class Node<T>{
    public T item;
    public Node<T> next;

    public Node() {
    }

    public Node(T item, Node<T> next) {
        this.item = item;
        this.next = next;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}
