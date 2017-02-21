package com.huirong.weiruan100New.to40;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by nanhuirong on 16-9-13.
 */
/*
有一个很大很大的输入流，大到没有存储器可以将其存储下来，
而且只输入一次，如何从这个输入流中随机取得m个记录。
 */
public class Test38 {

    public static void main(String[] args){
        int[] array = new int[10];
        Test38.getNumbers(10, array);
        System.out.println(Arrays.toString(array));
    }
    public static void getNumbers(int m, int[] array){
        //对于前k条记录, 将其放入arr中, 对于后面的记录,
        for (int i = 0; i < m; i++){
            array[i] = m;
        }
        for (int i = m; i < Integer.MAX_VALUE; i++){
            Random random = new Random();
            int number = random.nextInt(i);
            if (number < m){
                array[number] = i;
            }
            if (i % 100000000 == 0){
                System.out.println(i);
            }
        }
    }
}
