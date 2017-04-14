package com.huirong.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Created by huirong on 17-3-14.
 * 快速排序实现类, 基于分治法实现
 * 关键在于寻找主元
 * 快速排序的性能取决于划分是否平衡, 划分平衡, 接近归并排序, 划分不均衡接近插入排序
 * 最坏情况下:当划分为分别包含n - 1 和 0个元素时, (此时数组可能已经有序) O(n*n)
 * 最好情况下:划分比较均衡 O(nlgn)
 * 快速排序的性能更接近于最好情况
 * 快速排序的改进: 改善主元
 *              1.随机化版本, 采用一种称为随机化抽样的方法, 随机选取一个元素与left(right)进行交换 期望运行时间O(nlgn)
 *              2.当数据几乎有序时, 插入排序很快,
 *                当快速排序的长度小于k时,不做任何排序并返回, 并在排序排序结束时调用插入排序O(nk + nlg(n/k))
 *              3.随机选取三个元素, 选取中位数作为主轴元素
 *
 *
 */
public class QuickSortImpl<E> {
    private Comparator<E> comparator;

    public QuickSortImpl(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public void quickSort(List<E> list, int lower, int upper){
        if (lower < upper){
            int index = partition(list, lower, upper);
            quickSort(list, lower, index - 1);
            quickSort(list, index + 1, upper);
        }
    }

    public void randomQuickSort(List<E> list, int lower, int upper, Random random){
        if (lower < upper){
            int index = randomPartition(list, lower, upper, random);
            randomQuickSort(list, lower, index - 1, random);
            randomQuickSort(list, index + 1, upper, random);
        }
    }
    //选取第一个元素作为枢轴元素 O(n)
    private int partition(List<E> list, int lower, int upper){
        E elem = list.get(lower);
        int left = lower;
        int right = upper;
        while (left < right){
            //从右往左递归
            while (this.comparator.compare(list.get(right),elem) > 0 && left < right) right--;
            if (left < right){
                list.set(left++, list.get(right));
                //从左往右查找
                while (this.comparator.compare(list.get(left), elem) < 0 && left < right) left++;
                if (left < right){
                    list.set(right--, list.get(left));
                }
            }
        }
        list.set(left, elem);
        return left;
    }

    //随机选取主元
    private int randomPartition(List<E> list, int lower, int upper, Random random){
        int randomIndex = (int) random.nextDouble() * list.size();
        swap(list, randomIndex, 0);
        return partition(list, lower, upper);
    }

    private void swap(List<E> list, int srcIndex, int destIndex){
        E elem= list.get(srcIndex);
        list.set(srcIndex, list.get(destIndex));
        list.set(destIndex, elem);
    }

    public static void main(String[] args) throws InterruptedException {
        IntegerComparator comparator = new IntegerComparator();
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(8);
        list.add(7);
        list.add(1);
        list.add(3);
        list.add(5);
        list.add(6);
        list.add(4);
        QuickSortImpl<Integer> demo = new QuickSortImpl<Integer>(comparator);
//        demo.quickSort(list, 0, list.size() - 1);
        Random random = new Random(System.currentTimeMillis());
//        demo.randomQuickSort(list, 0, list.size() - 1, random);
        long start, end;
        for (int i = 0; i < 100000000; i++){
            list.add(random.nextInt());
        }
        start = System.currentTimeMillis();
        demo.quickSort(list, 0, list.size() - 1);
        end = System.currentTimeMillis();
        System.out.println((end - start));
        list.clear();
        Thread.sleep(10000);
        for (int i = 0; i < 100000000; i++){
            list.add(random.nextInt());
        }
        start = System.currentTimeMillis();
        demo.randomQuickSort(list, 0, list.size() - 1, random);
        end = System.currentTimeMillis();

        System.out.println(end - start);
    }
}
