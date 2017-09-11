package edu.learning.codefights.hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by duchuunguyen on 9/2/17.
 */
public class ContainsCloseNumbers {
    public static boolean containsCloseNums(int[] nums, int k) {
        if (nums.length == 0 || k == 0) {
            return false;
        }
        // process first k numbers
        Map<Integer, Boolean> map = new HashMap<>();
        for (int i = 0; i <= k && i < nums.length; i++) {
            if (map.containsKey(nums[i]) && map.get(nums[i])) {
                return true;
            }
            map.put(nums[i], Boolean.TRUE);
        }
        for (int i = 1; i < nums.length - k; i++) {
            map.put(nums[i-1], Boolean.FALSE);
            if (map.containsKey(nums[i + k]) && map.get(nums[i + k])) {
                return true;
            }
            map.put(nums[i + k], Boolean.TRUE);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2};
        int k = 2;
        System.out.println(containsCloseNums(nums, k));
    }
}