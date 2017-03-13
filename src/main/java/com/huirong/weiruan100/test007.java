package com.huirong.weiruan100;


import com.huirong.weiruan100.tool.Node;

/**
 * Created by nanhuirong on 16-8-26.\
 给出俩个单向链表的头指针,比如 h1,h2,判断这俩个链表是否相交。
 为了简化问题,我们假设俩个链表均不带环。
 问题扩展:
 1.如果链表可能有环列?
 2.如果需要求出俩个链表相交的第一个节点列?

 */
public class test007<T> {
//    Node<T> head;

    public test007() {
    }

//    public test007(Node<T> head) {
//        this.head = head;
//    }

    //如果不存在环
    public boolean isJoinedSimple(Node<T> head1, Node<T> head2){
        while (head1.next != null){
            head1 = head1.next;
        }
        while (head2.next != null){
            head2 = head2.next;
        }
        return head1 == head2;
    }


    public boolean isJoined(Node<T> head1, Node<T> head2){
        Node<T> cylic1 = testCylic(head1);
        Node<T> cylic2 = testCylic(head2);
        //如果不存在环
        if (cylic1 == null && cylic2 == null){
            return isJoinedSimple(head1, head2);
        }
        //如果仅有一个存在环, 肯定不会相交
        if ((cylic1 == null && cylic2 != null) || (cylic1 != null && cylic2 == null)){
            return false;
        }
        //如果两个点都存在环,
        Node<T> pointer = cylic1;
        while (true){
            if (pointer == cylic2 || pointer.next == cylic2){
                return true;
            }
            pointer = pointer.next.next;
            cylic1 = cylic1.next;
            if (pointer == cylic1){
                return false;
            }
        }
    }

    //判断是否存在环
    Node<T> testCylic(Node<T> head){
        Node<T> pointer1 = head;
        Node<T> pointer2 = head;
        while (pointer2 != null && pointer2.next != null){
            pointer1 = pointer1.next;
            pointer2 = pointer2.next.next;
            if (pointer1 == pointer2){
                return pointer1;
            }
        }
        return null;
    }

    //输出链表
    public void printLinkedList(Node<T> head){
        Node<T> pointer = head;
        System.out.print("[");
        while (pointer.next != null){
            System.out.print(pointer.item + "->");
            pointer = pointer.next;
        }
        System.out.print(pointer.item + "]\n");
    }



    //判断是否相交, 如果相交, 得到第一个交点
    public Node<T> getFirstintersectNode(Node<T> head1, Node<T> head2){
        if (head1 == null || head2 == null){
            return null;
        }
        Node<T> loop1 = getLoopNode(head1);
        Node<T> loop2 = getLoopNode(head2);
        if (loop1 == null && loop2 == null){
            return noLoop(head1, head2);
        }
        if (loop1 != null && loop2 != null){
            return bothLoop(head1, loop1, head2, loop2);
        }
        return null;

    }

    //判断是否存在环, 如果存在, 则找出环的入口
    //从头指针和相遇点同时往后走, 相遇的必定是环入口
    //返回节点是环的入口
    public Node<T> getLoopNode(Node<T> head){
        if (head == null || head.next == null || head.next.next == null){
            return null;
        }
        Node<T> slow = head.next;
        Node<T> fast = head.next.next;
        while (slow != fast){
            if (fast.next == null || fast.next.next == null){
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        //从头指针和相遇节点开始往后走
        fast = head;
        while (slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    //无环时的判断方法
    public Node<T> noLoop(Node<T> head1, Node<T> head2){
        if (head1 == null || head2 == null){
            return null;
        }
        Node<T> pointer1 = head1;
        Node<T> pointer2 = head2;
        int step = 0;
        while (pointer1.next != null){
            step++;
            pointer1 = pointer1.next;
        }
        while (pointer2.next != null){
            step--;
            pointer2 = pointer2.next;
        }
        if (pointer1 != pointer2){
            return null;
        }
        //pointer1 指向长的链表首
        //pointer2 指向短的链表首
        pointer1 = step > 0 ? head1 : head2;
        pointer2 = pointer1 == head1 ? head2 : head1;
        step = Math.abs(step);
        while (step != 0){
            step--;
            pointer1 = pointer1.next;
        }
        while (pointer1 != pointer2){
            pointer1 = pointer1.next;
            pointer2 = pointer2.next;
        }
        return pointer1;
    }

    //有环时的判断方法
    public Node<T> bothLoop(Node<T> head1, Node<T> loop1, Node<T> head2, Node<T> loop2){
        Node<T> pointer1 = null;
        Node<T> pointer2 = null;
        //如果入口相同,
        if (loop1 == loop2){
            pointer1 = head1;
            pointer2 = head2;
            int step = 0;
            while (pointer1 != loop1){
                step++;
                pointer1 = pointer1.next;
            }
            while (pointer2 != loop2){
                step--;
                pointer2 = pointer2.next;
            }
            pointer1 = step > 0 ? head1 : head2;
            pointer2 = pointer1 == head1 ? head2 : head1;
            step = Math.abs(step);
            while (step != 0){
                pointer1 = pointer1.next;
                step--;
            }
            while (pointer1 != pointer2){
                pointer1 = pointer1.next;
                pointer2 = pointer2.next;
            }
            return pointer1;
        }else {
            //如果入口不相同, 则这个相交点或是链表一的入环点, 或是链表二的入环点
            pointer1 = loop1.next;
            while (pointer1 != loop1){
                if (pointer1 == loop2){
                    return loop1;
                }
                pointer1 = pointer1.next;
            }
            return null;
        }

    }



    public static void main(String[] args){
        test007<Integer> demo = new test007<Integer>();
        //测试无环情况
        Node<Integer> head1 = new Node<Integer>(1, null);
        Node<Integer> pointer = head1;
        Node<Integer> temp1 = null;
        temp1 = new Node<Integer>(2, null);
        pointer.next = temp1;
        pointer = pointer.next;
        temp1 = new Node<Integer>(3, null);
        pointer.next = temp1;
        pointer = pointer.next;
        temp1 = new Node<Integer>(4, null);
        pointer.next = temp1;
        pointer = pointer.next;
        temp1 = new Node<Integer>(5, null);
        pointer.next = temp1;
        pointer = pointer.next;
        temp1 = new Node<Integer>(6, null);
        pointer.next = temp1;
        pointer = pointer.next;
        temp1 = new Node<Integer>(7, null);
        pointer.next = temp1;
//        pointer = pointer.next;
        demo.printLinkedList(head1);

        Node<Integer> head2 = null;
        Node<Integer> temp2 = null;
//        pointer = head2;
        temp2 = new Node<Integer>(0, null);
        head2 = temp2;
        pointer = head2;
//        pointer = pointer.next;
        temp2 = new Node<Integer>(9, null);
        pointer.next = temp2;
        pointer = pointer.next;
        temp2 = new Node<Integer>(8, null);
        pointer.next = temp2;
        pointer = pointer.next;
        temp2 = new Node<Integer>(6, null);
        pointer.next = temp2;
        pointer = pointer.next;
        pointer.next = temp1;
//        pointer.next = new Node<Integer>(7, null);
        demo.printLinkedList(head2);
//        System.out.println("测试无环情况:\t" + demo.isJoined(head1, head2));
        System.out.println(demo.getFirstintersectNode(head1, head2).item);
        //测试有环情况






    }


}


