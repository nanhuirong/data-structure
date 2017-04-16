package com.huirong.algorithm.classic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by huirong on 17-4-13.
 */
public class Algorithm<E> {
    private final Comparator<E> comparator;

    public Algorithm(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    private boolean order(E elem1, E elem2){
        return comparator.compare(elem1, elem2) > 0 ? true : false;
    }

    /**
     * 求解逆序对
     * 算法思路：利用归并排序（分治算法思想），在归并排序的过程中求解逆序数
     *          归并排序交换的次数就是逆序数的个数，求逆序对的过程就是归并排序的过程
     * @param array
     * @return
     */
    public int getInversion(List<E> array){
        return getInversion(array, 0, array.size() - 1);
    }

    private int getInversion(List<E> array, int start, int end){
        if (start >= end){
            return 0;
        }else {
            int middle = (start + end) >> 1;
            int left = getInversion(array, start, middle);
            int right = getInversion(array, middle + 1, end);
            int merge = merge(array, start, middle, end);
            return left + right + merge;
        }
    }

    private int merge(List<E> array, int start, int middle, int end){
        int count = 0;
        int leftLen = middle - start + 1, rightLen = end - middle;
        List<E> leftArr = new ArrayList<E>(leftLen);
        List<E> rightArr = new ArrayList<E>(rightLen);
        for (int i = start; i <= middle; i++){
            leftArr.add(array.get(i));
        }
        for (int i = middle + 1; i <= end; i++){
            rightArr.add(array.get(i));
        }
        int left = 0, right = 0, index = start;
        while (left < leftLen && right < rightLen){
            if (order(leftArr.get(left), rightArr.get(right))){
                array.set(index++, rightArr.get(right++));
                //此处容易出错
                count +=  middle + 1 - left;
            }else {
                array.set(index++, leftArr.get(left++));
            }
        }
        while (left < leftLen){
            array.set(index++, leftArr.get(left++));
        }
        while (right < rightLen){
            array.set(index++, rightArr.get(right++));
        }
        return count;
    }

    /**
     * 在O（n）的时间复杂度内寻找第k小的数, 选择排序的变种
     * @param array
     * @param k
     * @return
     */
    public E randomSelect(List<E> array, int k){
        return randomSelect(array, 0, array.size() - 1, k);
    }

    private E randomSelect(List<E> array, int start, int end, int k){
        if (start == end){
            return array.get(start);
        }
        int p = randomPartition(array, start, end);
        int leftLen = p - start + 1;
        if (k == leftLen){
            return array.get(p);
        }else if (k < leftLen){
            return randomSelect(array, start, p - 1, k);
        }else {
            return randomSelect(array, p + 1, end, k - leftLen);
        }
    }

    private int randomPartition(List<E> array, int start, int end){
        E elem = array.get(start);
        int left = start, right = end;
        while (left < right){
            while (order(array.get(right), elem)){
                right--;
            }
            if (left < right){
                array.set(left++, array.get(right));
                while (left < right && order(elem, array.get(left))){
                    left++;
                }
                if (left < right){
                    array.set(right--, array.get(left));
                }
            }
        }
        array.set(left, elem);
        return left;
    }

    private void swap(List<E> array, int index1, int index2){
        E elem = array.get(index1);
        array.set(index1, array.get(index2));
        array.set(index2, elem);
    }

}
