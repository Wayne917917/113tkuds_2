import java.util.*;
public class lt_39_combination_sum {
    public static List<List<Integer>> combinationSum(int[] candidates,int target){
        Arrays.sort(candidates); List<List<Integer>> ans=new ArrayList<>();
        dfs(candidates,target,0,new ArrayList<>(),ans); return ans;
    }
    static void dfs(int[] a,int rem,int idx,List<Integer> cur,List<List<Integer>> ans){
        if(rem==0){ ans.add(new ArrayList<>(cur)); return; }
        for(int i=idx;i<a.length && a[i]<=rem;i++){
            cur.add(a[i]); dfs(a,rem-a[i],i,cur,ans); cur.remove(cur.size()-1);
        }
    }
    public static void main(String[] args) {
        System.out.println(combinationSum(new int[]{2,3,6,7},7)); // [[2,2,3],[7]]
    }
}
