import java.util.*;


public class LC17_LetterCombinations {
private static final String[] MAP = {
"", "", "abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"
};
public static List<String> letterCombinations(String digits) {
List<String> res = new ArrayList<>();
if (digits == null || digits.isEmpty()) return res;
backtrack(digits, 0, new StringBuilder(), res);
return res;
}
private static void backtrack(String d, int i, StringBuilder sb, List<String> res) {
if (i == d.length()) { res.add(sb.toString()); return; }
String letters = MAP[d.charAt(i) - '0'];
for (char c : letters.toCharArray()) {
sb.append(c);
backtrack(d, i+1, sb, res);
sb.deleteCharAt(sb.length()-1);
}
}
public static void main(String[] args) {
System.out.println(letterCombinations("23"));
}
}