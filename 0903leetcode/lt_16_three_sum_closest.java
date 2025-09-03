import java.util.*;

public class lt_16_three_sum_closest {

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int best = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < n - 2; i++) {
            int l = i + 1, r = n - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (Math.abs(sum - target) < Math.abs(best - target)) best = sum;
                if (sum < target) l++;
                else if (sum > target) r--;
                else return sum;
            }
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println(threeSumClosest(new int[]{-1,2,1,-4}, 1)); // 2
        System.out.println(threeSumClosest(new int[]{0,0,0}, 1));     // 0
    }
}

/*
[Explanation]
排序後固定一個數，左右夾逼調整靠近 target；同時更新最接近和。
[Complexity] Time O(n^2), Space O(1).
*/
