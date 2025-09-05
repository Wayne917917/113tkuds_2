import java.util.*;


public class LC03_LongestSubstring {
public static int lengthOfLongestSubstring(String s) {
int left = 0, best = 0;
int[] last = new int[256];
Arrays.fill(last, -1);
for (int r = 0; r < s.length(); r++) {
char c = s.charAt(r);
if (last[c] >= left) left = last[c] + 1;
last[c] = r;
best = Math.max(best, r - left + 1);
}
return best;
}
public static void main(String[] args) {
System.out.println(lengthOfLongestSubstring("abcabcbb")); // 3
}
}