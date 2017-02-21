package com.huirong.weiruan100.sort;


import java.util.Arrays;

/**
 * Created by nanhuirong on 16-9-6.
 * 总结8大排序算法
 */
public class SortTool {

    public static void main(String[] args){
        int[] array = {49, 38, 65, 97, 76, 13, 27, 49, 55, 4};
        System.out.println(Arrays.toString(array));
        System.out.println("-----------------------------");
//        SortTool.shellSortPass(array, 5);
//        System.out.println(Arrays.toString(array));
//        SortTool.shellSortPass(array, 3);
//        System.out.println(Arrays.toString(array));
//        SortTool.shellSortPass(array, 1);
//        System.out.println(Arrays.toString(array));
//        SortTool.insertSort(array);
//        SortTool.shellSort(array);
//        SortTool.selectSort(array);
//        SortTool.binarySelectSort(array);
//        SortTool.heapSort(array);
//        SortTool.bubbleSort(array);
//        SortTool.bubbleSort1(array);
//        SortTool.bubbleSort2(array);
        SortTool.quickSort(array);
        System.out.println(Arrays.toString(array));
    }

    //创建随机数组
    public static int[] createArray(int arrayLen){
        int[] array = new int[arrayLen];
        int numberSize = arrayLen / 2;
        for (int i = 0; i < arrayLen; i++){
            array[i] = (int)(Math.random() * arrayLen);
        }
        return array;
    }

    //直接插入排序, 时间复杂度O(n), 空间复杂度O(1)
    public static void insertSort(int[] array){
        for (int i = 1; i < array.length; i++){
            int j = i - 1;
            int temp = array[i];
            while ( j >= 0 && temp < array[j]){
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = temp;
//            System.out.println("第" + i + "趟:\t" + Arrays.toString(array));
        }
    }

    //希尔排序, 依赖于步长的选取
    public static void shellSort(int[] array){
        int d = array.length / 2;
        while (d >= 1){
            shellSortPass(array, d);
            System.out.println("步长" + d + ":\t" + Arrays.toString(array));
            d = d / 2;
        }
    }
    //单步希尔排序, d 为步长, 若步长d = 1 为直接插入排序
    private static void shellSortPass(int[] array, int d){
        for (int i = d; i < array.length; i++){
            if (array[i] < array[i - d]){
                int j = i - d;
                int temp = array[i];
//                array[i] = array[j];
                while (j >= 0 && temp < array[j]){
                    array[j + d] = array[j];
                    j = j - d;
                }
                array[j + d] = temp;
            }
        }
    }


    //直接选择排序, 时间O(n^2), 空间O(1)
    public static void selectSort(int[] array){
        for (int i = 0; i < array.length; i++){
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++){
                //寻找当前最小元素的下标
                if (array[j] < array[minIndex]){
                    minIndex = j;
                }
            }
            if (minIndex != i){
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
    }

    //二元选择排序, 每次循环找出最大值与最小值, 将循环次数减少为原来的一半
    public static void binarySelectSort(int[] array){
        for (int i = 0; i <= array.length / 2; i++){
            int minIndex = i;
            int maxIndex = i;
            for (int j = i + 1; j < array.length - i; j++){
                if (array[j] < array[minIndex]){
                    minIndex = j;
                }
                if (array[j] > array[maxIndex]){
                    maxIndex = j;
                }
            }
            int temp;
            if (minIndex != i){
                temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
            if (maxIndex != i){
                temp = array[array.length - i - 1];
                array[array.length - i - 1]= array[maxIndex];
                array[maxIndex] = temp;
            }
        }
    }

    //堆排序, 针对直接选择排序的优化, 时间复杂度O(nlogn), 由于存在递归调用, 栈的深度为log(n) - n之间, 容易出现stackOverFlow
    //构建大顶堆
    public static void heapSort(int[] array){
        if (array == null || array.length <= 1){
            return;
        }
        //初始化堆
        buildHeap(array);
        for (int i = array.length - 1; i >= 1; i--){
            int temp = array[i];
            array[i] = array[0];
            array[0] = temp;
            adjustHeap(array, i, 0);
        }
    }
    //初始化堆
    private static void buildHeap(int[] array){
        if (array == null || array.length <= 1) return;
        //寻找第一个非叶子节点, 即最后一颗子树
        int half = array.length / 2 - 1;
        for (int i = half; i >= 0; i--){
            adjustHeap(array, array.length, i);
        }
    }
    //调整堆, heapSize为当前堆大小, root为需要调整的子树的跟节点
    private static void adjustHeap(int[] array, int heapSize, int root){
        //左子树
        int left = root * 2 + 1;
        //右子树
        int right = root * 2 + 2;
        int heapTop = root;
        if ( left < heapSize && array[heapTop] < array[left]){
            heapTop = left;
        }
        if (right < heapSize && array[heapTop] < array[right]){
            heapTop = right;
        }
        if (heapTop != root){
            int temp = array[root];
            array[root] = array[heapTop];
            array[heapTop] = temp;
            adjustHeap(array, heapSize, heapTop);
        }
    }

    //冒泡排序, 时间O(n^2), 空间O(1)
    public static void bubbleSort(int[] array){
        boolean exchange = true;
        for (int i = array.length - 1; i > 0 && exchange; i--){
            exchange = false;
            for (int j = 0; j < i; j++){
                if (array[j] > array[j + 1]){
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    if (!exchange){
                        exchange = true;
                    }
                }
            }
        }
    }

    //基于改进, 设置一个标记位, 记录上次冒泡最后交换的位置, 标记位后的元素无需进行移动
    public static void bubbleSort1(int[] array){
        int i = array.length - 1;
        while (i > 0){
            int pos = 0;
            for (int j = 0; j < i; j++){
                if (array[j] > array[j + 1]){
                    pos = j;
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            i = pos;
        }
    }

    //基于冒泡排序的改进, 每次进行正反两次冒泡, 找出一个最大值和最小值
    public static void bubbleSort2(int[] array){
        int low = 0;
        int high = array.length - 1;
        int lowPos, highPos;
        int j, temp;
        while (low < high){
            lowPos = 0;
            highPos = 0;
            for (j = low; j < high; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    highPos = j;
                }
            }
            high = highPos;
            for (j = high; j > low; j--){
                if (array[j] < array[j - 1]){
                    temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                    lowPos = j;
                }
            }
            low = lowPos;
        }
    }

    //快速排序, 时间O(nlogn), 空间O(1), 由于采用递归, 容易stackOverFlow
    public static void quickSort(int[] array){
        quickSortPass(array, 0, array.length - 1);
    }
    private static void quickSortPass(int[] array, int start, int end){
        //选取首元作为枢轴
        if (start >= end){
            return;
        }
        int temp = array[start];
        int i = start;
        int j = end;
        while (i < j){
            while (array[j] >= temp && i < j) j--;
            if (i < j){
                array[i++] = array[j];
                while (array[i] < temp && i < j) i++;
                if (i < j){
                    array[j--] = array[i];
                }
            }
        }
        //i =j 为找到的枢轴元素
        array[i] = temp;
        quickSortPass(array, start, i - 1);
        quickSortPass(array, i + 1, end);
    }


}
