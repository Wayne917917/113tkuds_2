import java.util.*;

public class lt_26_remove_duplicates_sorted_array {

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int slow = 1;
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast] != nums[fast - 1]) nums[slow++] = nums[fast];
        }
        return slow;
    }

    public static void main(String[] args) {
        int[] a = {1,1,2,2,3};
        int k = removeDuplicates(a);
        System.out.println(k); // 3
        System.out.println(Arrays.toString(Arrays.copyOf(a, k))); // [1,2,3]
    }
}

/*
[Explanation]
雙指針：快指針掃描，新值寫到慢指針位置。
[Complexity] Time O(n), Space O(1)。
*/
