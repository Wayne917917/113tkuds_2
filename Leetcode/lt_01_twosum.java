import java.util.HashMap;

public class lt_01_twosum {
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }

        throw new IllegalArgumentException("No solution found");
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 7, 11, 15};
        int[] result1 = twoSum(nums1, 9);
        System.out.println("[" + result1[0] + ", " + result1[1] + "]"); // [0,1]

        int[] nums2 = {3, 2, 4};
        int[] result2 = twoSum(nums2, 6);
        System.out.println("[" + result2[0] + ", " + result2[1] + "]"); // [1,2]

        int[] nums3 = {3, 3};
        int[] result3 = twoSum(nums3, 6);
        System.out.println("[" + result3[0] + ", " + result3[1] + "]"); // [0,1]
    }
}
