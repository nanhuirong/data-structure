package com.huirong.weiruan100New.to10;

import java.util.LinkedList;

/**
 * Created by nanhuirong on 16-9-12.
 */

//采用栈的数据结构, 使得push, pop, min 时间复杂度O(1)
public class MinStack {
    private LinkedList<Integer> stack = new LinkedList<Integer>();
    private LinkedList<Integer> minStack = new LinkedList<Integer>();

    //判断栈空
    public boolean isEmpty(){
        return stack.isEmpty();
    }

    //入栈
    public void push(Integer data){
        if (minStack.isEmpty()){
            minStack.push(data);
        }else if (data <= getMin()){
            minStack.push(data);
        }
        stack.push(data);
    }

    //出栈
    public Integer pop(){
        int temp = stack.pop();
        if (temp == getMin()){
            minStack.pop();
        }
        return temp;
    }

    public Integer peek(){
        return stack.peek();
    }

    //得到最小数
    public Integer getMin(){
        return minStack.peek();
    }

    private void pushMinStack(Integer data){
        minStack.push(data);
    }

    private Integer popMinStack(){
        return minStack.pop();
    }

    public static void main(String[] args){
        MinStack minStack = new MinStack();
        minStack.push(3);
        minStack.push(2);
        minStack.push(1);
        minStack.push(6);
        minStack.push(5);
        minStack.push(4);
        minStack.push(1);
        while (!minStack.isEmpty()){
            System.out.println("当前栈最小元素:" + minStack.getMin() + "\t" + minStack.getMin().hashCode());
            System.out.println("弹出栈顶元素:" + minStack.peek() + "\t" + minStack.peek().hashCode());
            minStack.pop();
//            System.out.println("弹出栈顶元素:" + minStack.peek() + "\t" + minStack.peek().hashCode());
            System.out.println("-----------------------------------------------");
        }
    }

}
