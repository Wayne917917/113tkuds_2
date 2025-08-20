import java.io.*;
import java.util.*;

public class M09_AVLValidate {
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

    static boolean isBST(Node x, long lo, long hi) {
        if (x == null)
            return true;
        if (x.v <= lo || x.v >= hi)
            return false;
        return isBST(x.l, lo, x.v) && isBST(x.r, x.v, hi);
    }

    static final int BAD = Integer.MIN_VALUE / 2;

    static int heightOrBad(Node x) {
        if (x == null)
            return 0;
        int hl = heightOrBad(x.l);
        if (hl == BAD)
            return BAD;
        int hr = heightOrBad(x.r);
        if (hr == BAD)
            return BAD;
        if (Math.abs(hl - hr) > 1)
            return BAD;
        return Math.max(hl, hr) + 1;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = fs.nextInt();
        Node root = build(a);
        if (!isBST(root, Long.MIN_VALUE, Long.MAX_VALUE)) {
            System.out.println("Invalid BST");
            return;
        }
        if (heightOrBad(root) == BAD) {
            System.out.println("Invalid AVL");
        } else {
            System.out.println("Valid");
        }
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
 * Time Complexity: O(n)
 * 說明：BST 驗證以界限遞迴走訪每節點一次 O(n)；AVL 驗證後序同樣每節點一次 O(n)。
 * 合併後仍為常數次走訪，整體 O(n)。
 */
