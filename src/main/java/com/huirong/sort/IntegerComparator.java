package com.huirong.sort;

import java.util.Comparator;

/**
 * Created by huirong on 17-3-12.
 */
public class IntegerComparator implements Comparator<Integer> {
    public int compare(Integer src, Integer dest) {
        return src.compareTo(dest);
    }
}
