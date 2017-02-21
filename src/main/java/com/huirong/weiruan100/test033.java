package com.huirong.weiruan100;

import java.util.Arrays;

/**
 * Created by nanhuirong on 16-8-30
 实现一个挺高级的字符匹配算法:
 给一串很长字符串,要求找到符合要求的字符串,例如目的串:123
 1******3***2 ,12*****3 这些都要找出来
 其实就是类似一些和谐系统。。。
 */
public class test033 {
    public static void main(String[] args){
        test033 demo = new test033();
        System.out.println(demo.findSubString("1******3***2", "123"));
        System.out.println(demo.findSubString("1******3***", "123"));

    }

    public boolean findSubString(String str, String subStr){
        //默认初始值是false
        boolean[] array = new boolean[256];
//        System.out.println(Arrays.toString(array));
        for (int i = 0; i < str.length(); i++){
            if (!array[i]){
                array[str.charAt(i)] = true;
            }
        }
        for (int i = 0; i < subStr.length(); i++){
            if (!array[subStr.charAt(i)]){
                return false;
            }
        }
        return true;
    }
}

