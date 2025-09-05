public class LC21_MergeTwoSortedLists {
static class ListNode { int val; ListNode next; ListNode(int v){val=v;} }
public static ListNode mergeTwoLists(ListNode a, ListNode b) {
ListNode dummy = new ListNode(0), cur = dummy;
while (a != null && b != null) {
if (a.val <= b.val) { cur.next = a; a = a.next; }
else { cur.next = b; b = b.next; }
cur = cur.next;
}
cur.next = (a != null) ? a : b;
return dummy.next;
}
public static void main(String[] args) {
ListNode x=new ListNode(1); x.next=new ListNode(2); x.next.next=new ListNode(4);
ListNode y=new ListNode(1); y.next=new ListNode(3); y.next.next=new ListNode(4);
ListNode r = mergeTwoLists(x,y);
while(r!=null){ System.out.print(r.val+" "); r=r.next; }
}
}