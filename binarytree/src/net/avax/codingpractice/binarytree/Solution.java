package net.avax.codingpractice.binarytree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int length = inorder.length;

        if (postorder.length != length) {
            return null;
        }

        Map<Integer, Integer> inOrderPosForVal = new HashMap<>();
        Map<Integer, Integer> postOrderPosForVal = new HashMap<>();
        Set<Integer> visitedVals = new HashSet<>();

        for (int pos = 0; pos < length; pos++) {
            inOrderPosForVal.put(inorder[pos], pos);
            postOrderPosForVal.put(postorder[pos], pos);
        }

        int rootPostOrderPos = length - 1;

        return getNode(inorder, postorder, inOrderPosForVal,
                postOrderPosForVal, visitedVals,
                rootPostOrderPos);
    }

    private TreeNode getNode(
            int[] inorder,
            int[] postorder,
            Map<Integer, Integer> inOrderPosForVal,
            Map<Integer, Integer> postOrderPosForVal,
            Set<Integer> visitedVals,
            int postOrderPos) {
        if (postOrderPos < 0 || postOrderPos >= postorder.length) {
            return null;
        }

        int val = postorder[postOrderPos];

        TreeNode node = new TreeNode(val);

        visitedVals.add(val);

        int childAPostOrderPos = postOrderPos - 1;
        int childAVal;

        if (childAPostOrderPos >= 0 && !visitedVals.contains(childAVal
                = postorder[childAPostOrderPos])) {
            int inOrderPos = inOrderPosForVal.get(val);

            int childBInOrderPos = inOrderPos - 1;
            int childBVal;

            if (childBInOrderPos >= 0 && !visitedVals.contains(childBVal =
                    inorder[childBInOrderPos])) {
                if (childBVal == childAVal) {

                    // Node has one child: Child A on left.
                    // (Child B is identical to Child A.)

                    node.left = getNode(inorder, postorder, inOrderPosForVal,
                            postOrderPosForVal, visitedVals,
                            childAPostOrderPos);
                } else {

                    // Node has two children: Child B on left, Child A on right.
                    // (Child B is distinct from Child A.)

                    int childBPostOrderPos = postOrderPosForVal.get(childBVal);

                    node.left = getNode(inorder, postorder, inOrderPosForVal,
                            postOrderPosForVal, visitedVals,
                            childBPostOrderPos);

                    node.right = getNode(inorder, postorder, inOrderPosForVal,
                            postOrderPosForVal, visitedVals,
                            childAPostOrderPos);
                }
            } else {

                // Node has one child: Child A on right.
                // (There is no Child B.)

                node.right = getNode(inorder, postorder, inOrderPosForVal,
                        postOrderPosForVal, visitedVals, childAPostOrderPos);
            }
        }

        return node;
    }
}
