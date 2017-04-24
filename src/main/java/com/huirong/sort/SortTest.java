package com.huirong.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Created by huirong on 17-4-12.
 */
public class SortTest {
    public static void main(String[] args) {
        SortTest test = new SortTest();
        Comparator comparator = new IntegerComparator();
        SortImpl sort = new SortImpl(comparator);

//        List array = test.generator(1000000);
//        sort.quickSort(array, false, true);
//        System.out.print(array);
//        long start = System.currentTimeMillis();
//        System.out.println(array);
////        sort.mergeSort(array);
//        sort.heapSort(array);
//        long end = System.currentTimeMillis();
//        System.out.println((end - start) / 1000 + "s," + array);
        test.test(50000000, 3);
    }

    public void test(int number, int iteration){
        Comparator comparator = new IntegerComparator();
        SortImpl sort = new SortImpl(comparator);
        SortTest test = new SortTest();
        List array;
        for (int i = 0; i < iteration; i++){
            long start;
            long end;
            //测试插入排序
//            start = System.currentTimeMillis();
//            array = test.generator(number);
//            sort.insertSort(array);
//            end = System.currentTimeMillis();
//            System.out.println("插入排序" + array.size() + " " + (end - start)/ 1000 + "s");
            //测试冒泡排序
//            array = test.generator(number);
//            start = System.currentTimeMillis();
//            sort.bubbleSort(array);
//            end = System.currentTimeMillis();
//            System.out.println("冒泡排序" + array.size() + " " + (end - start)/ 1000 + "s");
            //测试归并排序
            array = test.generator(number);
            start = System.currentTimeMillis();
            sort.mergeSort(array);
            end = System.currentTimeMillis();
            System.out.println("归并排序" + array.size() + " " + (end - start)/ 1000 + "s");
            //测试改进后的归并排序
            array = test.generator(number);
            start = System.currentTimeMillis();
            sort.mergeSort(array, 100);
            end = System.currentTimeMillis();
            System.out.println("改进归并排序" + array.size() + " " + (end - start)/ 1000 + "s");
            //测试堆排序
            array = test.generator(number);
            start = System.currentTimeMillis();
            sort.heapSort(array);
            end = System.currentTimeMillis();
            System.out.println("堆排序" + array.size() + " " + (end - start)/ 1000 + "s");
            //测试快速排序
            array = test.generator(number);
            start = System.currentTimeMillis();
            sort.quickSort(array);
            end = System.currentTimeMillis();
            System.out.println("快速排序" + array.size() + " " + (end - start)/ 1000 + "s");
            //测试随机化版本的快速排序
            array = test.generator(number);
            start = System.currentTimeMillis();
            sort.quickSort(array, true);
            end = System.currentTimeMillis();
            System.out.println("随机化版本快速排序" + array.size() + " " + (end - start)/ 1000 + "s");
            //测试随机化中卫数版本快速排序
            array = test.generator(number);
            start = System.currentTimeMillis();
            sort.quickSort(array, false, true);
            end = System.currentTimeMillis();
            System.out.println("随机化中位数快速排序" + array.size() + " " + (end - start)/ 1000 + "s");
            //测试插入版本的快速排序
            array = test.generator(number);
            start = System.currentTimeMillis();
            sort.quickSort(array, false, false, 100);
            end = System.currentTimeMillis();
            System.out.println("插入版本快速排序" + array.size() + " " + (end - start)/ 1000 + "s");
        }

    }

//    private static class IntegerComparator
//            implements Comparator<Integer>{
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
}
