package com.huirong.tree;

import com.huirong.sort.IntegerComparator;
import com.huirong.tree.BinarySortTree.Node;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Created by huirong on 17-4-18.
 */
public class TreeTest {
    public static void main(String[] args) {
        TreeTest test = new TreeTest();
        Comparator comparator = new IntegerComparator();
        BinarySortTree tree = new BinarySortTree(comparator);
        List<Integer> list = test.generator(100, 10);
        System.out.println(list);
        for (int i = 0; i < list.size(); i++){
//            tree.insert(list.get(i));
            tree.insertRec(list.get(i));
        }

//        //前序遍历
//        tree.preOrder();
//        //中序遍历
//        tree.inOrder();
//        //后序遍历
//        tree.postOrder();
        //查找
//        Node node = tree.search(list.get(4));
//        System.out.println(node.getValue());
//        node = tree.search(101);
//        System.out.println(node == null);
//        //寻找最大
//        node = tree.max();
//        System.out.println("最大的节点\t" + node.getValue());
//        node = tree.min();
//        System.out.println("最小的节点\t" + node.getValue());
        for (Integer elem : list){
//            node = tree.successor(elem);
//            System.out.println(elem + "后继\t" + (node == null ? "null" : node.getValue()));
//            node = tree.predecessor(elem);
//            System.out.println(elem + "前驱\t" + (node == null ? "null" : node.getValue()));
            System.out.println("删除" + elem);
            tree.remove(elem);
            tree.inOrder();
        }

    }
    public List<Integer> generator(int thread, int len){
        Random random = new Random(System.currentTimeMillis());
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < len; i++){
            list.add(random.nextInt(thread));
        }
        return list;
    }
}
