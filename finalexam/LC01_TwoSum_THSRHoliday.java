import java.io.*;
import java.util.*;

/** 高鐵連假加班車 Two Sum */
public class LC01_TwoSum_THSRHoliday {
    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String next() throws IOException {
            while (st == null || !st.hasMoreElements()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }
        int nextInt() throws IOException { return Integer.parseInt(next()); }
        long nextLong() throws IOException { return Long.parseLong(next()); }
    }
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        int n = fs.nextInt(); long target = fs.nextLong();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) a[i] = fs.nextLong();

        Map<Long, Integer> need = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (need.containsKey(a[i])) {
                System.out.println(need.get(a[i]) + " " + i);
                return;
            }
            need.put(target - a[i], i);
        }
        System.out.println("-1 -1");
    }
}
