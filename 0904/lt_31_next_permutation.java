import java.util.*;
public class lt_31_next_permutation {
    public static void nextPermutation(int[] nums) {
        int n = nums.length, i = n - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) i--;
        if (i >= 0) { int j = n - 1; while (nums[j] <= nums[i]) j--; swap(nums,i,j); }
        reverse(nums, i + 1, n - 1);
    }
    static void swap(int[] a,int i,int j){int t=a[i];a[i]=a[j];a[j]=t;}
    static void reverse(int[] a,int l,int r){while(l<r)swap(a,l++,r--);}
    public static void main(String[] args) {
        int[] a = {1,2,3}; nextPermutation(a); System.out.println(Arrays.toString(a)); // [1,3,2]
    }
}
