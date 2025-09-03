import java.util.*;

public class lt_30_substring_with_concatenation {

    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        if (s.isEmpty() || words.length == 0) return ans;
        int wlen = words[0].length(), cnt = words.length, win = wlen * cnt;
        if (s.length() < win) return ans;

        Map<String,Integer> need = new HashMap<>();
        for (String w : words) need.put(w, need.getOrDefault(w, 0) + 1);

        for (int offset = 0; offset < wlen; offset++) {
            int left = offset, matched = 0;
            Map<String,Integer> have = new HashMap<>();
            for (int right = offset; right + wlen <= s.length(); right += wlen) {
                String w = s.substring(right, right + wlen);
                if (need.containsKey(w)) {
                    have.put(w, have.getOrDefault(w, 0) + 1);
                    if (have.get(w) <= need.get(w)) matched++;
                    while (have.get(w) > need.get(w)) {
                        String lw = s.substring(left, left + wlen);
                        have.put(lw, have.get(lw) - 1);
                        if (have.get(lw) < need.get(lw)) matched--;
                        left += wlen;
                    }
                    if (matched == cnt) {
                        ans.add(left);
                        String lw = s.substring(left, left + wlen);
                        have.put(lw, have.get(lw) - 1);
                        matched--;
                        left += wlen;
                    }
                } else {
                    have.clear(); matched = 0; left = right + wlen;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(findSubstring("barfoothefoobarman", new String[]{"foo","bar"})); // [0,9]
        System.out.println(findSubstring("wordgoodgoodgoodbestword",
                new String[]{"word","good","best","word"})); // []
    }
}

/*
[Explanation]
固定字長滑動視窗：對每個偏移跑一趟，維護當前窗口各詞頻次與匹配數。
[Complexity] Time O(n * wordLen), Space O(#distinct words)。
*/
