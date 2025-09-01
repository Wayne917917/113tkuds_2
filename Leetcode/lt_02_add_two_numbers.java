// LeetCode 2. Add Two Numbers
public class lt_02_add_two_numbers {

    // ListNode 定義
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    // 主函式：逐位相加
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;
            int sum = x + y + carry;

            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            carry = sum / 10;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        return dummy.next;
    }

    // ====== 測試輔助 ======
    // 由陣列建立反向串列（如 [2,4,3] 代表 342）
    static ListNode build(int[] a) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : a) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }
    // 印出串列
    static void printList(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) sb.append(" -> ");
            head = head.next;
        }
        System.out.println(sb.toString());
    }

    // 範例測試
    public static void main(String[] args) {
        // Example 1: [2,4,3] + [5,6,4] = [7,0,8]
        ListNode l1 = build(new int[]{2,4,3});
        ListNode l2 = build(new int[]{5,6,4});
        printList(addTwoNumbers(l1, l2)); // 7 -> 0 -> 8

        // Example 2: [0] + [0] = [0]
        ListNode a = build(new int[]{0});
        ListNode b = build(new int[]{0});
        printList(addTwoNumbers(a, b));   // 0

        // Example 3: [9,9,9,9,9,9,9] + [9,9,9,9] = [8,9,9,9,0,0,0,1]
        ListNode c = build(new int[]{9,9,9,9,9,9,9});
        ListNode d = build(new int[]{9,9,9,9});
        printList(addTwoNumbers(c, d));   // 8 -> 9 -> 9 -> 9 -> 0 -> 0 -> 0 -> 1
    }
}
