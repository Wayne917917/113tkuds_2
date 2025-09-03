public class lt_29_divide_two_integers {

    public static int divide(int dividend, int divisor) {
        if (divisor == 0) throw new ArithmeticException("Divide by zero");
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;

        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);
        int sign = ((dividend < 0) ^ (divisor < 0)) ? -1 : 1;

        int res = 0;
        for (int shift = 31; shift >= 0; shift--) {
            if ((a >> shift) >= b) {
                a -= (b << shift);
                res += (1 << shift);
            }
        }
        return sign == 1 ? res : -res;
    }

    public static void main(String[] args) {
        System.out.println(divide(10, 3));   // 3
        System.out.println(divide(7, -3));   // -2
        System.out.println(divide(-2147483648, -1)); // 2147483647 (clamped)
    }
}

/*
[Explanation]
二進位位移減法：找最大 b<<k ≤ a，累積商並扣除；處理符號與特殊溢位情況。
[Complexity] Time O(32)≈O(1), Space O(1)。
*/
