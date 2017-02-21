package com.huirong.weiruan100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by nanhuirong on 16-8-30.
 有两个序列 a,b,大小都为 n,序列元素的值任意整数,无序
 要求:通过交换 a,b 中的元素,使[序列 a 元素的和]与[序列 b 元素的和]之间的差最小。
 例如:
 var a=[100,99,98,1,2, 3]
 var b=[1, 2, 3, 4,5,40]

 */
public class test032 {
    public static void main(String[] args){
        test032 demo = new test032();
        int[] arrayA = {1, 2, 3, 4, 5};
        int[] arrayB = {6, 7, 8, 10, 9};
        demo.findMinSum(arrayA, arrayB);

    }

    //
    public void findMinSum(int[] arrayA, int[] arrayB){
        //做法先将两个数组合并排序
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < arrayA.length; i++){
            list.add(arrayA[i]);
            list.add(arrayB[i]);
        }
        //默认是升序
        Collections.sort(list);
//        System.out.println(list);
        boolean flag = true;
        int index = list.size() - 1;
        int indexA = arrayA.length - 1;
        int indexB = arrayB.length - 1;
        while (index >= 0){
            int max = list.get(index--);
            int min = list.get(index--);
            if(flag){
                arrayA[indexA--] = max;
                arrayB[indexB--] = min;
                flag = false;
            }else {
                arrayA[indexA--] = min;
                arrayB[indexB--] = max;
                flag = true;
            }
        }
        System.out.print(Arrays.toString(arrayA) + "\n" + Arrays.toString(arrayB));
    }
}
