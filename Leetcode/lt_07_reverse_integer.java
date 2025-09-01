public class lt_07_reverse_integer {

    public static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int digit = x % 10;
            x /= 10;

            // 溢位檢查
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && digit > 7)) {
                return 0;
            }
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && digit < -8)) {
                return 0;
            }

            rev = rev * 10 + digit;
        }
        return rev;
    }

    // 測試
    public static void main(String[] args) {
        System.out.println(reverse(123));    // 321
        System.out.println(reverse(-123));   // -321
        System.out.println(reverse(120));    // 21
        System.out.println(reverse(0));      // 0
        System.out.println(reverse(1534236469)); // 0 (溢位)
    }
}
