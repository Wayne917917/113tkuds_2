
public class lt_14_longest_common_prefix {

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String pre = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (!strs[i].startsWith(pre)) {
                pre = pre.substring(0, pre.length() - 1);
                if (pre.isEmpty()) return "";
            }
        }
        return pre;
    }

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"flower","flow","flight"})); // "fl"
        System.out.println(longestCommonPrefix(new String[]{"dog","racecar","car"}));    // ""
    }
}

/*
[Explanation]
以首字串為候選前綴，逐一縮短直到成為所有字串的前綴。
可替代法：垂直掃描或二分答案。
Complexity：最壞 O(Σ字元數)，Space O(1)（不計回傳值）。
*/
