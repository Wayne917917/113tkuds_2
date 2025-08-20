import java.io.*;
import java.util.*;

public class M07_BinaryTreeLeftView {
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

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = Integer.parseInt(fs.next());
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = Integer.parseInt(fs.next());
        Node root = build(a);
        StringBuilder out = new StringBuilder();
        out.append("LeftView:");
        if (root != null) {
            Queue<Node> q = new ArrayDeque<>();
            q.add(root);
            boolean first;
            while (!q.isEmpty()) {
                int sz = q.size();
                first = true;
                for (int i = 0; i < sz; i++) {
                    Node cur = q.poll();
                    if (first) {
                        out.append(' ').append(cur.v);
                        first = false;
                    }
                    if (cur.l != null)
                        q.add(cur.l);
                    if (cur.r != null)
                        q.add(cur.r);
                }
            }
        }
        System.out.println(out.toString());
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
    }
}
