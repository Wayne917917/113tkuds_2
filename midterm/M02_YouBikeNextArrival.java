import java.io.*;
import java.util.*;

public class M02_YouBikeNextArrival {
    static int toMin(String s) {
        String[] p = s.trim().split(":");
        return Integer.parseInt(p[0]) * 60 + Integer.parseInt(p[1]);
    }

    static String toHHMM(int m) {
        int h = m / 60, mm = m % 60;
        return String.format("%02d:%02d", h, mm);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null && line.trim().isEmpty())
            ;
        int n = Integer.parseInt(line.trim());
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = toMin(br.readLine());
        String qStr;
        while ((qStr = br.readLine()) != null && qStr.trim().isEmpty())
            ;
        int q = toMin(qStr.trim());

        int l = 0, r = n; // upper_bound (first > q)
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (a[mid] <= q)
                l = mid + 1;
            else
                r = mid;
        }
        if (l == n)
            System.out.println("No bike");
        else
            System.out.println(toHHMM(a[l]));
    }
}
