package com.huirong.weiruan100.sort;

import java.util.Arrays;

/**
 * Created by nanhuirong on 16-8-24.
 */
public class HeapSort {
    public static void main(String[] args){
        HeapSort demo = new HeapSort();
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        System.out.println("堆排序之前:\t" + Arrays.toString(array));
        demo.heapSort(array);
        System.out.println("堆排序之后:\t" + Arrays.toString(array));

    }

    public  void heapSort(int[] array){
        if (array == null || array.length <= 1){
            return;
        }
        //构建初始化大顶堆
//        System.out.println("-----------------");
        buildMaxHeap(array);
//        System.out.println("-----------------");
        for (int i = array.length - 1; i >= 1; i--){
            //将最后一个元素放入堆顶
            swap(array, 0, i);
            //调整堆的结构
            reHeap(array, i, 0);
        }
    }

    public void swap(int[] array, int index1, int index2){
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    //构建初始化大顶堆
    public void buildMaxHeap(int[] array){
        if (array == null || array.length <= 1){
            return;
        }
        int half = array.length / 2 - 1;
        for (int i = half; i >= 0; i --){
            reHeap(array, array.length, i);
        }
    }

    //调整堆的结构
    public void reHeap(int[] array, int heapSize, int index){
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        int largest = index;

        if (left < heapSize && array[left] > array[largest]){
            largest = left;
        }

        if (right < heapSize && array[right] > array[largest]){
            largest = right;
        }

        if (index != largest){
            swap(array, index, largest);
            reHeap(array, heapSize, largest);
        }

    }

}
