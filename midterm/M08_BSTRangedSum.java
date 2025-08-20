import java.io.*;
import java.util.*;

public class M08_BSTRangedSum {
    static class Node {
        int v;
        Node l, r;

        Node(int v) {
            this.v = v;
        }
    }

    static Node build(int[] a) {
        int n = a.length;
        if (n == 0 || a[0] == -1)
            return null;
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++)
            if (a[i] != -1)
                nodes[i] = new Node(a[i]);
        for (int i = 0; i < n; i++)
            if (nodes[i] != null) {
                int L = 2 * i + 1, R = 2 * i + 2;
                if (L < n)
                    nodes[i].l = nodes[L];
                if (R < n)
                    nodes[i].r = nodes[R];
            }
        return nodes[0];
    }

    static long sum(Node x, int L, int R) {
        if (x == null)
            return 0;
        if (x.v < L)
            return sum(x.r, L, R);
        if (x.v > R)
            return sum(x.l, L, R);
        return x.v + sum(x.l, L, R) + sum(x.r, L, R);
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = fs.nextInt();
        int L = fs.nextInt(), R = fs.nextInt();
        Node root = build(a);
        System.out.println("Sum: " + sum(root, L, R));
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
