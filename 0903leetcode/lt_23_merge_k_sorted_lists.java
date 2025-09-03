import java.util.*;

public class lt_23_merge_k_sorted_lists {

    static class ListNode {
        int val; ListNode next;
        ListNode(int v){ val=v; }
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode node : lists) if (node != null) pq.offer(node);
        ListNode dummy = new ListNode(0), cur = dummy;
        while (!pq.isEmpty()) {
            ListNode t = pq.poll();
            cur.next = t;
            cur = cur.next;
            if (t.next != null) pq.offer(t.next);
        }
        return dummy.next;
    }

    private static ListNode build(int... a){
        ListNode d=new ListNode(0),c=d; for(int v:a){ c.next=new ListNode(v); c=c.next; } return d.next;
    }
    private static void print(ListNode h){
        while(h!=null){ System.out.print(h.val+(h.next!=null?"->":"")); h=h.next; } System.out.println();
    }

    public static void main(String[] args) {
        ListNode[] lists = new ListNode[]{ build(1,4,5), build(1,3,4), build(2,6) };
        print(mergeKLists(lists)); // 1->1->2->3->4->4->5->6
    }
}

/*
[Explanation]
小根堆存各鏈表當前節點，每次彈出最小並推入其 next。
[Complexity] Time O(N log k), Space O(k)。N 總節點數。
*/
