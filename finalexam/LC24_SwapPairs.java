public class LC24_SwapPairs {
static class ListNode { int val; ListNode next; ListNode(int v){val=v;} }
public static ListNode swapPairs(ListNode head) {
ListNode dummy = new ListNode(0); dummy.next = head;
ListNode prev = dummy;
while (head != null && head.next != null) {
ListNode a = head, b = head.next;
prev.next = b; a.next = b.next; b.next = a;
prev = a; head = a.next;
}
return dummy.next;
}
public static void main(String[] args) {
ListNode a=new ListNode(1); a.next=new ListNode(2); a.next.next=new ListNode(3); a.next.next.next=new ListNode(4);
ListNode r = swapPairs(a);
while(r!=null){ System.out.print(r.val+" "); r=r.next; }
}
}