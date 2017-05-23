package com.huirong.sort;

/**
 * Created by huirong on 17-5-22.
 */
public class Sort {

    /**
     * 计数排序
     * 时间复杂度O(n)
     */
    public void sortAges(int[] ages, int length){
        if (ages == null || ages.length <= 0){
            return;
        }
        int oldestAge = 99;
        int[] timesOfAge = new int[oldestAge + 1];
        for (int i = 0; i < timesOfAge.length; i++){
            timesOfAge[i] = 0;
        }
        for (int i = 0; i < ages.length; i++){
            int age = ages[i];
            if (age < 0 || age > oldestAge){
                throw new RuntimeException("年龄超出范围[0, 99]");
            }
            ++timesOfAge[age];
        }
        int index = 0;
        for (int i = 0; i < timesOfAge.length; i++){
            for (int j = 0; j < timesOfAge[i]; j++){
                ages[index++] = i;
            }
        }
    }
}
