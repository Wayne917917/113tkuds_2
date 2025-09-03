public class lt_24_swap_nodes_in_pairs {

    static class ListNode {
        int val; ListNode next;
        ListNode(int v){ val=v; }
    }

    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0); dummy.next = head;
        ListNode prev = dummy;
        while (prev.next != null && prev.next.next != null) {
            ListNode a = prev.next, b = a.next;
            a.next = b.next;
            b.next = a;
            prev.next = b;
            prev = a;
        }
        return dummy.next;
    }

    private static ListNode build(int... a){ ListNode d=new ListNode(0),c=d; for(int v:a){ c.next=new ListNode(v); c=c.next;} return d.next; }
    private static void print(ListNode h){ while(h!=null){ System.out.print(h.val+(h.next!=null?"->":"")); h=h.next;} System.out.println(); }

    public static void main(String[] args) {
        print(swapPairs(build(1,2,3,4))); // 2->1->4->3
    }
}

/*
[Explanation]
成對指針重連：prev->b->a->next，然後 prev 移到 a 繼續。
[Complexity] Time O(n), Space O(1)。
*/
