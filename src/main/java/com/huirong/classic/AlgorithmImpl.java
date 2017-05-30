package com.huirong.classic;

import java.util.Arrays;

/**
 * Created by huirong on 17-5-22.
 */
public class AlgorithmImpl {
    /**
     * 输出旋转数组的最小数字
     * {3, 4, 5, 1, 2} 是{1, 2, 3, 4, 5}的一个旋转，最小数字是1
     * 本题给的数组在一定程度上是排序的，因此可以尝试使用二分查找
     */
    public int minInOrder(int[] numbers){
        if (numbers == null || numbers.length <= 0){
            throw new RuntimeException("");
        }
        int start = 0, end = numbers.length - 1;
        //如果数组没有发生旋转，则第一个数字是最小的
        int index = start;
        while (numbers[start] >= numbers[end]){
            if (end - start == 1){
                index = end;
                break;
            }
            index = (start + end) / 2;
            //如果start、end、indexMin指向的三个元素相等，
            // 则无法判断，则只能用顺序查找
            if (numbers[index] == numbers[start] &&
                    numbers[index] == numbers[end]){
                return minInOrder(numbers, start, end);
            }
            if (numbers[start] >= numbers[index]){
                start = index;
            }else if (numbers[start] <= numbers[end]){
                end = index;
            }
        }
        return numbers[index];
    }

    private int minInOrder(int[] numbers, int start, int end){
        int result = numbers[start];
        for (int i = start + 1; i <= end; i++){
            if (result > numbers[i]){
                result = numbers[i];
            }
        }
        return result;
    }

    /**
     * 二进制中1出现的次数
     *  使用位运算
     *      注意：负数右移，首位补符号位1会陷入死循环
     *            所以在该解法中避免右移进而使用左移
     */
    public int numberOf1(int n){
        //该解法需要循环32次
//        int count = 0;
//        int flag = 1;
//        while (flag != 0){
//            if ((n & flag) == 1){
//                count++;
//            }
//            //不要用除法代替右移，效率比较低
//            flag = flag << 1;
//        }
//        return count;
        //有几个1循环几次
        int count = 0;
        while (n != 0){
            count++;
            n = (n - 1) & n;
        }
        return count;
    }

    /**
     * 实现浮点数的整数次乘法操作，不考虑大数问题
     *      考虑exponent >0 =0 <0 的情况
     *      考虑base为0的情况
     */
    public double power(double base, int exponent)throws Exception{
        if (Math.abs(base) < 0.0000000001 && exponent < 0){
            throw new Exception("");
        }
        int absExponent = Math.abs(exponent);
        double result = powerWithUnsigned(base, absExponent);
        if (exponent < 0){
            result = 1.0 / result;
        }
        return result;
    }

    private double powerWithUnsigned(double base, int exponent){
        if (exponent == 0){
            return 1;
        }
        if (exponent == 1){
            return base;
        }
        double result = powerWithUnsigned(base, exponent >> 1);
        result *= result;
        if ((exponent & 0x1) == 1){
            result *= base;
        }
        return result;
    }

    /**
     * 利用字符串解决大数问题（利用数组解决大数问题）
     * 打印出1到最大的n位数
     */
    public void printToMaxOfDigits(int n){
        if (n <= 0){
            return;
        }
        char[] array = new char[n];
        for (int i = 0; i < array.length; i++){
            array[i] = '0';
        }
        while (!increment(array)){
            printNumber(array);
        }
    }

    /**
     * 模拟大数加法
     */
    private boolean increment(char[] array){
        boolean isOverflow = false;
        int nTakeOver = 0;
        for (int i = array.length - 1; i >= 0; i--){
            int nSum = array[i] - '0' + nTakeOver;
            if (i == array.length - 1){
                nSum++;
            }
            if (nSum >= 10){
                //如果进位发生在第0位，则返回true
                if (i == 0){
                    isOverflow = true;
                }else {
                    nSum -= 10;
                    nTakeOver = 1;
                    array[i] = (char) ('0' + nSum);
                }
            }else {
                array[i] = (char)('0' + nSum);
                break;
            }
        }
        return isOverflow;
    }
    private void printNumber(char[] array){
        boolean isBeginning0 = true;
        for (int i = 0; i < array.length; i++){
            if (isBeginning0 && array[i] != '0'){
                isBeginning0 = false;
            }
            if (!isBeginning0){
                System.out.print(array[i]);
            }
        }
        System.out.print("\t");
    }

    public static void main(String[] args) {
        AlgorithmImpl test = new AlgorithmImpl();
//        test.printToMaxOfDigits(2);
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        test.reorderOddEven(array);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 调整数组顺序，使得奇数位于偶数前面
     */
    public void reorderOddEven(int[] array){
        if (array == null || array.length <= 0){
            return;
        }
        int head = 0, tail = array.length - 1;
        while (head < tail){
            //向后移动数据，直到遇上偶数
            while (head < tail && (array[head] & 0x1) != 0){
                head++;
            }
            //向前移动，直到遇上奇数
            while (head < tail && (array[tail] & 0x1) == 0){
                tail--;
            }
            if (head < tail){
                swap(array, head, tail);
            }
        }
    }



    private void swap(int[] array, int index1, int index2){
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    /**
     * 荷兰国旗问题
     *  三色0， 1， 2
     *  三个指针head，tail，current
     *      遇到1，current指针移动，不变动位置
     *      遇到0，current与head交换位置,begin++ current++
     *      遇到2，与end交换位置
     */
    public void control(int[] array)throws Exception{
        int head = 0, curreent = 0, tail = array.length - 1;
        while (curreent <= tail){
            if (array[curreent] == 0){
                swap(array, curreent, head);
                head++;
                curreent++;
            }else if (array[curreent] == 1){
                curreent++;
            }else if (array[curreent] == 2){
                swap(array, curreent, tail);
                tail--;
            }else {
                throw new Exception("");
            }
        }
    }


}
