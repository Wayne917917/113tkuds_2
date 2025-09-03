import java.util.*;

public class lt_18_four_sum {

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i-1]) continue;
            for (int j = i + 1; j < n; j++) {
                if (j > i + 1 && nums[j] == nums[j-1]) continue;
                int l = j + 1, r = n - 1;
                while (l < r) {
                    long sum = 1L*nums[i] + nums[j] + nums[l] + nums[r];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                        while (l < r && nums[l] == nums[l+1]) l++;
                        while (l < r && nums[r] == nums[r-1]) r--;
                        l++; r--;
                    } else if (sum < target) l++;
                    else r--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(fourSum(new int[]{1,0,-1,0,-2,2}, 0)); // [[-2,-1,1,2],...]
        System.out.println(fourSum(new int[]{2,2,2,2,2}, 8));     // [[2,2,2,2]]
    }
}

/*
[Explanation]
排序＋雙層固定＋內層雙指針；注意去重與 long 防溢位。
[Complexity] Time O(n^3), Space O(1)（不含輸出）。
*/
