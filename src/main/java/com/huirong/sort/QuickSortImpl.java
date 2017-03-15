package com.huirong.sort;

import java.util.Comparator;
import java.util.List;

/**
 * Created by huirong on 17-3-14.
 * 快速排序实现类
 */
public class QuickSortImpl<E> {
    private List<E> list;
    private Comparator<E> comparator;

    public QuickSortImpl(List<E> list, Comparator<E> comparator) {
        this.list = list;
        this.comparator = comparator;
    }

    public void quickSort(List<E> list){

    }
}
