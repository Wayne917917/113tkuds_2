import java.io.*;
import java.util.*;

public class M01_BuildHeap {
    static boolean better(int a, int b, boolean isMax) {
        return isMax ? a > b : a < b;
    }

    static void heapifyDown(int[] a, int n, int i, boolean isMax) {
        while (true) {
            int l = 2 * i + 1, r = l + 1, best = i;
            if (l < n && better(a[l], a[best], isMax))
                best = l;
            if (r < n && better(a[r], a[best], isMax))
                best = r;
            if (best == i)
                break;
            int tmp = a[i];
            a[i] = a[best];
            a[best] = tmp;
            i = best;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        String type = fs.next();
        int n = fs.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = fs.nextInt();
        boolean isMax = "max".equalsIgnoreCase(type);

        for (int i = (n / 2) - 1; i >= 0; i--)
            heapifyDown(a, n, i, isMax);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0)
                sb.append(' ');
            sb.append(a[i]);
        }
        System.out.println(sb.toString());
    }

    // FastScanner
    static class FastScanner {
        BufferedInputStream in;
        byte[] buffer = new byte[1 << 16];
        int ptr = 0, len = 0;

        FastScanner(InputStream is) {
            in = new BufferedInputStream(is);
        }

        int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0)
                    return -1;
            }
            return buffer[ptr++];
        }

        String next() throws IOException {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = read()) <= ' ' && c != -1)
                ;
            if (c == -1)
                return null;
            do {
                sb.append((char) c);
                c = read();
            } while (c > ' ');
            return sb.toString();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}

/*
 * Time Complexity: O(n)
 * 說明：自底向上建堆，對於高度 h 的節點最多下沉 h 次；所有節點之高度加總為 O(n)。
 * 因此整體堆化成本 Σ O(h_i) = O(n)，優於逐一插入的 O(n log n)。
 */
