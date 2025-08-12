import java.util.*;

public class MergeKSortedArrays {
    static class Node {
        int val, ai, bi;
        Node(int v,int a,int b){ val=v; ai=a; bi=b; }
    }

    public static int[] merge(List<int[]> lists){
        int total=0; for (int[] arr: lists) total+=arr.length;
        int[] out = new int[total];
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>(){
            public int compare(Node x, Node y){ return Integer.compare(x.val, y.val); }
        });
        for (int i=0;i<lists.size();i++){
            if (lists.get(i).length>0) pq.offer(new Node(lists.get(i)[0], i, 0));
        }
        int idx=0;
        while(!pq.isEmpty()){
            Node n = pq.poll();
            out[idx++] = n.val;
            int next = n.bi+1;
            if (next < lists.get(n.ai).length){
                pq.offer(new Node(lists.get(n.ai)[next], n.ai, next));
            }
        }
        return out;
    }

    public static void main(String[] args){
        System.out.println(Arrays.toString(
            merge(Arrays.asList(new int[]{1,4,5}, new int[]{1,3,4}, new int[]{2,6}))
        ));
        System.out.println(Arrays.toString(
            merge(Arrays.asList(new int[]{1,2,3}, new int[]{4,5,6}, new int[]{7,8,9}))
        ));
        System.out.println(Arrays.toString(
            merge(Arrays.asList(new int[]{1}, new int[]{0}))
        ));
    }
}
