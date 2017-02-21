package com.huirong.weiruan100;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by nanhuirong on 16-8-30.
 n 支队伍比赛,分别编号为 0,1,2。。
 。。n-1,已知它们之间的实力对比关系,
 存储在一个二维数组 w[n][n]中,w[i][j] 的值代表编号为 i,j 的队伍中更强的一支。
 所以 w[i][j]=i 或者 j,现在给出它们的出场顺序,并存储在数组 order[n]中,
 比如 order[n] = {4,3,5,8,1......},那么第一轮比赛就是 4 对 3, 5 对 8。.......
 胜者晋级,败者淘汰,同一轮淘汰的所有队伍排名不再细分,即可以随便排,
 下一轮由上一轮的胜者按照顺序,再依次两两比,比如可能是 4 对 5,直至出现第一名
 编程实现,给出二维数组 w,一维数组 order 和用于输出比赛名次的数组 result[n],
 求出 result。
 */
public class test036 {
    public static void main(String[] args){
        test036 demo = new test036();
        int[][] weight = {{0,1,2,3,4},{1,1,2,3,4},{2,2,2,3,4},{3,3,3,3,4},{4,4,4,4,4}};
        int[] order = {4,3,1,2,0};
        int[] result = new int[order.length];
        demo.knockOut(weight, order, result);
        System.out.println(Arrays.toString(result));
    }
    public void knockOut(int[][] weight, int[] order, int[] result){
        int number = weight.length;
//        int round = number;
        int orderIndex = number;
        int resultIndex = number;
        //存储上一轮的比赛比赛的胜出队伍
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < orderIndex; i++){
            list.add(order[i]);
        }
//        System.out.println(list.size());
        while (list.size() > 1){
            int resultIndexPre = resultIndex;
            //一轮比赛
            for (int i = 0; i < list.size() - 1; i += 2){
                int winner = weight[list.get(i)][list.get(i + 1)];
                int loser = winner == list.get(i) ? list.get(i + 1) : list.get(i);
//                System.out.print("(" + winner + "-->" + loser + ")\t");
//                list.remove(loser);
                result[--resultIndex] = loser;
            }
//            System.out.println();
            //将所有的loser从list中移除
            for (int i = resultIndexPre - 1; i >= resultIndex; i--){
                int index = list.indexOf(result[i]);
                list.remove(index);
            }
//            System.out.println("胜出者\t" + list);
//            System.out.println(Arrays.toString(result));
//            System.out.println("------------------------");
        }
//        System.out.println(list.get(0));
        result[--resultIndex] = list.get(0);
    }
}
