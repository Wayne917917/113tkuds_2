import java.util.*;


public class LC40_CombinationSum2_Procurement {
public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
Arrays.sort(candidates);
List<List<Integer>> res = new ArrayList<>();
backtrack(candidates, target, 0, new ArrayList<>(), res);
return res;
}


private static void backtrack(int[] c, int remain, int start, List<Integer> path, List<List<Integer>> res) {
if (remain == 0) { res.add(new ArrayList<>(path)); return; }
for (int i = start; i < c.length && c[i] <= remain; i++) {
if (i > start && c[i] == c[i - 1]) continue; // skip dups at same depth
path.add(c[i]);
backtrack(c, remain - c[i], i + 1, path, res); // use once => i+1
path.remove(path.size() - 1);
}
}


public static void main(String[] args) {
int[] cand = {10,1,2,7,6,1,5};
List<List<Integer>> ans = combinationSum2(cand, 8);
System.out.println(ans); // [[1,1,6], [1,2,5], [1,7], [2,6]] (順序可能不同)
}
}