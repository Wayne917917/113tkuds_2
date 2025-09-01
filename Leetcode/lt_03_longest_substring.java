public class lt_03_longest_substring {

    // 回傳最長不重複子字串長度
    public static int lengthOfLongestSubstring(String s) {
        // 題目字元為英數/符號/空白（ASCII 足夠），用 256 長度表即可
        int[] last = new int[256]; // 存最後出現位置+1，預設 0
        int left = 0;              // 視窗左界（0-based）
        int ans = 0;

        for (int right = 0; right < s.length(); right++) {
            int c = s.charAt(right);
            // 若此字元上次出現在左界右邊，縮小左界
            left = Math.max(left, last[c]);
            // 更新答案（right 是 0-based，長度需 +1；left 已是索引）
            ans = Math.max(ans, right - left + 1);
            // 紀錄此字元最新出現位置的「下一格」
            last[c] = right + 1;
        }
        return ans;
    }

    // 簡單測試
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb")); // 3
        System.out.println(lengthOfLongestSubstring("bbbbb"));    // 1
        System.out.println(lengthOfLongestSubstring("pwwkew"));   // 3
        System.out.println(lengthOfLongestSubstring(""));         // 0
        System.out.println(lengthOfLongestSubstring("au"));       // 2
        System.out.println(lengthOfLongestSubstring("abba"));     // 2
    }
}
