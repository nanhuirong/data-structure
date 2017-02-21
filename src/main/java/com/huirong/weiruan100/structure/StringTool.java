package com.huirong.weiruan100.structure;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by nanhuirong on 16-8-29.
 * 字符串操作工作类
 */
public class StringTool {
    //在一个字符串中找到第一个只出现一次的字符。如输入 abaccdeff,则输出 b
    public char firstSingle(String str){
        //用一个256数组表示ASCII码
        int[] cache = new int[256];
        char[] array = str.toCharArray();
        for (int i = 0; i < array.length; i++){
            cache[array[i]] ++;
        }
        //cache中缓存着每个字符出现的次数
        for (int i = 0; i < array.length; i++){
            if (cache[array[i]] == 1){
                return array[i];
            }
        }
        //如果没有找到, 返回\0
        return '\0';
    }

    //输入一个表示整数的字符串,把该字符串转换成整数并输出
    public int transformStringToInt(String str){
        return Integer.parseInt(str);
    }

    //在字符串中找出连续最长的数字串,并把这个串的长度返回, 并把这个最长数字串付给其中一个函数参数
    //如果查找失败, 返回0, 且list为空
    public int continueMaxDigital(String str, List<Character> list){
//        char[] array = str.toCharArray();
        //记录数字的起始位置
        int maxStartIndex = 0;
        //记录数字串的最大长度
        int maxLen = 0;
        //记录当前数字串的起始位置
        int startIndex = 0;
        //记录当前数字串的长度
        int len = 0;
        for (int i = 0; i < str.length(); i++){
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9'){
//                System.out.println(str.charAt(i));
                if (len == 0){
                    startIndex = i;
                }
                len++;
            }else {
                if (maxLen < len){
                    maxStartIndex = startIndex;
                    maxLen = len;
                }
                len = 0;
            }
        }
        if (maxLen < len){
            maxStartIndex = startIndex;
            maxLen = len;
        }
//        System.out.print("(" + maxStartIndex + "," + maxLen + ")\n");
//        System.out.print("(" + startIndex + "," + len + ")\n");
        for (int i = 0; i < maxLen; i++){
            list.add(str.charAt(maxStartIndex++));
        }
        return maxLen;
    }

    //定义字符串的左旋转操作:把字符串前面的若干个字符移动到字符串的尾部, 如把字符串 abcdef 左旋转 2 位得到字符串 cdefab。请实现字符串左旋转的函数
    //要求时间对长度为 n 的字符串操作的复杂度为 O(n),辅助内存为 O(1)
    public String leftReverse(String str, int len){
        //首先将字符串完全倒置
        str = reverse(str, 0, str.length());
        str = reverse(str, 0, str.length() - len);
        str = reverse(str, str.length() - len, len);
        return str;
    }


    //字符串倒置
    public String reverse(String str, int startIndex, int len){
        char[] array = str.toCharArray();
        int headIndex = startIndex;
        int tailIndex = startIndex + len - 1;
        while (headIndex <= tailIndex){
            char temp = array[headIndex];
            array[headIndex] = array[tailIndex];
            array[tailIndex] = temp;
            headIndex++;
            tailIndex--;
        }
        return new String(array);
    }

    /*
    有 n 个长为 m+1 的字符串,
    如果某个字符串的最后 m 个字符与某个字符串的前 m 个字符匹配,则两个字符串可以联接,
    问这 n 个字符串最多可以连成一个多长的字符串,如果出现循环,则返回错误。
     */



    /*
    输入一个字符串,打印出该字符串中字符的所有排列。无法解决出现的重复排列问题
    例如输入字符串 abc,则输出由字符 a、b、c 所能排列出来的所有字符串
    abc、acb、bac、bca、cab 和 cba
     */
    public void permutation(char[] array, int i){
        if (i >= array.length){
            return;
        }
        if (i == array.length - 1){
            System.out.println(String.valueOf(array));
        }else {
            for (int j = i; j < array.length; j++){
                char temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                permutation(array, i + 1);
                temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
    }

    //LCS字符串的最长子序列
//    public static int LCS(String str1, String str2){
//        char[] charArray1 = str1.toCharArray();
//        char[] charArray2 = str2.toCharArray();
//        if (charArray1.length == 0 || charArray2.length == 0){
//            return 0;
//        }
//        int[][] array = new int[charArray1.length][charArray2.length];
//
//    }

    //寻找最长的回文字串
    private static String getEvery(char[] ch, int i, int j){
        while (i >= 0 && j < ch.length && ch[i] == ch[j]){
            i--;
            j++;
        }
        return String.valueOf(ch).substring(i + 1, j);
    }

    public static String getPalindrome(String str){
        char[] ch = str.toCharArray();
        String result = "";
        String temp = "";
        for (int i = 0; i < ch.length; i++){
            temp = getEvery(ch, i, i);
            if (temp.length() > result.length()){
                result = temp;
            }
            temp = getEvery(ch, i, i + 1);
            if (temp.length() > result.length()){
                result = temp;
            }
        }
        return result;
    }

    public static void main(String[] args)throws Exception{
        //测试修改java String类型, 但是并不改变内存大小
//        String str1 = "aaa";
//        String str2 = "aaa";
//        String str3 = "bbbb";
//        System.out.println(str1 + "(" + str1.hashCode() + ")\t" + str2 + "(" + str2.hashCode() + ")\t" + str3 + "(" + str3.hashCode() + ")");
//        Field field = str2.getClass().getDeclaredField("value");
//        field.setAccessible(true);
//        field.set(str2, new char[]{'b', 'b', 'b', 'b'});
//        System.out.println(str1 + "(" + str1.hashCode() + ")\t" + str2 + "(" + str2.hashCode() + ")\t" + str3 + "(" + str3.hashCode() + ")");
//        field = str2.getClass().getDeclaredField("hash");
//        field.setAccessible(true);
//        field.set(str2, 0);
//        System.out.println(str1 + "(" + str1.hashCode() + ")\t" + str2 + "(" + str2.hashCode() + ")\t" + str3 + "(" + str3.hashCode() + ")");
//        System.out.println(str1 == str2);
//        System.out.println(str2 == str3);
        StringTool stringTool = new StringTool();
//        System.out.println(stringTool.reverse("nanhui", 0, 6));
//        System.out.println(stringTool.leftReverse("abcdef", 2));
        //测试输出字符串的全排列
        String str = "abcd";
        stringTool.permutation(str.toCharArray(), 0);
        //最长回文字串
//        String str = "abcddcc";
//        System.out.println(stringTool.getPalindrome(str));


    }




}
