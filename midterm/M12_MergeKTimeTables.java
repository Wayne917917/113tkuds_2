import java.io.*;
import java.util.*;

public class M12_MergeKTimeTables {
    static class Node {
        int time, li, idx;

        Node(int t, int l, int i) {
            time = t;
            li = l;
            idx = i;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int K = fs.nextInt();
        List<int[]> lists = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            int len = fs.nextInt();
            int[] arr = new int[len];
            for (int j = 0; j < len; j++)
                arr[j] = fs.nextInt();
            lists.add(arr);
        }
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.time));
        for (int i = 0; i < K; i++)
            if (lists.get(i).length > 0) {
                pq.offer(new Node(lists.get(i)[0], i, 0));
            }
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (!first)
                sb.append(' ');
            sb.append(cur.time);
            first = false;
            int[] arr = lists.get(cur.li);
            int ni = cur.idx + 1;
            if (ni < arr.length)
                pq.offer(new Node(arr[ni], cur.li, ni));
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
