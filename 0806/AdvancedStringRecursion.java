
public class AdvancedStringRecursion {
    public static void permute(String str, String out) {
        if (str.length() == 0) {
            System.out.println(out);
            return;
        }
        for (int i = 0; i < str.length(); i++)
            permute(str.substring(0, i) + str.substring(i + 1), out + str.charAt(i));
    }

    public static boolean match(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();
        boolean firstMatch = (!text.isEmpty() &&
                              (text.charAt(0) == pattern.charAt(0) || pattern.charAt(0) == '.'));
        if (pattern.length() >= 2 && pattern.charAt(1) == '*')
            return match(text, pattern.substring(2)) || (firstMatch && match(text.substring(1), pattern));
        else
            return firstMatch && match(text.substring(1), pattern.substring(1));
    }

    public static String removeDuplicates(String s) {
        if (s.length() <= 1) return s;
        if (s.substring(1).contains(s.charAt(0) + ""))
            return removeDuplicates(s.substring(1));
        else
            return s.charAt(0) + removeDuplicates(s.substring(1));
    }

    public static void substrings(String prefix, String rest) {
        if (rest.isEmpty()) return;
        for (int i = 1; i <= rest.length(); i++)
            System.out.println(prefix + rest.substring(0, i));
        substrings(prefix + rest.charAt(0), rest.substring(1));
    }

    public static void main(String[] args) {
        System.out.println("Permutations of 'abc':");
        permute("abc", "");

        System.out.println("\nPattern match 'a*b' vs 'aaab': " + match("aaab", "a*b"));

        System.out.println("\nRemove duplicates from 'banana': " + removeDuplicates("banana"));

        System.out.println("\nSubstrings of 'abc':");
        substrings("", "abc");
    }
}
