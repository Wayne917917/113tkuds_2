
public class lt_13_roman_to_integer {

    private static int val(char c) {
        switch (c) {
            case 'I': return 1;   case 'V': return 5;   case 'X': return 10;
            case 'L': return 50;  case 'C': return 100; case 'D': return 500;
            case 'M': return 1000;
        }
        return 0;
    }

    public static int romanToInt(String s) {
        int n = s.length(), ans = 0;
        for (int i = 0; i < n; i++) {
            int v = val(s.charAt(i));
            if (i + 1 < n && v < val(s.charAt(i + 1))) ans -= v;
            else ans += v;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("III"));     // 3
        System.out.println(romanToInt("LVIII"));   // 58
        System.out.println(romanToInt("MCMXCIV")); // 1994
    }
}

/*
[Explanation]
從左到右：若當前值 < 下一值則屬「減法記號」（如 IV, IX）需相減；否則相加。
Complexity：Time O(n)，Space O(1)。
*/
