package com.huirong.weiruan100;

/**
 * Created by nanhuirong on 16-9-2.
 如何对 n 个数进行排序,要求时间复杂度 O(n),空间复杂度 O(1)
 */
public class test049 {
    public static void main(String[] args){
        test049 demo = new test049();
        int[] array = new int[10000];
        for (int i = 0; i < 10000; i++){
            array[i] = (int)(Math.random() * 100);
        }
        demo.Sort(array, true);
        System.out.println(array.length);
        for (int i = 0; i < array.length; i++){
            if (i % 5 == 0 && i != 0){
                System.out.println();
            }
            System.out.print(array[i] + "\t");
        }
    }

    //实现史上最牛逼的排序算法, 时间复杂度是O(n), 空间复杂度是O(1), 布尔值true代表升序
    public void Sort(int[] array, boolean flag){
        int[] arrayTemp = new int[array.length];
        for (int i = 0; i < array.length; i++){
            arrayTemp[array[i]]++;
        }
        if (flag){
            int index = 0;
            for (int i = 0; i < arrayTemp.length; i++){
                while (arrayTemp[i] != 0){
                    array[index ++] = i;
                    arrayTemp[i]--;
                }
            }
        }else {
            int index = array.length - 1;
            for (int i = 0; i < arrayTemp.length; i++){
                while (arrayTemp[i] != 0){
                    array[index --] = i;
                    arrayTemp[i]--;
                }
            }
        }

    }
}
