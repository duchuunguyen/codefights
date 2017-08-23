package edu.learning.codefights.linkedlist;

/**
 * Created by duchuunguyen on 7/28/17.
 */
public class RearrangeLastN {
    public static ListNode<Integer> rearrangeLastN(ListNode<Integer> l, int n) {
        if (n == 0)
            return l;
        ListNode<Integer> kPointer = l;
        for (int i = 0; i < n; i++) {
            if (kPointer == null)
                return l;
            kPointer = kPointer.next;
        }
        ListNode<Integer> nPointer = l;
        if (kPointer == null) {
            return l;
        }
        while (kPointer.next != null) {
            kPointer = kPointer.next;
            nPointer = nPointer.next;
        }

        ListNode<Integer> result = nPointer.next;
        nPointer.next = null;
        kPointer.next = l;
        return result;
    }
    public static void main(String[] args) {
        ListNode<Integer> node0 = new ListNode<Integer>(1);
        ListNode<Integer> node1 = new ListNode<Integer>(2);
        ListNode<Integer> node2 = new ListNode<Integer>(3);
        ListNode<Integer> node3 = new ListNode<Integer>(4);
        ListNode<Integer> node4 = new ListNode<Integer>(5);
        ListNode<Integer> node5 = new ListNode<Integer>(6);

        node0.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode<Integer> r = rearrangeLastN(node0, 6);
        System.out.println();
    }
}
