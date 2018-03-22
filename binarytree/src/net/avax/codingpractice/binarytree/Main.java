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
        Solution solution = new Solution();

        TreeNode node = solution.buildTree(
                new int[]{9, 3, 15, 20, 7},
                new int[]{9, 15, 7, 20, 3}
        );

        printVals(node, Traversal.PRE_ORDER);
        printVals(node, Traversal.IN_ORDER);
        printVals(node, Traversal.POST_ORDER);

        node = solution.buildTree(
                new int[]{2, 3, 1},
                new int[]{3, 2, 1}
        );

        System.out.println();
        printVals(node, Traversal.PRE_ORDER);
        printVals(node, Traversal.IN_ORDER);
        printVals(node, Traversal.POST_ORDER);

        node = solution.buildTree(
                new int[]{1, 3, 2},
                new int[]{3, 2, 1}
        );

        System.out.println();
        printVals(node, Traversal.PRE_ORDER);
        printVals(node, Traversal.IN_ORDER);
        printVals(node, Traversal.POST_ORDER);
    }
}
