import java.io.*;
import java.util.*;

public class M05_GCD_LCM_Recursive {
    static long gcd(long a, long b) {
        if (b == 0)
            return Math.abs(a);
        return gcd(b, a % b);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        long g = gcd(a, b);
        long l = (a / g) * b; // 先除後乘避免溢位
        System.out.println("GCD: " + g);
        System.out.println("LCM: " + Math.abs(l));
    }
}

/*
 * Time Complexity: O(log min(a,b))
 * 說明：遞迴歐幾里得演算法每次以取餘數縮小參數，迭代次數與較小數的對數成正比。
 * LCM 以常數時間由 GCD 推得，總時間受 GCD 主導。
 */
