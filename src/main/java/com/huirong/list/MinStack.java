package com.huirong.list;

/**
 * Created by huirong on 17-6-1.
 * 最小操作数栈
 */
public class MinStack {
    private int[] stack;
    private int[] minStack;
    private int top1 = -1, top2 = -1;

    public MinStack() {
        construct(16);
    }

    public MinStack(int num){
        construct(num);
    }

    private void construct(int num){
        stack = new int[num];
        minStack = new int[num];
    }

    public void push(int elem)throws Exception{
        if (top1 < stack.length && top2 < stack.length){
            stack[++top1] = elem;
            if (top2 == -1 || elem < minStack[top2]){
                stack[++top2] = elem;
            }else {
                int elem1 = minStack[top2];
                minStack[++top2] = elem1;
            }
        }else {
            throw new Exception("");
        }
    }

    public int pop()throws Exception{
        if (top1 > -1 && top2 > -1){
            top2--;
            return stack[top1--];
        }else {
            throw new Exception("");
        }
    }

    public int min()throws Exception{
        if (top1 > -1 && top2 > -1){
            return minStack[top2];
        }else {
            throw new Exception("");
        }
    }

    /**
     * 给定两个整数序列，一个表示压入顺序，另外一个表示弹出顺序
     */
    public boolean isPopOrder(int[] pushs, int[] pops){
        boolean result = false;
        if (pushs != null && pushs.length != 0 &&
                pops != null && pops.length != 0 &&
                pushs.length == pops.length){
            int pushIndex = 0, popIndex = 0, top = -1;
            int[] stack = new int[pushs.length];
            while (popIndex < pops.length){
                while (top == -1 || stack[top] != pops[popIndex]){
                    if (pushIndex == pushs.length){
                        break;
                    }
                    stack[++top] = pushs[pushIndex++];
                }
                if (stack[top] != pops[popIndex]){
                    break;
                }
                top--;
                popIndex++;
            }
            if (top == -1 &&  popIndex == pops.length){
                result = true;
            }
        }
        return result;
    }

}
