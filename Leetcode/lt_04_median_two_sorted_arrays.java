public class lt_04_median_two_sorted_arrays {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 確保 nums1 較短，降低二分範圍
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int m = nums1.length, n = nums2.length;
        int totalLeft = (m + n + 1) / 2;

        int lo = 0, hi = m;
        while (lo <= hi) {
            int i = (lo + hi) >>> 1;          // nums1 的切點
            int j = totalLeft - i;            // nums2 的切點

            int left1  = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int right1 = (i == m) ? Integer.MAX_VALUE : nums1[i];
            int left2  = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int right2 = (j == n) ? Integer.MAX_VALUE : nums2[j];

            // 條件滿足：左邊都 ≤ 右邊
            if (left1 <= right2 && left2 <= right1) {
                if (((m + n) & 1) == 1) {
                    return Math.max(left1, left2);
                } else {
                    return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
                }
            }
            // i 太大，往左縮
            else if (left1 > right2) {
                hi = i - 1;
            }
            // i 太小，往右擴
            else {
                lo = i + 1;
            }
        }
        // 依題意不會到這
        throw new IllegalArgumentException("Invalid input arrays.");
    }

    // ====== 測試 ======
    public static void main(String[] args) {
        System.out.printf("%.5f%n", findMedianSortedArrays(new int[]{1,3}, new int[]{2}));          // 2.00000
        System.out.printf("%.5f%n", findMedianSortedArrays(new int[]{1,2}, new int[]{3,4}));        // 2.50000
        System.out.printf("%.5f%n", findMedianSortedArrays(new int[]{}, new int[]{1}));             // 1.00000
        System.out.printf("%.5f%n", findMedianSortedArrays(new int[]{0,0}, new int[]{0,0}));        // 0.00000
        System.out.printf("%.5f%n", findMedianSortedArrays(new int[]{2}, new int[]{1,3,4,5,6}));    // 3.00000
    }
}
