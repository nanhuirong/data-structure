package com.huirong.algorithm.classic;

import com.huirong.sort.IntegerComparator;

import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huirong on 17-4-13.
 */
public class AlgorithmTest {
    public static void main(String[] args) {
        Comparator comparator = new IntegerComparator();
        AlgorithmTest test = new AlgorithmTest();
        Algorithm algorithm = new Algorithm(comparator);
//        List array = test.generator(6);
//        System.out.println(array);
//        System.out.print(algorithm.randomSelect(array, (array.size() + 1) >> 1));


//        Algorithm algorithm = new Algorithm(comparator);
//        int pairs = algorithm.getInversion(array);
//        List<Integer>array = new ArrayList<Integer>();
//        array.add(13);
//        array.add(-3);
//        array.add(-25);
//        array.add(20);
//        array.add(-3);
//        array.add(-16);
//        array.add(-23);
//        array.add(18);
//        array.add(20);
//        array.add(-7);
//        array.add(12);
//        array.add(-5);
//        array.add(-22);
//        array.add(15);
//        array.add(-4);
//        array.add(7);
//        System.out.print(test.maxSubarray(array).sum);

//        test.getCoinNum(100);
//        String str = "100-trade-done";
//        String p = "1*trade*done".replace("?*", "[\\s\\S]+")
//                .replace("?", "[\\s\\S]")
//                .replace("*", "[\\s\\S]*");
//        System.out.println(p);
//        Pattern pattern = Pattern.compile(p);
//        System.out.println(pattern.matcher(str).matches());
        test.getLIS();

    }

//    private static class IntegerComparator
//            implements Comparator<Integer> {
//        @Override
//        public int compare(Integer obj1, Integer obj2) {
//            return obj1.compareTo(obj2);
//        }
//    }

    /**
     * 随机产生数据
     * @param number
     * @return
     */
    private List<Integer> generator(int number){
        Random random = new Random(System.currentTimeMillis());
        long start = System.currentTimeMillis();
        List array = new ArrayList(number);
//        int thread = number / 10;
        int thread = number;
        for (int i = 0; i < number; i++){
            Integer num = random.nextInt(thread);
            array.add(num);
        }
        long end = System.currentTimeMillis() - start;
        System.out.println("产生[0, " + thread + ")的" + number + "个随机数花费时间" + end / 1000 + "s");
        return array;
    }

    private int[] generatorArray(int number){
        int[] array = new int[number];
        Random random = new Random(System.currentTimeMillis());
        long start = System.currentTimeMillis();
        int thread = number;
        for (int i = 0; i < number; i++){
            int num = random.nextInt(thread);
            array[i] = num;
        }
        long end = System.currentTimeMillis() - start;
        System.out.println("产生[0, " + thread + ")的" + number + "个随机数花费时间" + end / 1000 + "s");
        return array;
    }



    /**
     * 寻找最大连续子数组（子数组和最大）
     * 算法思想：采用分治法，数组的连续子数组必然时下面三种情况
     *          1）完全位于A[low, middle]中，
     *          2）完全位于A[middle + 1, high]
     *          3）跨越中点
     */
    public Elem maxSubarray(List<Integer> array){
        return maxSubarray(array, 0, array.size() - 1);
    }

    public Elem maxSubarray(List<Integer> array, int start, int end){
        if (start == end){
            return new Elem(start, end, (int)array.get(start));
        }else {
            int middle = (start + end) >> 1;
            Elem leftElem = maxSubarray(array, start, middle);
            Elem rithElem = maxSubarray(array, middle + 1, end);
            Elem crossElem = maxSubarrayCross(array, start, middle, end);
            Elem elem = null;
            if (leftElem.sum > rithElem.sum){
                elem = leftElem;
            }else {
                elem =  rithElem;
            }
            if (elem.sum < crossElem.sum){
                elem = crossElem;
            }
            return elem;
        }
    }

    private Elem maxSubarrayCross(List<Integer> array, int start, int middle, int end){
        int leftIndex = middle, leftSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = middle; i >= start; i--){
            //这里做了一下转换
            sum += array.get(i);
            if (sum > leftSum){
                leftIndex = i;
                leftSum = sum;
            }
        }
        int rightIndex = middle + 1, rightSum = Integer.MIN_VALUE;
        sum = 0;
        for (int i = middle + 1; i <= end; i++){
            sum += array.get(i);
            if (sum > rightSum){
                rightIndex = i;
                rightSum = sum;
            }
        }
        Elem elem = new Elem(leftIndex, rightIndex, leftSum + rightSum);
        return elem;
    }

    public class Elem{
        public int start;
        public int end;
        public int sum;

        public Elem(int start, int end, int sum) {
            this.start = start;
            this.end = end;
            this.sum = sum;
        }
    }

