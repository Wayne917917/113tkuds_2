public class lt_21_merge_two_sorted_lists {

    static class ListNode {
        int val; ListNode next;
        ListNode(int v){ val=v; }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0), cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) { cur.next = l1; l1 = l1.next; }
            else { cur.next = l2; l2 = l2.next; }
            cur = cur.next;
        }
        cur.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }

    private static void print(ListNode h) {
        while (h != null) { System.out.print(h.val + (h.next!=null?"->":"")); h = h.next; }
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode a=new ListNode(1); a.next=new ListNode(2); a.next.next=new ListNode(4);
        ListNode b=new ListNode(1); b.next=new ListNode(3); b.next.next=new ListNode(4);
        print(mergeTwoLists(a,b)); // 1->1->2->3->4->4
    }
}

/*
[Explanation]
雙指針逐步選小節點；用假頭簡化連結。
[Complexity] Time O(m+n), Space O(1).
*/
