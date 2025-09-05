public class LC28_StrStr_KMP {
public static int strStr(String haystack, String needle) {
if (needle.isEmpty()) return 0;
int m = needle.length();
int[] lps = new int[m];
for (int i = 1, len = 0; i < m; ) {
if (needle.charAt(i) == needle.charAt(len)) lps[i++] = ++len;
else if (len > 0) len = lps[len-1];
else lps[i++] = 0;
}
for (int i = 0, j = 0; i < haystack.length(); ) {
if (haystack.charAt(i) == needle.charAt(j)) { i++; j++; if (j == m) return i - m; }
else if (j > 0) j = lps[j-1]; else i++;
}
return -1;
}
public static void main(String[] args) {
System.out.println(strStr("sadbutsad", "sad")); // 0
}
}