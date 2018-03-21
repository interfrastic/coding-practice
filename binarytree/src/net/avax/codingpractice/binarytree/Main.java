package net.avax.codingpractice.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static void getPreOrderVals(TreeNode n, List<Integer> vals) {
        if (n == null || vals == null) {
            return;
        }

        vals.add(n.val);
        getPreOrderVals(n.left, vals);
        getPreOrderVals(n.right, vals);
    }

    private static void getInOrderVals(TreeNode n, List<Integer> vals) {
        if (n == null || vals == null) {
            return;
        }

        getInOrderVals(n.left, vals);
        vals.add(n.val);
        getInOrderVals(n.right, vals);
    }

    private static void getPostOrderVals(TreeNode n, List<Integer> vals) {
        if (n == null || vals == null) {
            return;
        }

        getPostOrderVals(n.left, vals);
        getPostOrderVals(n.right, vals);
        vals.add(n.val);
    }

    private static void printPreOrder(TreeNode n) {
        List<Integer> vals = new ArrayList<>();

        getPreOrderVals(n, vals);

        System.out.println("preorder = [" + vals.stream()
                .map(Object::toString)
                .collect(Collectors.joining(",")) + "]");
    }

    private static void printInOrder(TreeNode n) {
        List<Integer> vals = new ArrayList<>();

        getInOrderVals(n, vals);

        System.out.println("inorder = [" + vals.stream()
                .map(Object::toString)
                .collect(Collectors.joining(",")) + "]");
    }

    private static void printPostOrder(TreeNode n) {
        List<Integer> vals = new ArrayList<>();

        getPostOrderVals(n, vals);

        System.out.println("postorder = [" + vals.stream()
                .map(Object::toString)
                .collect(Collectors.joining(",")) + "]");
    }

    public static void main(String[] args) {
        TreeNode n = new TreeNode(3);

        n.left = new TreeNode(9);
        n.right = new TreeNode(20);
        n.right.left = new TreeNode(15);
        n.right.right = new TreeNode(7);

        printPreOrder(n);
        printInOrder(n);
        printPostOrder(n);
    }
}
