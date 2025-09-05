public class LC34_SearchRange {
public static int[] searchRange(int[] nums, int target) {
int left = lowerBound(nums, target);
int right = lowerBound(nums, target + 1) - 1;
if (left == nums.length || nums[left] != target) return new int[]{-1,-1};
return new int[]{left, Math.max(left, right)};
}
private static int lowerBound(int[] a, int x) {
int l = 0, r = a.length; // [l,r)
while (l < r) {
int m = (l + r) >>> 1;
if (a[m] < x) l = m + 1; else r = m;
}
return l;
}
public static void main(String[] args) {
int[] ans = searchRange(new int[]{5,7,7,8,8,10}, 8);
System.out.println(ans[0]+" "+ans[1]); // 3 4
}
}