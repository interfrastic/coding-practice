package net.avax.codingpractice.addtwonumbers;

// 2018-04-06 Rupert's "LeetCode problems for rainy days," first problem:
//
// https://leetcode.com/problems/add-two-numbers/description/
//
// 1562 / 1562 test cases passed.
// Status: Accepted
// Runtime: 58 ms
// Your runtime beats 78.48 % of java submissions.
//
// https://leetcode.com/submissions/detail/149428039/

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode place = result;
        boolean isNextPlacePresent;

        do {
            place = addListNodesToListNode(l1, l2, place);

            l1 = getNextListNode(l1);
            l2 = getNextListNode(l2);

            isNextPlacePresent = l1 != null || l2 != null;

            if (isNextPlacePresent && place.next == null) {
                place.next = new ListNode(0);
            }

            place = place.next;
        } while (isNextPlacePresent);

        return result;
    }

    private static ListNode addListNodesToListNode(ListNode l1, ListNode l2,
                                                   ListNode listNode) {
        if (l1 != null) {
            addValueToListNode(l1.val, listNode);
        }

        if (l2 != null) {
            addValueToListNode(l2.val, listNode);
        }

        return listNode;
    }

    private static ListNode addValueToListNode(int value, ListNode listNode) {
        if (listNode == null) {
            listNode = new ListNode(value);
        } else {
            int sum = value + listNode.val;
            int sumDigit = sum % 10;
            int carryDigit = sum / 10;

            listNode.val = sumDigit;

            if (carryDigit > 0) {
                listNode.next = addValueToListNode(carryDigit, listNode.next);
            }
        }

        return listNode;
    }

    private static ListNode getNextListNode(ListNode listNode) {
        ListNode nextListNode = null;

        if (listNode != null) {
            nextListNode = listNode.next;
        }

        return nextListNode;
    }
}
