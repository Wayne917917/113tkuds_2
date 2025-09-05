public class lt_34_search_range {
    static int[] searchRange(int[] nums, int target) { return new int[]{bound(nums,target,true), bound(nums,target,false)}; }
    static int bound(int[] a,int x,boolean lower){
        int l=0,r=a.length-1,ans=-1;
        while(l<=r){
            int m=(l+r)>>>1;
            if(a[m]>x || (lower && a[m]==x)){ if(a[m]==x) ans=m; r=m-1; }
            else { if(!lower && a[m]==x) ans=m; l=m+1; }
        }
        return ans;
    }
    public static void main(String[] args) {
        System.out.println(java.util.Arrays.toString(searchRange(new int[]{5,7,7,8,8,10},8))); // [3,4]
    }
}
