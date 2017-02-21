package com.huirong.weiruan100.structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by nanhuirong on 16-8-30.
 */
public class ArrayTool {
    //输入两个整数n和m,从数列1,2,3，……n中随意取几个数，使其和等于m。要求将所有的可能组合列出来。
    public void findSum(int n, int m, LinkedList<Integer> list){
        if (n <= 0 || m <= 0){
            return;
        }
        list.push(n);
        if (m == n){
            System.out.println(list);
        }
        findSum(n - 1, m - n, list);
        list.pop();
        findSum(n - 1, m, list);
    }

    //一个数组由一个递减数组左移若干位形成, 如{4, 3, 2, 1, 6, 5}由{6, 5, 4, 3, 2, 1}左移两位形成
    //在这样的数组中查找一个数字
    public int shiftBinarySearch(int[] array, int key){
        int index = 0;
        for (int i = 1; i < array.length; i++){
            if (array[i] > array[i - 1]){
                index = i;
                break;
            }
        }
        int result = 0;
        if ((result = binarySearch(array, key, 0, index - 1)) != -1){
            return result;
        }
        if ((result = binarySearch(array, key, index, array.length - 1)) != -1){
            return result;
        }
        return -1;
    }



    //数组的二分查找, Arrays的binarySearch方法必须使用升序数组, 否则会失败
    public int binarySearch(int[] array, int key, int fromIndex, int toIndex){
//        return Arrays.binarySearch(array, key);
        //基于上述原因, 重新编写二分查找算法
        int low = fromIndex;
        int high = toIndex;
        while (low <= high){
            int middle = (low + high) >>> 1;
            int midVal = array[middle];
            if (key > midVal){
                high = middle - 1;
            }else if (key < midVal){
                low = middle + 1;
            }else {
                return middle;
            }
        }
        return -1;
    }

    //求500W以内的亲和数,
    public static void findTheNumber(int n, ArrayList<Integer> list){
        for (int i = 0; i <= n; i++){
            //1是所有数的真因数
            list.add(i, 1);
        }
        for (int i = 2; i + i <= n; i++){
            int j = i + i;
            while (j <= n){
                //在每一个i的倍数上+i
                list.set(j, list.get(j) + i);
                j = j + i;
            }
        }
        for (int i = 0; i <= n; i++){
            if (list.get(i) > i && list.get(i) <= n && list.get(list.get(i)) == i){
                System.out.print(i + "," + list.get(i) + "\n");
            }
        }
    }

    //找出数组中出现次数超过一半的数
    public static int findMaxCount(int[] array){
        int candidate = 0;
        int count = 0;
        for (int i = 0; i < array.length; i++){
            if (count == 0){
                candidate = array[i];
                count = 1;
            }else {
                if (candidate == array[i]){
                    count++;
                }else {
                    count--;
                }
            }
        }
        //主要是上述方法无法实现桥好是一半的问题
        count = 0;
        int candidate2 = array[array.length - 1];
        for (int arr: array){
            if (arr == candidate){
                count++;
            }
        }
        return count == (array.length / 2) ? candidate : candidate2;
//        return candidate;
    }





    public static void main(String[] args){
        ArrayTool arrayTool = new ArrayTool();
//        int[] array = {4, 3, 2, 1, 6, 5};
//        System.out.println(arrayTool.shiftBinarySearch(array, 3));
//        LinkedList<Integer> list = new LinkedList<>();
//        arrayTool.findSum(20, 8, list);
//        ArrayList<Integer> list = new ArrayList<Integer>();
//        arrayTool.findTheNumber(5000000, list);

        //测试查找数组中超过一半的数字
        int[] array = {0, 1, 2, 1};
        System.out.println(arrayTool.findMaxCount(array));
    }
}
