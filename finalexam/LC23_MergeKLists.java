import java.util.*;


public class LC23_MergeKLists {
static class ListNode { int val; ListNode next; ListNode(int v){val=v;} }
public static ListNode mergeKLists(ListNode[] lists) {
PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
for (ListNode n : lists) if (n != null) pq.offer(n);
ListNode dummy = new ListNode(0), cur = dummy;
while (!pq.isEmpty()) {
ListNode node = pq.poll();
cur.next = node; cur = cur.next;
if (node.next != null) pq.offer(node.next);
}
return dummy.next;
}
public static void main(String[] args) {
ListNode a=new ListNode(1); a.next=new ListNode(4); a.next.next=new ListNode(5);
ListNode b=new ListNode(1); b.next=new ListNode(3); b.next.next=new ListNode(4);
ListNode c=new ListNode(2); c.next=new ListNode(6);
ListNode r = mergeKLists(new ListNode[]{a,b,c});
while(r!=null){ System.out.print(r.val+" "); r=r.next; }
}
}