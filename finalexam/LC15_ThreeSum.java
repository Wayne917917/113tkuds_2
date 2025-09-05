import java.util.*;


public class LC15_ThreeSum {
public static List<List<Integer>> threeSum(int[] nums) {
Arrays.sort(nums);
List<List<Integer>> res = new ArrayList<>();
for (int i = 0; i < nums.length; i++) {
if (i > 0 && nums[i] == nums[i-1]) continue;
int l = i + 1, r = nums.length - 1;
while (l < r) {
int s = nums[i] + nums[l] + nums[r];
if (s == 0) {
res.add(Arrays.asList(nums[i], nums[l], nums[r]));
l++; r--;
while (l < r && nums[l] == nums[l-1]) l++;
while (l < r && nums[r] == nums[r+1]) r--;
} else if (s < 0) l++; else r--;
}
}
return res;
}
public static void main(String[] args) {
System.out.println(threeSum(new int[]{-1,0,1,2,-1,-4}));
}
}