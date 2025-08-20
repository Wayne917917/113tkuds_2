import java.util.*;

public class M03_TopKConvenience {
    static class Item {
        String name;
        int qty;

        Item(String n, int q) {
            name = n;
            qty = q;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), K = sc.nextInt();
        PriorityQueue<Item> pq = new PriorityQueue<>((a, b) -> {
            if (a.qty != b.qty)
                return a.qty - b.qty;
            return b.name.compareTo(a.name);
        });

        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int qty = sc.nextInt();
            if (pq.size() < K)
                pq.offer(new Item(name, qty));
            else if (qty > pq.peek().qty || (qty == pq.peek().qty && name.compareTo(pq.peek().name) < 0)) {
                pq.poll();
                pq.offer(new Item(name, qty));
            }
        }

        List<Item> ans = new ArrayList<>(pq);
        ans.sort((a, b) -> b.qty == a.qty ? a.name.compareTo(b.name) : b.qty - a.qty);
        for (Item it : ans)
            System.out.println(it.name + " " + it.qty);
    }
}

/*
 * Time Complexity: O(n log K)
 * 說明：維護大小為 K 的最小堆，每筆插入 O(log K)，總共 O(n log K)。
 */
