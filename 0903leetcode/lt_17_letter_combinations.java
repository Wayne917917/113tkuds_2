import java.util.*;

public class lt_17_letter_combinations {

    private static final String[] MAP = {
        "", "", "abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"
    };

    public static List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits == null || digits.isEmpty()) return ans;
        dfs(digits, 0, new StringBuilder(), ans);
        return ans;
    }

    private static void dfs(String d, int i, StringBuilder path, List<String> ans) {
        if (i == d.length()) { ans.add(path.toString()); return; }
        for (char c : MAP[d.charAt(i) - '0'].toCharArray()) {
            path.append(c);
            dfs(d, i + 1, path, ans);
            path.deleteCharAt(path.length() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(letterCombinations("23")); // [ad, ae, af, bd, ...]
        System.out.println(letterCombinations(""));   // []
    }
}

/*
[Explanation]
回溯生成所有組合；每位數對應一組字母。
[Complexity] Time O(3^a * 4^b), Space O(L) 路徑深度。
*/
