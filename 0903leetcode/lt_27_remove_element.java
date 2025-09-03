import java.util.*;

public class lt_27_remove_element {

    public static int removeElement(int[] nums, int val) {
        int k = 0;
        for (int x : nums) if (x != val) nums[k++] = x;
        return k;
    }

    public static void main(String[] args) {
        int[] a = {3,2,2,3};
        int k = removeElement(a, 3);
        System.out.println(k); // 2
        System.out.println(Arrays.toString(Arrays.copyOf(a, k))); // [2,2]
    }
}

/*
[Explanation]
穩定寫回：略過等於 val 的元素，其他覆寫到前面。
[Complexity] Time O(n), Space O(1)。
*/
