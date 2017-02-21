package com.huirong.weiruan100New.tool;

import java.util.Arrays;

/**
 * Created by nanhuirong on 16-9-13.
 */
public class StringTool {
    /*
    翻转句子中单词的顺序。
    题目：输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。
    句子中单词以空格符隔开。为简单起见，标点符号和普通字母一样处理。
    例如输入“I am a student.”，则输出“student. a am I”。
     */
    public String reserveWord(String str){
        String[] split = str.split(" ");
        int low = 0, high = split.length - 1;
        String[] destStr = new String[split.length];
        while (low <= high){
            destStr[low] = split[high];
            destStr[high] = split[low];
            low++;
            high--;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < destStr.length; i++){
            if (i == destStr.length - 1){
                sb.append(destStr[i]);
            }else {
                sb.append(destStr[i] + " ");
            }
        }
        return sb.toString();
    }

    /*
    题目：在一个字符串中找到第一个只出现一次的字符。如输入abaccdeff，则输出b。
     */
    public char getTheFirstChar(String str){
        int[] bitMap = new int[256];
        for (int i = 0; i < str.length(); i++){
            bitMap[str.charAt(i)]++;
        }
        for (int i = 0; i < str.length(); i++){
            if (bitMap[str.charAt(i)] == 1){
                return str.charAt(i);
            }
        }
        return 0;
    }

    /*
    在字符串中找出连续最长的数字串，并把这个串的长度返回，
    并把这个最长数字串付给其中一个函数参数outputstr所指内存。
    例如："abcd12345ed125ss123456789"的首地址传给intputstr后，函数将返回9，
    outputstr所指的值为123456789
     */
    public char[] continueMax(char[] inputChar){
        int maxIndex = 0, maxLen = 0;
        int currentMaxIndex = 0, currentMaxLen = 0;
//        for (int i = 0; i < inputChar.length; i++){
//            int j = i;
//            while (j < inputChar.length && (inputChar[j] > '9' || inputChar[j] < '0')) j++;
//            currentMaxIndex = j;
//            while (j < inputChar.length && inputChar[j] <= '9' && inputChar[j] >= '0'){
//                j++;
//                currentMaxLen++;
//            }
//            if (currentMaxLen > maxLen){
//                maxIndex = currentMaxIndex;
//                maxLen = currentMaxLen;
//                currentMaxLen = 0;
//            }
//        }
        int i = 0;
        int j = 0;
        while (i < inputChar.length){
            j = i;
            while (j < inputChar.length && (inputChar[j] > '9' || inputChar[j] < '0')) j++;
            currentMaxIndex = j;
            while (j < inputChar.length && inputChar[j] <= '9' && inputChar[j] >= '0'){
                j++;
                currentMaxLen++;
            }
            if (currentMaxLen > maxLen){
                maxIndex = currentMaxIndex;
                maxLen = currentMaxLen;
            }
            i = j;
            currentMaxLen = 0;
        }
        char[] outputChar = new char[maxLen];
        int pointer = maxIndex;
        for (i = 0; i < maxLen; i++){
            outputChar[i] = inputChar[pointer++];
        }
        return outputChar;
    }


    /*
    定义字符串的左旋转操作：把字符串前面的若干个字符移动到字符串的尾部。
    如把字符串abcdef左旋转2位得到字符串cdefab。请实现字符串左旋转的函数。
    要求时间对长度为n的字符串操作的复杂度为O(n)，辅助内存为O(1)
     */
    //目前的算法是两次倒置, 时间复杂度是O(n)
    public String reverseLeft(String str, int step){
        char[] array = str.toCharArray();
        //首先进行一次全局倒置
        int low = 0, high = array.length - 1;
        while (low < high){
            char temp = array[low];
            array[low] = array[high];
            array[high] = temp;
            low++;
            high--;
        }
        low = 0;
        high = array.length - 1 - step;
        while (low < high){
            char temp = array[low];
            array[low] = array[high];
            array[high] = temp;
            low++;
            high--;
        }
        low = array.length - step;
        high = array.length - 1;
        while (low < high){
            char temp = array[low];
            array[low] = array[high];
            array[high] = temp;
            low++;
            high--;
        }
        return new String(array);
    }

    public static void main(String[] args){
        StringTool stringTool = new StringTool();
//        //测试句中单词的倒置
//        System.out.println(stringTool.reserveWord("I am a student."));
//        //测试字符串中第一个出现的之出现一次的字符
//        System.out.println(stringTool.getTheFirstChar("abaccdeff"));
        //测试字符串的最长数字串
//        System.out.println(Arrays.toString(stringTool.continueMax("abcd12345ed125ss123456789".toCharArray())));
        //测试字符串的左旋
        System.out.println(stringTool.reverseLeft("abcdef", 2));

    }

}
