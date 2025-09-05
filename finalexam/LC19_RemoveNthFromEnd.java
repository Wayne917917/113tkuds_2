public class LC19_RemoveNthFromEnd {
static class ListNode { int val; ListNode next; ListNode(int v){val=v;} }
public static ListNode removeNthFromEnd(ListNode head, int n) {
ListNode dummy = new ListNode(0); dummy.next = head;
ListNode fast = dummy, slow = dummy;
for (int i = 0; i < n; i++) fast = fast.next;
while (fast.next != null) { fast = fast.next; slow = slow.next; }
slow.next = slow.next.next;
return dummy.next;
}
public static void main(String[] args) {
ListNode a=new ListNode(1); a.next=new ListNode(2); a.next.next=new ListNode(3);
a.next.next.next=new ListNode(4); a.next.next.next.next=new ListNode(5);
ListNode r = removeNthFromEnd(a, 2); // 1,2,3,5
while(r!=null){ System.out.print(r.val+" "); r=r.next; }
}
}