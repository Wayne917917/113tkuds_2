import java.util.*;


public class LC32_LongestValidParentheses {
public static int longestValidParentheses(String s) {
int best = 0;
Deque<Integer> st = new ArrayDeque<>();
st.push(-1);
for (int i = 0; i < s.length(); i++) {
if (s.charAt(i) == '(') st.push(i);
else {
st.pop();
if (st.isEmpty()) st.push(i); // reset base
else best = Math.max(best, i - st.peek());
}
}
return best;
}
public static void main(String[] args) {
System.out.println(longestValidParentheses(")()())")); // 4
}
}