import java.io.*;
import java.util.*;

public class M06_PalindromeClean {
    static boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    static boolean rec(char[] s, int l, int r) {
        if (l >= r)
            return true;
        if (s[l] != s[r])
            return false;
        return rec(s, l + 1, r - 1);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        if (s == null) {
            System.out.println("No");
            return;
        }
        StringBuilder t = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isLetter(c))
                t.append(Character.toLowerCase(c));
        }
        char[] arr = t.toString().toCharArray();
        System.out.println(rec(arr, 0, arr.length - 1) ? "Yes" : "No");
    }
}
