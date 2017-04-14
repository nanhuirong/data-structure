package com.huirong.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Created by huirong on 17-4-12.
 */
public class SortImpl <E>{


    private final Comparator<E> comparator;
    private final Random random = new Random(System.currentTimeMillis());
    //默认升序
    private boolean order;

    public SortImpl(Comparator<E> comparator) {
        this(comparator, true);
    }

    public SortImpl(Comparator<E> comparator, boolean order) {
        this.comparator = comparator;
        this.order = order;
    }

    public void setOrder(boolean order){
        this.order = order;
    }

    /**
     * 插入排序
     * 算法思想：选择初始化有序区，每次往有序区填入一个元素
     */
    public void insertSort(List<E> array){
        insertSort1(array, 0, array.size());
    }

    private void insertSort1(List<E> array, int start, int size){
        for (int i = start + 1; i < start + size; i++){
            E elem = array.get(i);
            int j;
            for (j = i - 1; j >= start && order(array.get(j), elem); j--){
                array.set(j + 1, array.get(j));
            }
            array.set(j + 1, elem);
        }
    }

    /**
     * 归并排序，采用分治法思想
     * 算法改进：在归并排序的固定长度的小组内进行插入排序假设长度为k，则时间复杂度O(nk + nlog(n/k))
     * @param array
     * @param k 是否采用插入算法的k值选取
     */

    public void mergeSort(List<E> array, int k){
        mergeSort(array, 0, array.size() - 1, k);
    }

    public void mergeSort(List<E> array){
        mergeSort(array, 0);
    }


    private void mergeSort(List<E> array, int start, int end, int k){
        if (start < end){
            int middle = (int)Math.floor((start + end) / 2.0);
            int left = middle - start + 1, right = end - middle;
            if (left < k){
                insertSort1(array, start, left);
            }else {
                mergeSort(array, start, middle, k);
            }
            if (right < k){
                insertSort1(array, middle + 1, right);
            }else {
                mergeSort(array, middle + 1, end, k);
            }
            merge(array, start, middle, end);
        }
    }


    /**
     * 将左右两个有序堆合并
     * @param array
     * @param start
     * @param end  元素个数 - 1
     * @param middle
     */
    private void merge(List<E> array, int start, int middle, int end){
        int leftLen = middle - start + 1, rightLen = end - middle;
        List<E> leftArray = new ArrayList(leftLen);
        List<E> rightArray = new ArrayList(rightLen);
        for (int i = start ; i <= middle; i++){
            leftArray.add(array.get(i));
        }
        for (int i = middle + 1; i <= end; i++){
            rightArray.add(array.get(i));
        }
        int left = 0 , right = 0;
        int index = start;
        while (left < leftLen && right < rightLen){
            if (order(leftArray.get(left), rightArray.get(right))){
                array.set(index++, rightArray.get(right++));
            }else{
                array.set(index++, leftArray.get(left++));
            }
        }
        while (left < leftLen){
            array.set(index++, leftArray.get(left++));
        }
        while (right < rightLen){
            array.set(index++, rightArray.get(right++));
        }
    }



    /**
     * 冒泡排序
     * @param array
     */
    public void bubbleSort(List<E> array){
        for (int i = 0; i < array.size() - 1; i++){
            for (int j = array.size() - 1; j >= i + 1; j--){
                if (order(array.get(j - 1), array.get(j))){
                    swap(array, j , j - 1);
                }
            }
        }
    }

    /**
     * 堆排序，我们目前能找到的两种最优的排序算法之一（具备空间原址性和低时间复杂度，另外一种时快速排序）
     * 算法思想：包括堆的构建和堆的调整两块内容
     * @param array
     */
    public void heapSort(List<E> array){
        int index = array.size() - 1, heapSize = array.size();
        //初始化堆的构建，时间复杂度O(n)
        buildHeap(array, heapSize);
        for (; index >= 0; index--){
            swap(array, index, 0);
            //堆调整O(logn)
            heapModify(array, 0, --heapSize);
        }
    }

    private void buildHeap(List<E> array, int heapSize){
        int middle = (heapSize - 1) >> 1;
        for (; middle >= 0; middle--){
            heapModify(array, middle, heapSize);
        }
    }

    private void heapModify(List<E> array, int root, int heapSize){
        int left = getLeft(root), right = getRight(root);
        int largest = root;
        if (left < heapSize && order(array.get(left), array.get(root))){
            largest = left;
        }
        if (right < heapSize && order(array.get(right), array.get(largest))){
            largest = right;
        }
        if (largest != root){
            swap(array, root, largest);
            heapModify(array, largest, heapSize);
        }

    }

    /**
     * 快速排序，基于比较性能最好的排序算法
     * 算法思想：分治,分区平衡时接近归并排序，分区不平衡时接近插入排序
     * 算法改进：改进主元的选取
     *          1）随机选取主元素
     *          2）(当数据几乎有序的适合插入排序很快，)
     *          当快速排序的长度小于k时,不做任何排序并返回,
     *          并在排序排序结束时调用插入排序O(nk + nlg(n/k))
     *          3）随机选取三个元素, 选取中位数作为主轴元素
     * @param array
     */
    public void quickSort(List<E> array){
        quickSort(array, 0, array.size() - 1);
    }

    private void quickSort(List<E> array, int start, int end){
        if (start < end){
            //选取枢轴元素
            int partition = partition(array, start, end);
            quickSort(array, start, partition - 1);
            quickSort(array, partition + 1, end);
        }
    }

    //分区算法的性能是快速排序性能的关键
    private int partition(List<E> array, int start, int end){
        int left = start, right = end;
        E elem = array.get(start);
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

    private int getLeft(int index){
        return index * 2 + 1;
    }

    private int getRight(int index){
        return index * 2 + 2;
    }

    private int getParent(int index){
        return index >> 1;
    }



    private boolean order(E elem1, E elem2){
        if (order){
            return comparator.compare(elem1, elem2) > 0 ? true : false;
        }else {
            return comparator.compare(elem2, elem1) > 0 ? true : false;
        }
    }

    private void swap(List<E> array, int index1, int index2){
        E elem = array.get(index1);
        array.set(index1, array.get(index2));
        array.set(index2, elem);
    }

//    private  class SortComparator implements Comparator<E>{
//        @Override
//        public int compare(E obj1, E obj2) {
//            return obj1
//        }
//    }
}
