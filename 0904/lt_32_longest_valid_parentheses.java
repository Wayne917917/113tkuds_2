import java.util.*;
public class lt_32_longest_valid_parentheses {
    public static int longestValidParentheses(String s) {
        Deque<Integer> st = new ArrayDeque<>(); st.push(-1);
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') st.push(i);
            else { st.pop(); if (st.isEmpty()) st.push(i); else ans = Math.max(ans, i - st.peek()); }
        }
        return ans;
    }
    public static void main(String[] args) {
        System.out.println(longestValidParentheses(")()())")); // 4
    }
}
