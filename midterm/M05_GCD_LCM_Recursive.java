import java.util.*;

public class M05_GCD_LCM_Recursive {
    static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong(), b = sc.nextLong();
        long g = gcd(a, b);
        long l = (a / g) * b; // 避免溢位：先除後乘
        System.out.println("GCD: " + g);
        System.out.println("LCM: " + Math.abs(l));
    }
}

/*
 * Time Complexity: O(log min(a,b))
 * 說明：歐幾里得演算法每次取餘數，最多 log(min(a,b)) 次遞迴。
 */
