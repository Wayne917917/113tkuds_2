

public class lt_10_regex_match {

    public static boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        for (int j = 2; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char pc = p.charAt(j - 1);
                if (pc == '*') {
                    dp[i][j] = dp[i][j - 2];
                    char prev = p.charAt(j - 2);
                    if (prev == '.' || prev == s.charAt(i - 1)) {
                        dp[i][j] |= dp[i - 1][j];
                    }
                } else if (pc == '.' || pc == s.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        System.out.println(isMatch("aa", "a"));       // false
        System.out.println(isMatch("aa", "a*"));      // true
        System.out.println(isMatch("ab", ".*"));      // true
        System.out.println(isMatch("aab", "c*a*b"));  // true
        System.out.println(isMatch("mississippi", "mis*is*p*.")); // false
    }
}

/*
[Explanation]
DP：dp[i][j] 表示 s[0..i-1] 與 p[0..j-1] 是否完全匹配。
- 非 '*'：匹配當前字元（或 '.'）且看 dp[i-1][j-1]。
- '*'   ：兩種情況 OR：
  1) 當 0 次：dp[i][j-2]
  2) 當 >=1 次 且 前字元可匹配 s[i-1]：dp[i-1][j]
Complexity：Time O(mn)，Space O(mn)。
 */
