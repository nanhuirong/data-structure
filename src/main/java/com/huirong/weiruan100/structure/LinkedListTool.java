package com.huirong.weiruan100.structure;

import com.huirong.weiruan100.tool.Node;

/**
 * Created by nanhuirong on 16-8-29.
 * 链表操作工具类
 */
public class LinkedListTool {
    //链表插入, 尾插入
    public Node<Integer> insert(Node<Integer> head, Node<Integer> node){
        if (head == null){
            head = node;
        }else {
            Node<Integer> pointer = head;
            while (pointer.next != null){
                pointer = pointer.next;
            }
            pointer.next = node;
            node.next = null;
        }
        return head;
    }

    //遍历链表
    public void printList(Node<Integer> head){
        if (head == null){
            return;
        }
        System.out.print("链表的哈希值:" + head.hashCode() + "\t");
        Node<Integer> pointer = head;
        while (pointer != null){
            System.out.print(pointer.getItem() + "(" + pointer.hashCode() + ")" + "->");
            pointer = pointer.next;
        }
        System.out.print("null\n");
    }

    //单链表就地倒置
    public Node<Integer> reverse(Node<Integer> head){
        if (head == null){
//            System.out.println("kong");
            return null;
        }
        Node<Integer> pointer = head;
        Node<Integer> temp = null;
        Node<Integer> newHead = null;
        while (pointer != null){
            if (newHead == null){
                temp = pointer;
                pointer = pointer.next;
                newHead = temp;
                newHead.next = null;
            }else {
                temp = pointer;
                pointer = pointer.next;
                temp.next = newHead;
                newHead = temp;
            }
        }
        return newHead;
    }

    //链表归并, 原链表A与原链表B为单调增, 合并后的链表为单调递减
    public Node<Integer> merge(Node<Integer> headA, Node<Integer> headB){
        if (headA == null && headB == null){
            return null;
        }
        Node<Integer> newHead = null;
        Node<Integer> pointerA = headA;
        Node<Integer> pointerB = headB;
        Node<Integer> temp = null;
        while (pointerA != null && pointerB != null){
            if (pointerA.getItem() < pointerB.getItem()){
                temp = pointerA;
                pointerA = pointerA.next;
            }else {
                temp = pointerB;
                pointerB = pointerB.next;
            }
            if (newHead == null){
                newHead = temp;
                newHead.next = null;
            }else {
                temp.next = newHead;
                newHead = temp;
            }
        }
        Node<Integer> pointer = pointerA == null ? pointerB : pointerA;
        while (pointer != null){
            temp = pointer;
            pointer = pointer.next;
            temp.next = newHead;
            newHead = temp;
        }
        return newHead;
    }


    /**
     *  给出俩个单向链表的头指针,比如 h1,h2,判断这俩个链表是否相交。
     为了简化问题,我们假设俩个链表均不带环。
     问题扩展:
     1.如果链表可能有环列?
     2.如果需要求出俩个链表相交的第一个节点列?
     */
    //如果不存在环, 判断链表是否相交
    private boolean isJoinedSimple(Node<Integer> head1, Node<Integer> head2){
        while (head1.next != null){
            head1 = head1.next;
        }
        while (head2.next != null){
            head2 = head2.next;
        }
        return head1 == head2;
    }

    //判断是否存在环, 如果存在环, 返回相遇的那个节点
    private Node<Integer> testCylic(Node<Integer> head){
        Node<Integer> pointer1 = head;
        Node<Integer> pointer2 = head;
        while (pointer2 != null && pointer2.next != null){
            pointer1 = pointer1.next;
            pointer2 = pointer2.next.next;
            if (pointer1 == pointer2){
                return pointer1;
            }
        }
        return null;
    }

    //判断链表是否相交
    public boolean isJoined(Node<Integer> head1, Node<Integer> head2){
        Node<Integer> cylic1 = testCylic(head1);
        Node<Integer> cylic2 = testCylic(head2);
        //如果不存在环
        if (cylic1 == null && cylic2 == null){
            return isJoinedSimple(head1, head2);
        }
        //如果仅有一个存在环, 肯定不会相交
        if ((cylic1 == null && cylic2 != null) || (cylic1 != null && cylic2 == null)){
            return false;
        }
        //如果两个点都存在环, 判断环里面的一个节点是否能到达列外一个还链表节点
        Node<Integer> pointer = cylic1;
        while (pointer != cylic1){
            if (pointer == cylic2){
                return true;
            }
            pointer = pointer.next;
        }
        return false;
//        while (true){
//            if (pointer == cylic2 || pointer.next == cylic2){
//                return true;
//            }
//            pointer = pointer.next.next;
//            cylic1 = cylic1.next;
//            if (pointer == cylic1){
//                return false;
//            }
//        }
    }

    //输出链表
    public void printLinkedList(Node<Integer> head){
        Node<Integer> pointer = head;
        System.out.print("[");
        while (pointer.next != null){
            System.out.print(pointer.item + "->");
            pointer = pointer.next;
        }
        System.out.print(pointer.item + "]\n");
    }



    //判断是否相交, 如果相交, 得到第一个交点
    public Node<Integer> getFirstintersectNode(Node<Integer> head1, Node<Integer> head2){
        if (head1 == null || head2 == null){
            return null;
        }
        Node<Integer> loop1 = getLoopNode(head1);
        Node<Integer> loop2 = getLoopNode(head2);
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
    private Node<Integer> getLoopNode(Node<Integer> head){
        if (head == null || head.next == null || head.next.next == null){
            return null;
        }
        Node<Integer> slow = head.next;
        Node<Integer> fast = head.next.next;
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
    private Node<Integer> noLoop(Node<Integer> head1, Node<Integer> head2){
        if (head1 == null || head2 == null){
            return null;
        }
        Node<Integer> pointer1 = head1;
        Node<Integer> pointer2 = head2;
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
    private Node<Integer> bothLoop(Node<Integer> head1, Node<Integer> loop1, Node<Integer> head2, Node<Integer> loop2){
        Node<Integer> pointer1 = null;
        Node<Integer> pointer2 = null;
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

    //测试用例
    public static void main(String[] args){
        LinkedListTool listTool = new LinkedListTool();
        //测试链表置换
        Node<Integer> head = null;
        for (int i = 1; i < 10; i++){
            head = listTool.insert(head, new Node<Integer>(i, null));
        }
        listTool.printList(head);
        head = listTool.reverse(head);
//        System.out.println(head == null);
        listTool.printList(head);

        //测试链表归并
        Node<Integer> headA = null;
        for (int i = 1; i < 10; i = i + 2){
            headA = listTool.insert(headA, new Node<Integer>(i, null));
        }
        Node<Integer> headB = null;
        for (int i = 2; i < 10; i = i + 2){
            headB = listTool.insert(headB, new Node<Integer>(i, null));
        }
        listTool.printList(headA);
        listTool.printList(headB);
        head = listTool.merge(headA, headB);
        listTool.printList(head);
    }
}
