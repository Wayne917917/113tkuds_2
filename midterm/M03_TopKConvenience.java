import java.io.*;
import java.util.*;

public class M03_TopKConvenience {
    static class Item {
        String name;
        int qty, idx;

        Item(String n, int q, int i) {
            name = n;
            qty = q;
            idx = i;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt(), K = fs.nextInt();
        PriorityQueue<Item> pq = new PriorityQueue<>((a, b) -> {
            if (a.qty != b.qty)
                return Integer.compare(a.qty, b.qty); // smaller qty is worse
            return b.name.compareTo(a.name); // lexicographically larger is worse
        });
        for (int i = 0; i < n; i++) {
            String name = fs.next();
            int qty = fs.nextInt();
            Item it = new Item(name, qty, i);
            if (pq.size() < K)
                pq.offer(it);
            else {
                Item worst = pq.peek();
                if (qty > worst.qty || (qty == worst.qty && name.compareTo(worst.name) < 0)) {
                    pq.poll();
                    pq.offer(it);
                }
            }
        }
        ArrayList<Item> ans = new ArrayList<>(pq);
        ans.sort((a, b) -> {
            if (b.qty != a.qty)
                return Integer.compare(b.qty, a.qty);
            return a.name.compareTo(b.name); // tie: name asc
        });
        StringBuilder sb = new StringBuilder();
        for (Item it : ans)
            sb.append(it.name).append(' ').append(it.qty).append('\n');
        System.out.print(sb.toString());
    }

    static class FastScanner {
        BufferedInputStream in;
        byte[] buf = new byte[1 << 16];
        int ptr = 0, len = 0;

        FastScanner(InputStream is) {
            in = new BufferedInputStream(is);
        }

        int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buf);
                ptr = 0;
                if (len <= 0)
                    return -1;
            }
            return buf[ptr++];
        }

        String next() throws IOException {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = read()) <= 32 && c != -1)
                ;
            if (c == -1)
                return null;
            do {
                sb.append((char) c);
                c = read();
            } while (c > 32);
            return sb.toString();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}

/*
 * Time Complexity: O(n log K) + O(K log K)
 * 說明：逐筆維護大小為 K 的最小堆，每筆插入/替換成本 O(log K)，總計 O(n log K)；
 * 輸出前對 K 筆結果排序 O(K log K)。當 K ≪ n 時效率佳。
 */
