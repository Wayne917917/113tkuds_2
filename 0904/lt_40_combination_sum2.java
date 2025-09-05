import java.util.*;
public class lt_40_combination_sum2 {
    public static List<List<Integer>> combinationSum2(int[] candidates,int target){
        Arrays.sort(candidates); List<List<Integer>> ans=new ArrayList<>();
        dfs(candidates,target,0,new ArrayList<>(),ans); return ans;
    }
    static void dfs(int[] a,int rem,int start,List<Integer> cur,List<List<Integer>> ans){
        if(rem==0){ ans.add(new ArrayList<>(cur)); return; }
        for(int i=start;i<a.length && a[i]<=rem;i++){
            if(i>start && a[i]==a[i-1]) continue;
            cur.add(a[i]); dfs(a,rem-a[i],i+1,cur,ans); cur.remove(cur.size()-1);
        }
    }
    public static void main(String[] args) {
        System.out.println(combinationSum2(new int[]{10,1,2,7,6,1,5},8)); // [[1,1,6],[1,2,5],[1,7],[2,6]]
    }
}
