package com.huirong.list;

/**
 * Created by huirong on 17-5-22.
 * 两个队列模拟栈
 * 队列为循环队列
 */
public class QueueStack<E> {
    private final int NUM;
    private Object[] queue1;
    private Object[] queue2;
    private int front1 = 0, tail1 = 0, front2 = 0, tail2 = 0;


    public QueueStack(int num) {
        this.NUM = num;
        queue1 = new Object[NUM];
        queue2 = new Object[NUM];
    }

    /**
     * 如果queue1或者queue2都为空
     */
    public boolean empty(){
        return front1 == tail1 && front2 == tail2;
    }

    /**
     * 两个队列任意一个队列满都会溢出
     */
    public boolean full(){
        return (tail1 + 1) % NUM == front1 || (tail2 + 1) % NUM == front2;
    }

    /**
     * 入栈
     *  1.如果队列不满，则往队列中添加元素，如果队列满，溢出
     */
    public void push(E elem){
        if (empty()){
            queue1[(tail1++) % NUM] = elem;
        }else {
            if (full()){
                throw new RuntimeException("栈满");
            }else {
                if (tail1 == front1){
                    queue2[(tail2++) % NUM] = elem;
                }else {
                    queue1[(tail1++) % NUM] = elem;
                }
            }
        }
    }

    public E pop(){
        if (empty()){
            throw new RuntimeException("栈空");
        }else {
            if (tail1 == front1){
                while ((tail2 + 2) % NUM != front2){
                    queue1[tail1++] = queue2[front2++];
                }
                return (E)queue2[front2++];
            }else {
                while ((tail1 + 2) % NUM != front1){
                    queue2[tail2++] = queue1[front1++];
                }
                return (E)queue1[front1++];
            }
        }
    }
}