//    /**
//     * 计数排序：需要提前知道数组的取值范围[0, k)，并且需要一个O(k)的临时空间和一个O(n)的排序空间，
//     *          因为计数排序无法提供原址排序，存在很大程度的缺陷
//     * @param array
//     * @return
//     */
//    public List<Integer> countSort(List<Integer> array){
//        return array;
//    }

    /**
     * 钢条切割问题:采用传统的递归解法，会重复计算子问题
     * @param length
     * @return
     */
    public int cut(int length){
        int[] p = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        return cut(p, length);
    }

    private int cut(int[] p, int length){
        if (length == 0){
            return 0;
        }
        int q = Integer.MIN_VALUE;
        for (int i = 1; i <= length; i++){
            q = Math.max(q, p[i - 1] + cut(p, length - i));
        }
        return q;
    }

    /**
     * 动态规划
     * 选择硬币问题
     * 状态转移方程：d[i] = min{d[u - vj] + 1}, i - vj >= 0,vj为硬币的面值
     * 输出最优解：通过记录加入的硬币（1, 3, 5）以及上一个最优解
     */
    public void getCoinNum(int total){
        int[] base = {1, 3, 5};
        int[] dp = new int[total + 1];
        class CoinElem{
            public CoinElem(int pre, int value) {
                this.pre = pre;
                this.value = value;
            }

            int pre;
            int value;

            public void setPre(int pre) {
                this.pre = pre;
            }

            public void setValue(int value) {
                this.value = value;
            }

            @Override
            public String toString() {
                return value + "(" + pre + ")";
            }
        }
        CoinElem[] solution = new CoinElem[total + 1];
        for (int i = 0; i <= total; i++){
            dp[i] = i;
            solution[i] = new CoinElem(-1, -1);
            for (int j = 0; j < base.length; j++){
                int flag = i - base[j];
                if (flag >= 0 && dp[i] > dp[flag] + 1){
                    dp[i] = dp[flag] + 1;
                    solution[i].setPre(flag);
                    solution[i].setValue(base[j]);
                }
            }
        }
        //输出最优解
        int[] result = new int[base.length];
        for (int i = 0; i <= total; i++){
            System.out.println(i + "\t" + dp[i] + "\t" + solution[i]);
        }
    }

    /**
     * 求最长非降序序列的长度
     * 时间复杂度O(n*n)
     * 状态方程 ：dp[i] = max{1, dp[j] + 1} 其中j < i && A[j] <= A[i]
     * 输出最优解：保存每一步的上一步记录
     *
     * 存在一种O(n*logn)的解法
     */
    public void getLIS(){
        int[] array = generatorArray(10);
        int[] dp = new int[array.length];
        int[] result = new int[array.length];
        int max = 1;
        for (int i = 0; i < array.length; i++){
            dp[i] = 1;
            result[i] = i;
            for (int j = 0; j < i; j++){
                if (array[j] <= array[i] && dp[j] + 1 > dp[i]){
                    dp[i] = dp[j] + 1;
                    result[i] = j;
                }
            }
            max = Math.max(max, dp[i]);
        }
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(dp));
        System.out.println(Arrays.toString(result));
        System.out.println(max);
    }

    /**
     * 寻找最长公共子序列LCS
     * 时间复杂度O(n * m)
     */
    public int getLCS(List<Integer> array1, List<Integer> array2){
        int[][] dp = new int[array1.size() + 1][array2.size() + 1];
        int[][] dpRes = new int[array1.size() + 1][array2.size() + 1];
        int row = 0, col = 0;
        for (int i = 0; i < dp.length; i++){
            dp[i][0] = 0;
        }
        for (int i = 0; i < dp[0].length; i++){
            dp[0][i] = 0;
        }
        for (row = 0; row < array1.size(); row++){
            for (col = 0; col < array2.size(); col++){
                if (Integer.compare(array1.get(row), array2.get(col)) == 0){
                    dp[row][col] = dp[row - 1][col - 1] + 1;
                    dpRes[row][col] = 0;
                }else if (dp[row][col - 1] > dp[row - 1][col]){
                    dp[row][col] = dp[row][col - 1];
                    dpRes[row][col] = -1;
                }else {
                    dp[row][col] = dp[row - 1][col];
                    dpRes[row][col] = 1;
                }
            }
        }
        printLCS(dpRes, array1, array1.size(), array2.size());
        return dp[row][col];
    }
    private void printLCS(int[][] array, List<Integer> x, int row, int col){
        String str = printLCS1(array, x, row, col);
        System.out.println(str);
    }
    private String printLCS1(int[][] array, List<Integer> x, int row, int col) {
        StringBuilder builder = new StringBuilder();
        if (row == 0 || col == 0) {
            return "";
        } else if (array[row][col] == 0) {
            builder.append(printLCS1(array, x, row - 1, col - 1))
                    .append(array[row][col])
                    .append(",");
        }else if (array[row][col] == -1){
            builder.append(printLCS1(array, x, --row, col));
        }else {
            builder.append(printLCS1(array, x, row, --col));
        }
        return builder.toString();
    }











}
