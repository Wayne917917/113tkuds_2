import java.util.*;


public class LC39_CombinationSum {
public static List<List<Integer>> combinationSum(int[] candidates, int target) {
Arrays.sort(candidates);
List<List<Integer>> res = new ArrayList<>();
dfs(candidates, target, 0, new ArrayList<>(), res);
return res;
}
private static void dfs(int[] c, int remain, int idx, List<Integer> cur, List<List<Integer>> res) {
if (remain == 0) { res.add(new ArrayList<>(cur)); return; }
for (int i = idx; i < c.length && c[i] <= remain; i++) {
cur.add(c[i]);
dfs(c, remain - c[i], i, cur, res);
cur.remove(cur.size()-1);
}
}
public static void main(String[] args) {
System.out.println(combinationSum(new int[]{2,3,6,7}, 7));
}
}