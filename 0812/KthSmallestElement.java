import java.util.*;

public class KthSmallestElement {
    // 方法1：Max Heap 大小維持 k
    public static int kthSmallestWithMaxHeap(int[] a, int k){
        PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
        for (int x : a) {
            max.offer(x);
            if (max.size() > k) max.poll();
        }
        return max.peek();
    }

    // 方法2：Min Heap 取出 k 次
    public static int kthSmallestWithMinHeap(int[] a, int k){
        PriorityQueue<Integer> min = new PriorityQueue<>();
        for (int x : a) min.offer(x);
        for (int i=1;i<k;i++) min.poll();
        return min.peek();
    }

    public static void main(String[] args){
        System.out.println(kthSmallestWithMaxHeap(new int[]{7,10,4,3,20,15},3)); // 7
        System.out.println(kthSmallestWithMinHeap(new int[]{1},1)); // 1
        System.out.println(kthSmallestWithMaxHeap(new int[]{3,1,4,1,5,9,2,6},4)); // 3
    }
}
