package com.huirong.weiruan100New.to30;

/**
 * Created by nanhuirong on 16-9-13.
 */
/*
题目：输入一个整数，求该整数的二进制表达中有多少个1。
例如输入10，由于其二进制表示为1010，有两个1，因此输出2。
 */
public class Test28 {
    public static void main(String[] args){
        System.out.println(Test28.binarySequenceNumber(-1));
    }
    //当n是正数时不存在问题, 每次右移一位
    public static int binarySequenceNumber(int n){
        int count = 0;
        while (n != 0){
            if ((n & 1) == 1){
                count++;
            }
            n = n >>> 1;
        }
        return count;
    }

}
