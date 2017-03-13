package com.huirong.weiruan100;

import com.huirong.weiruan100.structure.ArrayTool;

/**
 * Created by nanhuirong on 16-9-2.
 */
public class test048 {
    public static void main(String[] args){
        ArrayTool arrayTool = new ArrayTool();
        int[] array = {4, 3, 2, 1, 6, 5};
        System.out.println(arrayTool.shiftBinarySearch(array, 3));
    }
}
