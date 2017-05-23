package com.huirong.classic;

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

}
