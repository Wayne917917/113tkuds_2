import java.io.*;
import java.util.*;

public class M11_HeapSortWithTie {
    static class Node {
        int s, idx;

        Node(int s, int i) {
            this.s = s;
            this.idx = i;
        }
    }

    static boolean greater(Node a, Node b) {
        if (a.s != b.s)
            return a.s > b.s; // 分數較大者為「大」
        return a.idx > b.idx; // 平手時索引較大者為「大」（確保輸出時較小索引在前）
    }

    static void siftDown(Node[] a, int n, int i) {
        while (true) {
            int l = 2 * i + 1, r = l + 1, best = i;
            if (l < n && greater(a[l], a[best]))
                best = l;
            if (r < n && greater(a[r], a[best]))
                best = r;
            if (best == i)
                break;
            Node tmp = a[i];
            a[i] = a[best];
            a[best] = tmp;
            i = best;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        Node[] a = new Node[n];
        for (int i = 0; i < n; i++)
            a[i] = new Node(fs.nextInt(), i);
        for (int i = (n / 2) - 1; i >= 0; i--)
            siftDown(a, n, i);
        for (int end = n - 1; end > 0; end--) {
            Node tmp = a[0];
            a[0] = a[end];
            a[end] = tmp;
            siftDown(a, end, 0);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0)
                sb.append(' ');
            sb.append(a[i].s); // 遞增
        }
        System.out.println(sb.toString());
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream is) {
            br = new BufferedReader(new InputStreamReader(is));
        }

        String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}

/*
 * Time Complexity: O(n log n)
 * 說明：建堆 O(n)，每次彈出最大元素並下沉 O(log n)，共 n 次為 O(n log n)；
 * 就地排序、額外空間 O(1)。同分時以索引較小者優先（採用「較大索引視為更大」的堆序）。
 */
