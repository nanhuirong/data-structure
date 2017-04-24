package com.huirong.algorithm.classic;

import com.huirong.sort.IntegerComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Created by huirong on 17-4-13.
 */
public class AlgorithmTest {
    public static void main(String[] args) {
        Comparator comparator = new IntegerComparator();
        AlgorithmTest test = new AlgorithmTest();
        Algorithm algorithm = new Algorithm(comparator);
        List array = test.generator(6);
        System.out.println(array);
        System.out.print(algorithm.randomSelect(array, (array.size() + 1) >> 1));


//        Algorithm algorithm = new Algorithm(comparator);
//        int pairs = algorithm.getInversion(array);
//        List<Integer>array = new ArrayList<Integer>();
//        array.add(13);
//        array.add(-3);
//        array.add(-25);
//        array.add(20);
//        array.add(-3);
//        array.add(-16);
//        array.add(-23);
//        array.add(18);
//        array.add(20);
//        array.add(-7);
//        array.add(12);
//        array.add(-5);
//        array.add(-22);
//        array.add(15);
//        array.add(-4);
//        array.add(7);
//        System.out.print(test.maxSubarray(array).sum);

    }

//    private static class IntegerComparator
//            implements Comparator<Integer> {
//        @Override
//        public int compare(Integer obj1, Integer obj2) {
//            return obj1.compareTo(obj2);
//        }
//    }

    /**
     * 随机产生数据
     * @param number
     * @return
     */
    private List<Integer> generator(int number){
        Random random = new Random(System.currentTimeMillis());
        long start = System.currentTimeMillis();
        List array = new ArrayList(number);
//        int thread = number / 10;
        int thread = number;
        for (int i = 0; i < number; i++){
            Integer num = random.nextInt(thread);
            array.add(num);
        }
        long end = System.currentTimeMillis() - start;
        System.out.println("产生[0, " + thread + ")的" + number + "个随机数花费时间" + end / 1000 + "s");
        return array;
    }

    /**
     * 寻找最大连续子数组（子数组和最大）
     * 算法思想：采用分治法，数组的连续子数组必然时下面三种情况
     *          1）完全位于A[low, middle]中，
     *          2）完全位于A[middle + 1, high]
     *          3）跨越中点
     */
    public Elem maxSubarray(List<Integer> array){
        return maxSubarray(array, 0, array.size() - 1);
    }

    public Elem maxSubarray(List<Integer> array, int start, int end){
        if (start == end){
            return new Elem(start, end, (int)array.get(start));
        }else {
            int middle = (start + end) >> 1;
            Elem leftElem = maxSubarray(array, start, middle);
            Elem rithElem = maxSubarray(array, middle + 1, end);
            Elem crossElem = maxSubarrayCross(array, start, middle, end);
            Elem elem = null;
            if (leftElem.sum > rithElem.sum){
                elem = leftElem;
            }else {
                elem =  rithElem;
            }
            if (elem.sum < crossElem.sum){
                elem = crossElem;
            }
            return elem;
        }
    }

    private Elem maxSubarrayCross(List<Integer> array, int start, int middle, int end){
        int leftIndex = middle, leftSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = middle; i >= start; i--){
            //这里做了一下转换
            sum += array.get(i);
            if (sum > leftSum){
                leftIndex = i;
                leftSum = sum;
            }
        }
        int rightIndex = middle + 1, rightSum = Integer.MIN_VALUE;
        sum = 0;
        for (int i = middle + 1; i <= end; i++){
            sum += array.get(i);
            if (sum > rightSum){
                rightIndex = i;
                rightSum = sum;
            }
        }
        Elem elem = new Elem(leftIndex, rightIndex, leftSum + rightSum);
        return elem;
    }

    public class Elem{
        public int start;
        public int end;
        public int sum;

        public Elem(int start, int end, int sum) {
            this.start = start;
            this.end = end;
            this.sum = sum;
        }
    }

    /**
     * 计数排序：需要提前知道数组的取值范围[0, k)，并且需要一个O(k)的临时空间和一个O(n)的排序空间，
     *          因为计数排序无法提供原址排序，存在很大程度的缺陷
     * @param array
     * @return
     */
    public List<Integer> countSort(List<Integer> array){
        return array;
    }





}
