import java.util.*;


public class LC01_TwoSum_THSRHoliday {
public static int[] twoSum(int[] nums, int target) {
Map<Integer, Integer> need = new HashMap<>(); // value -> index needing this value
for (int i = 0; i < nums.length; i++) {
if (need.containsKey(nums[i])) {
return new int[]{need.get(nums[i]), i};
}
need.put(target - nums[i], i);
}
return new int[]{-1, -1};
}


public static void main(String[] args) {
// demo
int[] ans = twoSum(new int[]{2,7,11,15}, 9);
System.out.println(ans[0] + " " + ans[1]);
}
}