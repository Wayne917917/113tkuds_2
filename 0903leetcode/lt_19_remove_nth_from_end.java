public class lt_19_remove_nth_from_end {

    static class ListNode {
        int val; ListNode next;
        ListNode(int v){ val = v; }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0); dummy.next = head;
        ListNode fast = dummy, slow = dummy;
        for (int i = 0; i < n; i++) fast = fast.next;
        while (fast.next != null) { fast = fast.next; slow = slow.next; }
        slow.next = slow.next.next;
        return dummy.next;
    }

    private static void print(ListNode h) {
        while (h != null) { System.out.print(h.val + (h.next!=null?"->":"")); h = h.next; }
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1); a.next=new ListNode(2); a.next.next=new ListNode(3);
        a.next.next.next=new ListNode(4); a.next.next.next.next=new ListNode(5);
        print(removeNthFromEnd(a, 2)); // 1->2->3->5
    }
}

/*
[Explanation]
快指針先走 n 步，再與慢指針同速；當快指針到尾，慢指針正好在要刪除節點前一個。
[Complexity] Time O(n), Space O(1).
*/
