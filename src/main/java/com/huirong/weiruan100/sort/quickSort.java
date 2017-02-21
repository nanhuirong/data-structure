package com.huirong.weiruan100.sort;

import java.util.Arrays;

/**
 * Created by nanhuirong on 16-9-5.
 * 深度解析快速排序算法
 */
public class quickSort {
    public static void main(String[] args){
        quickSort sort = new quickSort();
        for (int i = 0; i < 10; i++)
        sort.testTime();
//        int arrayLen = 10000000;
//        int[] array = new int[arrayLen];
//        sort.addAll(array, arrayLen);
////        System.out.println(Arrays.toString(array));
//
//        sort.sortVersion1(array, 0, array.length - 1);
////        System.out.println(Arrays.toString(array));
    }

    public void testTime(){
        //测试时间
        int[] numbers = {100000, 1000000, 10000000, 100000000};
        for (int i = 0; i < numbers.length; i++){
            int[] array = new int[numbers[i]];
            addAll(array, array.length);
            long start = System.currentTimeMillis();
            sortVersion1(array, 0, array.length - 1);
            long end = System.currentTimeMillis();
            System.out.println("快速排序版本一:" + array.length / 10000 + "W个数\t耗时:" + (end - start) + "ms");
//            System.gc();
        }

    }

    //填充元素
    public void addAll(int[] array, int arrayLen){
        int number = arrayLen / 2;
        for (int i = 0; i < arrayLen; i++){
            int random = (int)(Math.random() * number);
            array[i] = random;
        }
    }

    //算法的优化主要在于枢轴元素的选取
    public void sortVersion1(int[] array, int start, int end){
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
        sortVersion1(array, start, i - 1);
        sortVersion1(array, i + 1, end);
    }
}
