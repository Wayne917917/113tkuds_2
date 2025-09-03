import java.util.*;

public class lt_22_generate_parentheses {

    public static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        backtrack(n, 0, 0, new StringBuilder(), ans);
        return ans;
    }

    private static void backtrack(int n, int open, int close, StringBuilder sb, List<String> ans) {
        if (sb.length() == 2*n) { ans.add(sb.toString()); return; }
        if (open < n) { sb.append('('); backtrack(n, open+1, close, sb, ans); sb.deleteCharAt(sb.length()-1); }
        if (close < open) { sb.append(')'); backtrack(n, open, close+1, sb, ans); sb.deleteCharAt(sb.length()-1); }
    }

    public static void main(String[] args) {
        System.out.println(generateParenthesis(3)); // ["((()))","(()())","(())()","()(())","()()()"]
    }
}

/*
[Explanation]
回溯：可放 '(' 的前提是 open<n；可放 ')' 的前提是 close<open。
[Complexity] Catalan 數量級，Space O(n) 深度。
*/
