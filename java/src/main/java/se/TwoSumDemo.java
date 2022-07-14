package se;

import java.util.HashMap;
import java.util.Map;

public class TwoSumDemo {
    public static int[] twoSum1(int[] nums, int target) {   //暴力算法
        for (int i = 0; i < nums.length; i++) { //通过双重循环遍历数组中的两两组合
            for (int j = i + 1; j < nums.length; j++) {
                if (target - nums[i] == nums[j]) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static int[] twoSum2(int[] nums, int target) {   //哈希算法
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int partnerNumber = target - nums[i];
            if (map.containsKey(partnerNumber)) {
                return new int[]{map.get(partnerNumber), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;

        int[] myIndex = twoSum2(nums, target);
        for (int element : myIndex) {
            System.out.println(element);
        }
    }
}
