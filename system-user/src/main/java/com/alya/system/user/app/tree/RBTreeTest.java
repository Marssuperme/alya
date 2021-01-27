package com.alya.system.user.app.tree;

public class RBTreeTest {

    public static void main(String[] args) {
        RedBlackTree<Integer, Object> tree = new RedBlackTree<>();
        tree.put(2, 1);
        tree.put(8, 8);
        tree.put(6, 5);
        tree.put(10, 3);
        tree.put(4, 4);
        tree.put(100, 4);
        System.out.println(tree.getRoot().getKey());
        tree.inOrderPrint();
        System.out.println();

        tree.preOrderPrint();
        System.out.println();

        tree.afterOrderPrint();
        System.out.println();

        System.out.println(tree.size());
    }

}
