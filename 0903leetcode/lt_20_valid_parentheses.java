import java.util.*;

public class lt_20_valid_parentheses {

    public static boolean isValid(String s) {
        Deque<Character> st = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c=='('||c=='['||c=='{') st.push(c);
            else {
                if (st.isEmpty()) return false;
                char o = st.pop();
                if ((o=='(' && c!=')') || (o=='[' && c!=']') || (o=='{' && c!='}')) return false;
            }
        }
        return st.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValid("()[]{}")); // true
        System.out.println(isValid("(]"));     // false
    }
}

/*
[Explanation]
堆疊匹配括號對；遇到右括號需與棧頂成對。
[Complexity] Time O(n), Space O(n).
*/
