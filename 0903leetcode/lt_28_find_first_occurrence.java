public class lt_28_find_first_occurrence {

    public static int strStr(String haystack, String needle) {
        if (needle.isEmpty()) return 0;
        int[] lps = buildLPS(needle);
        int i = 0, j = 0;
        while (i < haystack.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++; j++;
                if (j == needle.length()) return i - j;
            } else if (j > 0) {
                j = lps[j - 1];
            } else {
                i++;
            }
        }
        return -1;
    }

    private static int[] buildLPS(String p) {
        int n = p.length(), len = 0;
        int[] lps = new int[n];
        for (int i = 1; i < n; ) {
            if (p.charAt(i) == p.charAt(len)) lps[i++] = ++len;
            else if (len > 0) len = lps[len - 1];
            else lps[i++] = 0;
        }
        return lps;
    }

    public static void main(String[] args) {
        System.out.println(strStr("sadbutsad", "sad")); // 0
        System.out.println(strStr("leetcode", "leeto")); // -1
    }
}

/*
[Explanation]
KMP：利用部分匹配表（LPS）在失配時跳過可行前綴，保證線性時間。
[Complexity] Time O(n+m), Space O(m)。
*/
