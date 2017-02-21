package com.huirong.weiruan100New.tool;

import java.util.ArrayList;

/**
 * Created by nanhuirong on 16-9-12.
 */
public class ArrayTool {
    /*
    输入一个整形数组，数组里有正数也有负数。
    数组中连续的一个或多个整数组成一个子数组，每个子数组都有一个和。
    求所有子数组的和的最大值。要求时间复杂度为O(n)。
    例如输入的数组为1, -2, 3, 10, -4, 7, 2, -5，和最大的子数组为3, 10, -4, 7, 2，
    因此输出为该子数组的和18。
    */
    //1.暴力法:时间O(n^3)
    //本方法:时间O(n)
    public int getMaxSumSubArray(int[] array){
        int max = array[0];
//        int maxStartIndex, maxNumber = 0;
        int sum = 0;
//        int sumStartIndex, sumNumber = 0;
        for (int i = 0; i < array.length; i++){
            if (sum < 0){
                sum = array[i];
            }else {
//                sumNumber++;
                sum += array[i];
            }
            if (max < sum){
                max = sum;
            }
        }
        return max;
    }

    /*
    输入两个整数 n 和 m，从数列1，2，3.......n 中 随意取几个数,
    使其和等于 m ,要求将其中所有的可能组合列出来.
     */
    public void getTotalSumSequence(int[] array){

    }

    /*
    题目：输入一个已经按升序排序过的数组和一个数字，
    在数组中查找两个数，使得它们的和正好是输入的那个数字。
    要求时间复杂度是O(n)。如果有多对数字的和等于输入的数字，输出任意一对即可。
    例如输入数组1、2、4、7、11、15和数字15。由于4+11=15，因此输出4和11
     */
    public boolean getDoubleSum(int[] array, int sum){
        int low = 0, high = array.length - 1;
        while (low < high){
            if (array[low] + array[high] > sum){
                high--;
            }else if (array[low] + array[high] < sum){
                low++;
            }else{
                System.out.println("(" + array[low] + "," + array[high] + ")");
                return true;
            }
        }
        return false;
    }

    /*
    第18题（数组）：
    题目：n个数字（0,1,…,n-1）形成一个圆圈，从数字0开始，
    每次从这个圆圈中删除第m个数字（第一个为当前数字本身，第二个为当前数字的下一个数字）。
    当一个数字删除后，从被删除数字的下一个继续删除第m个数字。
    求出在这个圆圈中剩下的最后一个数字。
     */
    //约瑟夫问题
    public int Joseph(int n, int m){
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < n; i++){
            list.add(i);
        }
        int end = 0;
        while (list.size() > 1){
            end = (end + m - 1) % list.size();
            System.out.println("本轮被杀死的人:\t" + list.remove(end));
        }
        System.out.println("本次的幸运儿:" + list.get(0) + "(他\\她)将得到所有的宝藏");
        return list.get(0);
    }

    /*
    题目：定义Fibonacci数列如下：
    / 0 n=0
    f(n)= 1 n=1
     / f(n-1)+f(n-2) n=2
    输入n，用最快的方法求该数列的第n项。
    分析：在很多C语言教科书中讲到递归函数的时候，都会用Fibonacci作为例子。
    因此很多程序员对这道题的递归解法非常熟悉，但....呵呵，你知道的
     */
    //递归的写法很容易出现栈的溢出问题, 当n=46是java int的极限, 并且存在很多的数字重复计算, long 的极限是92
    public long FibonacciRecursion(long n){
        if (n == 0) return 0L;
        if (n == 1) return 1L;
        return FibonacciRecursion(n - 1) + FibonacciRecursion(n - 2);
    }
    //将递归的写法改为非递归的写法, 时间复杂度还是O(n), 本质没有发生改变
    public long Fibonacci(long n){
        if (n == 0) return 0L;
        if (n == 1) return 1L;
        long pointer = 1, pointerPre = 0;
        for (int i = 2; i <= n; i++){
            long sum = pointer + pointerPre;
            pointerPre = pointer;
            pointer = sum;
        }
        return pointer;
    }

    /*
    .跳台阶问题（递归）
    题目：一个台阶总共有n级，如果一次可以跳1级，也可以跳2级。
    求总共有多少总跳法，并分析算法的时间复杂度。
    这道题最近经常出现，包括MicroStrategy等比较重视算法的公司
    都曾先后选用过个这道题作为面试题或者笔试题。
     */
    //跳台阶问题, 一次跳2步, 1步
    public int DanceStepTwo(int n){
        if (n == 1){
            return 1;
        }
        if (n == 2){
            return 2;
        }
        int pre = 1;
        int prepre = 2;
        for (int i = 3; i <= n; i++){
            int sum = pre + prepre;
            prepre = pre;
            pre = sum;
        }
        return pre;
    }
    //一次可以跳1, 2, 3
    public int DanceStepThree(int n){
        if (n == 1){
            return 1;
        }
        if (n == 2){
            return 2;
        }
        if (n == 3){
            return 4;
        }
        int pre = 4;
        int prepre = 2;
        int preprepre = 1;
        for (int i = 4; i <= n; i++){
            int sum = pre + prepre + preprepre;
            preprepre = prepre;
            prepre = pre;
            pre = sum;
        }
        return pre;
    }

    /*
    题目：输入一个整数n，求从1到n这n个整数的十进制表示中1出现的次数。
    例如输入12，从1到12这些整数中包含1 的数字有1，10，11和12，1一共出现了5次
     */
    public int getArrayNumber(int n){
        int count = 0;
        for (int i = 1; i <= n; i++){
            count += getNumber(i);
        }
        return count;
    }
    //计算每一个数的1的个数
    private int getNumber(int number){
        int bit = number % 10;
        int count = 0;
        while (number != 0){
            if (bit == 1){
                count++;
            }
            number = number / 10;
            bit = number % 10;
        }
        return count;
    }

    /*
    有两个序列a,b，大小都为n,序列元素的值任意整数，无序；
    要求：通过交换a,b中的元素，使[序列a元素的和]与[序列b元素的和]之间的差最小。
    例如:
    var a=[100,99,98,1,2, 3];
    var b=[1, 2, 3, 4,5,40];
     */


    public static void main(String[] args){
        ArrayTool arrayTool = new ArrayTool();
        //测试最小连续子数组
//        int[] array = {1, -2, 3, 10, -4, 7, 2, -5};
//        System.out.println(arrayTool.getMaxSumSubArray(array));
//        int[] arrayThe = {-2, -3, -4, -5, -1};
//        System.out.println(arrayTool.getMaxSumSubArray(arrayThe));
        //测试升序数组是否存在两个数==sum
//        int[] array = {1, 2, 4, 7, 11, 15};
//        System.out.println(arrayTool.getDoubleSum(array, 20));
        //测试约瑟夫问题
//        arrayTool.Joseph(41, 3);
        //测试Fibonacci数列
//        System.out.println(Long.MAX_VALUE);
//        System.out.println(arrayTool.Fibonacci(92));
//        System.out.println(arrayTool.FibonacciRecursion(60));
        //测试条台阶问题, 二阶int的极限45, 三阶int的极限是36
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(arrayTool.DanceStepTwo(45));
//        for (int i = 10; i < 40; i++){
//            System.out.println(i + "\t" + arrayTool.DanceStepThree(i));
//        }
        //测试序列中1的个数
        System.out.println(arrayTool.getArrayNumber(12));
    }

}
