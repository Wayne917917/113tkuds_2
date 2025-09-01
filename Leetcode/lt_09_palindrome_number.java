public class lt_09_palindrome_number {

    public static boolean isPalindrome(int x) {
        // 負數或 (尾數是0 且不是0本身) 一定不是迴文
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int reversed = 0;
        while (x > reversed) {
            reversed = reversed * 10 + x % 10;
            x /= 10;
        }

        // 偶數長度: x == reversed
        // 奇數長度: x == reversed/10 (去掉中間位)
        return (x == reversed || x == reversed / 10);
    }

    // 測試
    public static void main(String[] args) {
        System.out.println(isPalindrome(121));   // true
        System.out.println(isPalindrome(-121));  // false
        System.out.println(isPalindrome(10));    // false
        System.out.println(isPalindrome(0));     // true
        System.out.println(isPalindrome(12321)); // true
    }
}
