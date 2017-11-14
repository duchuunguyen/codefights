package edu.learning.codefights.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class KthLargestElement {
    static public int kthLargestElement(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(nums.length, Comparator.reverseOrder());
        heap.addAll(Arrays.stream(nums).boxed().collect(Collectors.toList()));
        int kLargest = 0;
        for (int i = 0; i < k; i++) {
            kLargest = heap.poll();
        }
        return kLargest;
    }
    static public void main(String[] args) {
        int[] nums = {7, 6, 8, 4, 5, 2, 3, 4, 1};
        int k = 2;
        kthLargestElement(nums, k);
    }
}