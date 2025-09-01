public class lt_05_longest_palindrome {

    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 2) return s;
        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            int len1 = expand(s, i, i);       // odd center
            int len2 = expand(s, i, i + 1);   // even center
            int len = Math.max(len1, len2);
            if (len > end - start + 1) {
                int newStart = i - (len - 1) / 2;
                int newEnd   = i + len / 2;
                start = newStart;
                end   = newEnd;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expand(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--; r++;
        }
        return r - l - 1; // 長度
    }

    // 簡單測試
    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad")); // bab 或 aba
        System.out.println(longestPalindrome("cbbd"));  // bb
        System.out.println(longestPalindrome("a"));     // a
        System.out.println(longestPalindrome("ac"));    // a 或 c
        System.out.println(longestPalindrome("abccba")); // abccba
        System.out.println(longestPalindrome("forgeeksskeegfor")); // geeksskeeg
    }
}
