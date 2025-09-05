public class lt_35_search_insert_position {
    public static int searchInsert(int[] nums, int target) {
        int l=0,r=nums.length;
        while(l<r){ int m=(l+r)>>>1; if(nums[m]<target) l=m+1; else r=m; }
        return l;
    }
    public static void main(String[] args) {
        System.out.println(searchInsert(new int[]{1,3,5,6},5)); // 2
        System.out.println(searchInsert(new int[]{1,3,5,6},2)); // 1
    }
}
