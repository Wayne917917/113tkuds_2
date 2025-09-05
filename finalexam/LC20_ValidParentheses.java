import java.util.*;


public class LC20_ValidParentheses {
public static boolean isValid(String s) {
Map<Character, Character> m = Map.of(')', '(', ']', '[', '}', '{');
Deque<Character> st = new ArrayDeque<>();
for (char c : s.toCharArray()) {
if (m.containsValue(c)) st.push(c);
else if (m.containsKey(c)) {
if (st.isEmpty() || st.pop() != m.get(c)) return false;
}
}
return st.isEmpty();
}
public static void main(String[] args) {
System.out.println(isValid("()[]{}")); // true
}
}