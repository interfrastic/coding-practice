package net.avax.codingpractice.binarytree;

public class Solution {
    private TreeNode getNode(
            int[] inorder, int inorderBeginIndex, int inorderEndIndex,
            int[] postorder, int postorderBeginIndex, int postorderEndIndex) {
        if (inorderBeginIndex < 0
                || inorderBeginIndex >= inorder.length
                || inorderEndIndex <= 0
                || inorderEndIndex > inorder.length
                || inorderBeginIndex >= inorderEndIndex
                || postorderBeginIndex < 0
                || postorderBeginIndex >= postorder.length
                || postorderEndIndex <= 0
                || postorderEndIndex > postorder.length
                || postorderBeginIndex >= postorderEndIndex) {
            return null;
        }

        int rootPostorderIndex = postorderEndIndex - 1;
        int rootVal = postorder[rootPostorderIndex];

        TreeNode node = new TreeNode(rootVal);

        int rootInorderIndex = inorderEndIndex - 1;

        while (rootInorderIndex >= inorderBeginIndex
                && inorder[rootInorderIndex] != rootVal) {
            rootInorderIndex--;
        }

        int rightInorderBeginIndex = rootInorderIndex + 1;
        int rightInorderEndIndex = inorderEndIndex;
        int rightLength = rightInorderEndIndex - rightInorderBeginIndex;
        int rightPostorderBeginIndex = rootPostorderIndex - rightLength;
        int rightPostorderEndIndex = rootPostorderIndex;

        node.right = getNode(
                inorder, rightInorderBeginIndex, rightInorderEndIndex,
                postorder, rightPostorderBeginIndex, rightPostorderEndIndex);

        int leftInorderBeginIndex = inorderBeginIndex;
        int leftInorderEndIndex = rootInorderIndex;
        int leftLength = leftInorderEndIndex - leftInorderBeginIndex;
        int leftPostorderBeginIndex = rightPostorderBeginIndex - leftLength;
        int leftPostorderEndIndex = rightPostorderBeginIndex;

        node.left = getNode(
                inorder, leftInorderBeginIndex, leftInorderEndIndex,
                postorder, leftPostorderBeginIndex, leftPostorderEndIndex);

        return node;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return getNode(inorder, 0, inorder.length,
                postorder, 0, postorder.length);
    }
}
