import java.io.*;
import java.util.*;

public class M04_TieredTaxSimple {
    static long tax(long x) {
        long t = 0;
        long a = Math.min(x, 120000);
        t += a * 5 / 100;
        if (x > 120000) {
            long b = Math.min(x, 500000) - 120000;
            t += b * 12 / 100;
        }
        if (x > 500000) {
            long c = Math.min(x, 1000000) - 500000;
            t += c * 20 / 100;
        }
        if (x > 1000000) {
            long d = x - 1000000;
            t += d * 30 / 100;
        }
        return t;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        long sum = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            long x = Long.parseLong(br.readLine().trim());
            long t = tax(x);
            sum += t;
            sb.append("Tax: ").append(t).append('\n');
        }
        sb.append("Average: ").append(sum / n);
        System.out.print(sb.toString());
    }
}

/*
 * Time Complexity: O(n)
 * 說明：每筆收入以常數段距計算稅額，單筆 O(1)，總計走訪 n 筆為 O(n)。
 */
