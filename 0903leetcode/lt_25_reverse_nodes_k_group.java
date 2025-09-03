public class lt_25_reverse_nodes_k_group {

    static class ListNode {
        int val; ListNode next;
        ListNode(int v){ val=v; }
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0); dummy.next = head;
        ListNode groupPrev = dummy;

        while (true) {
            ListNode kth = getKth(groupPrev, k);
            if (kth == null) break;
            ListNode groupNext = kth.next;

            // reverse group
            ListNode prev = groupNext, cur = groupPrev.next;
            while (cur != groupNext) {
                ListNode tmp = cur.next;
                cur.next = prev;
                prev = cur;
                cur = tmp;
            }
            // connect
            ListNode tmp = groupPrev.next;
            groupPrev.next = kth;
            groupPrev = tmp;
        }
        return dummy.next;
    }

    private static ListNode getKth(ListNode start, int k) {
        while (start != null && k > 0) { start = start.next; k--; }
        return start;
    }

    private static ListNode build(int... a){ ListNode d=new ListNode(0),c=d; for(int v:a){ c.next=new ListNode(v); c=c.next;} return d.next; }
    private static void print(ListNode h){ while(h!=null){ System.out.print(h.val+(h.next!=null?"->":"")); h=h.next;} System.out.println(); }

    public static void main(String[] args) {
        print(reverseKGroup(build(1,2,3,4,5), 2)); // 2->1->4->3->5
        print(reverseKGroup(build(1,2,3,4,5), 3)); // 3->2->1->4->5
    }
}

/*
[Explanation]
每次定位第 k 個節點，原地反轉該區間，接回前後鏈。
[Complexity] Time O(n), Space O(1)。
*/
