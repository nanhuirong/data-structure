package com.huirong.list;

import java.util.Queue;
import java.util.Stack;

/**
 * Created by huirong on 17-5-22.
 * 利用两个栈模拟队列
 */
public class StackQueue <E>{
    private final int NUM;
    private Object[] stack1;
    private Object[] stack2;
    private int top1 = -1;
    private int top2 = -1;

    public StackQueue(int num) {
        this.NUM = num;
        stack1 = new Object[NUM];
        stack2 = new Object[NUM];
    }



    /**
     * 入队操作
     *  1.如果stack1不满，放入stack1空
     *  2.如果stack1满并且stack2为空，将stack1中元素压入stack2中，然后放入stack1中
     *  3.如果stack1满并且stack2不为空，则出现栈溢出
     */
    public void enQueue(E elem){
        if (top1 < NUM - 1){
            stack1[++top1] = elem;
        }else {
            if (top2 == -1){
                throw new RuntimeException("栈溢出");
            }else {
                while (top1 != -1){
                    stack2[++top2] = stack1[top1--];
                }
                stack1[++top1] = elem;
            }
        }
    }

    /**
     * 出队操作
     *  1.当stack2不为空时，从stack2中弹出元素
     *  2.当stack2为空时，将stack1中的元素压入stack2中
     *  3.如果stack1与stack2都为空则出现溢出现象
     */
    public E deQueue(){
        if (top2 != -1){
            return (E)stack2[top2--];
        }else {
            if (top1 != -1){
                while (top1 != -1){
                    stack2[++top2] = stack1[top1--];
                }
                return (E)stack2[top2--];
            }else {
                throw new RuntimeException("栈为空");
            }
        }
    }

    /**
     * 队空：stack1与stack2均为空
     */
    public boolean empty(){
        return top1 == -1 && top2 == -1;
    }

    /**
     * 队满：stack1满，stack2非空
     */
    public boolean full(){
        return top1 == NUM - 1 && top2 != -1;
    }
}
