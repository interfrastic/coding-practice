package net.avax.codingpractice.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private enum Traversal {
        PRE_ORDER("preorder"),
        IN_ORDER("inorder"),
        POST_ORDER("postorder");

        private final String s;

        Traversal(String s) {
            this.s = s;
        }

        public String toString() {
            return s;
        }
    }

    private static void getVals(TreeNode n, List<Integer> vals, Traversal t) {
        if (n == null || vals == null || t == null) {
            return;
        }

        if (t == Traversal.PRE_ORDER) {
            vals.add(n.val);
        }

        getVals(n.left, vals, t);

        if (t == Traversal.IN_ORDER) {
            vals.add(n.val);
        }

        getVals(n.right, vals, t);

        if (t == Traversal.POST_ORDER) {
            vals.add(n.val);
        }
    }

    private static void printVals(TreeNode n, Traversal t) {
        List<Integer> vals = new ArrayList<>();

        getVals(n, vals, t);

        System.out.println(t + " = [" + vals.stream()
                .map(Object::toString)
                .collect(Collectors.joining(",")) + "]");
    }

    public static void main(String[] args) {
        TreeNode n = new TreeNode(3);

        n.left = new TreeNode(9);
        n.right = new TreeNode(20);
        n.right.left = new TreeNode(15);
        n.right.right = new TreeNode(7);

        printVals(n, Traversal.PRE_ORDER);
        printVals(n, Traversal.IN_ORDER);
        printVals(n, Traversal.POST_ORDER);
    }
}
