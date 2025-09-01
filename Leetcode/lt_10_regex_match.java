public class lt_10_regex_match {

    public static boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        // 初始化：處理像 a*、a*b* 這種空字串能匹配
        for (int j = 2; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char pc = p.charAt(j - 1);
                if (pc == '*') {
                    // 當 '*' 當作 0 次前一個字元
                    dp[i][j] = dp[i][j - 2];
                    // 當 '*' 當作 >=1 次前一個字元
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

    // 測試
    public static void main(String[] args) {
        System.out.println(isMatch("aa", "a"));    // false
        System.out.println(isMatch("aa", "a*"));   // true
        System.out.println(isMatch("ab", ".*"));   // true
        System.out.println(isMatch("aab", "c*a*b")); // true
        System.out.println(isMatch("mississippi", "mis*is*p*.")); // false
    }
}
