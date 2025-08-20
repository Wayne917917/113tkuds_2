import java.io.*;
import java.util.*;

public class M10_RBPropertiesCheck {
    static int n;
    static int[] val;
    static char[] col;

    static boolean isNull(int i) {
        return i >= n || val[i] == -1;
    }

    static boolean isRed(int i) {
        return !isNull(i) && col[i] == 'R';
    }

    static boolean isBlack(int i) {
        return isNull(i) || col[i] == 'B';
    }

    static int blackHeight(int i) {
        if (i >= n || val[i] == -1)
            return 1; // NIL 為黑
        int L = 2 * i + 1, R = 2 * i + 2;
        int bl = blackHeight(L);
        if (bl == -1)
            return -1;
        int br = blackHeight(R);
        if (br == -1)
            return -1;
        if (bl != br)
            return -1;
        return bl + (col[i] == 'B' ? 1 : 0);
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        n = fs.nextInt();
        val = new int[n];
        col = new char[n];
        for (int i = 0; i < n; i++) {
            val[i] = fs.nextInt();
            String c = fs.next();
            col[i] = (c == null || c.isEmpty()) ? 'B' : c.charAt(0);
            if (val[i] == -1)
                col[i] = 'B'; // 空節點視為黑
        }
        // 1) 根為黑
        if (n > 0 && val[0] != -1 && col[0] != 'B') {
            System.out.println("RootNotBlack");
            return;
        }
        // 2) 不得紅紅相鄰
        for (int i = 0; i < n; i++)
            if (isRed(i)) {
                int L = 2 * i + 1, R = 2 * i + 2;
                if (L < n && isRed(L)) {
                    System.out.println("RedRedViolation at index " + L);
                    return;
                }
                if (R < n && isRed(R)) {
                    System.out.println("RedRedViolation at index " + R);
                    return;
                }
            }
        // 3) 黑高度一致
        if (blackHeight(0) == -1)
            System.out.println("BlackHeightMismatch");
        else
            System.out.println("RB Valid");
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
