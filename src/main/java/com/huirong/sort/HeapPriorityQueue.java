package com.huirong.sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by huirong on 17-3-14.
 */
public class HeapPriorityQueue<E> extends HeapSortImpl<E> {

    private final int length;
    private E[] array;
    public int size = 0;
    public HeapPriorityQueue(Comparator<? super E> comparator, int length) {
        super(comparator);
        this.length = length;
        this.array = (E[]) new Object[this.length];
    }

    //返回最大的元素
    public E getMax(){
        return this.array[0];
    }

    //出队操作 O(lgn)
    public E take(){
        if (size < 1){
            return null;
        }
        E max = array[1];
        array[1] = array[size - 1];
        array[size - 1] = null;
        size--;
        this.maxHeapIfy(array, 1, size);
        return max;
    }

    //入队操作
    public boolean put(E elem){
        if (size == length - 1){
            return false;
        }
        array[size] = elem;
        incrementPriority(size, elem);
        size++;
        return true;
    }

    //更新节点休闲级, O(logn)
    public void incrementPriority(int index, E elem){
        if (this.getComparator().compare(elem, array[index]) < 0){
            return;
        }
        array[index] = elem;
        while (index >= 0 && this.getComparator().compare(array[getParent(index)], array[index]) < 0) {
            swap(array, index, getParent(index));
            index = getParent(index);
        }
    }

    @Override
    public String toString() {
        return "HeapPriorityQueue{" +
                "array=" + Arrays.toString(array) +
                '}';
    }

    public static void main(String[] args) {
        IntegerComparator comparator = new IntegerComparator();
        HeapPriorityQueue queue = new HeapPriorityQueue(comparator, 15);
        queue.put(15);
        queue.put(13);
        queue.put(9);
        queue.put(5);
        queue.put(12);
        queue.put(8);
        queue.put(7);
        queue.put(4);
        queue.put(0);
        queue.put(6);
        System.out.println(queue);
    }








}
