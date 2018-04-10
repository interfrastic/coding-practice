package net.avax.codingpractice.addtwonumbers;

import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    private static void print(ListNode listNode) {
        Deque<Integer> places = new ArrayDeque<>();

        while (listNode != null) {
            places.push(listNode.val);
            listNode = listNode.next;
        }

        System.out.print(places);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        print(l1);
        System.out.print(" + ");
        print(l2);
        System.out.print(" = ");
        print(solution.addTwoNumbers(l1, l2));
        System.out.println();

        l1 = new ListNode(1);

        l2 = new ListNode(9);
        l2.next = new ListNode(9);

        print(l1);
        System.out.print(" + ");
        print(l2);
        System.out.print(" = ");
        print(solution.addTwoNumbers(l1, l2));
        System.out.println();

        l1 = new ListNode(0);
        l1.next = new ListNode(0);
        l1.next.next = new ListNode(1);

        l2 = new ListNode(0);
        l2.next = new ListNode(0);
        l2.next.next = new ListNode(0);
        l2.next.next.next = new ListNode(1);

        print(l1);
        System.out.print(" + ");
        print(l2);
        System.out.print(" = ");
        print(solution.addTwoNumbers(l1, l2));
        System.out.println();

        l1 = new ListNode(0);
        l2 = new ListNode(0);

        print(l1);
        System.out.print(" + ");
        print(l2);
        System.out.print(" = ");
        print(solution.addTwoNumbers(l1, l2));
        System.out.println();
    }
}
