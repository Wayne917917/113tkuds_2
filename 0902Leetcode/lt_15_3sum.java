import java.util.*;

public class lt_15_3sum {

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 跳過重複 a
            int l = i + 1, r = n - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l < r && nums[l] == nums[l + 1]) l++; // 去重 b
                    while (l < r && nums[r] == nums[r - 1]) r--; // 去重 c
                    l++; r--;
                } else if (sum < 0) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return res;
    }

    private static void printTriplets(List<List<Integer>> res) {
        System.out.print("[");
        for (int i = 0; i < res.size(); i++) {
            System.out.print(res.get(i));
            if (i + 1 < res.size()) System.out.print(", ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        printTriplets(threeSum(new int[]{-1,0,1,2,-1,-4})); // [[-1,-1,2], [-1,0,1]]
        printTriplets(threeSum(new int[]{0,1,1}));           // []
        printTriplets(threeSum(new int[]{0,0,0}));           // [[0,0,0]]
    }
}

/*
[Explanation]
排序後固定 a，用雙指針在右側找 b, c 使 a+b+c=0。
sum<0 → 左指針右移；sum>0 → 右指針左移；=0 → 收錄並去重。
Complexity：Time O(n^2)，Space O(1)（不含輸出）。
*/
