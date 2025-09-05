public class LC25_ReverseKGroup {
static class ListNode { int val; ListNode next; ListNode(int v){val=v;} }
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
ListNode tmp = groupPrev.next; // tail after reverse
groupPrev.next = kth;
groupPrev = tmp;
}
return dummy.next;
}
private static ListNode getKth(ListNode start, int k) {
while (start != null && k-- > 0) start = start.next;
return start;
}
public static void main(String[] args) {
ListNode a=new ListNode(1); a.next=new ListNode(2); a.next.next=new ListNode(3); a.next.next.next=new ListNode(4); a.next.next.next.next=new ListNode(5);
ListNode r = reverseKGroup(a, 2);
while(r!=null){ System.out.print(r.val+" "); r=r.next; }
}
}