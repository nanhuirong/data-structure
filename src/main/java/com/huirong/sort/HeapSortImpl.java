package com.huirong.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * Created by huirong on 17-3-12.
 * 作为一个优秀的算法,快速排序的性能一般优于堆排序
 * 堆的常见应用, 作为一个优先队列:最大优先队列和最小优先队列
 */
public class HeapSortImpl<E> {
    private final Comparator<? super E> comparator;

    public Comparator<? super E> getComparator() {
        return comparator;
    }

    public HeapSortImpl(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    //堆排序算法
    public void heapSort(E[] array){
        buildMaxHeap(array);
        int heapSize = array.length;
        while (heapSize >= 2){
            swap(array, heapSize - 1, 0);
            heapSize--;
            maxHeapIfy(array, 0, heapSize);
//            System.out.println(Arrays.toString(array));
        }
    }

    public void swap(E[] array, int srcInsex, int destIndex){
        E temp = array[srcInsex];
        array[srcInsex] = array[destIndex];
        array[destIndex] = temp;
    }

    //初始化大顶堆O(n)
    private void buildMaxHeap(E[] array){
        int parent = getParent(array.length - 1);
        System.out.println(parent);
        while (parent >= 0){
            maxHeapIfy(array, parent, array.length);
            parent--;
        }
//        System.out.println("初始化大顶堆\t" + Arrays.toString(array));
    }

    //调整, 大顶堆O(logn)
    public void maxHeapIfy(E[] array, int index, int heapSize){
        int left = getLeft(index);
        int right = getRight(index);
        int largest = index;
        if (left < heapSize && left >=0 && comparator.compare(array[left], array[largest]) > 0){
            largest = left;
        }
        if (right < heapSize && right >=0 && comparator.compare(array[right], array[largest]) > 0){
            largest = right;
        }
        if (largest != index){
//            System.out.println(largest + "\t" + index);
            swap(array, index, largest);
            maxHeapIfy(array, largest, heapSize);
        }
    }

    //获取左节点
    public int getLeft(int index){
        return 2 * index + 1;
    }

    //获取右节点
    public int getRight(int index){
        return 2 * index + 2;
    }

    //获取父节点
    public int getParent(int index){
        int result = (int)Math.ceil(index / 2.0) - 1;
        return result < 0 ? 0 : result;
    }

    public static void main(String[] args) {
        HeapSortImpl<Integer> demo = new HeapSortImpl<Integer>(new IntegerComparator());
        Integer[] array =new Integer[10];
        Random random = new Random();
        for (int i = 0; i < array.length; i++){
            array[i] = random.nextInt();
        }
        System.out.println(Arrays.toString(array));
        demo.heapSort(array);
        System.out.println(Arrays.toString(array));
//        System.out.println(demo.getParent(4));
    }



}
