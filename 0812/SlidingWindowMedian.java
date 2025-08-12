import java.util.*;

public class SlidingWindowMedian {
    // 兩堆＋延遲刪除
    private PriorityQueue<Integer> low = new PriorityQueue<>(Collections.reverseOrder()); // max-heap
    private PriorityQueue<Integer> high = new PriorityQueue<>(); // min-heap
    private Map<Integer,Integer> delayed = new HashMap<>();
    private int lowSize=0, highSize=0;

    private void add(int num){
        if (low.isEmpty() || num <= low.peek()) { low.offer(num); lowSize++; }
        else { high.offer(num); highSize++; }
        rebalance();
    }

    private void remove(int num){
        // 標記延遲刪除並調整有效大小
        if (!low.isEmpty() && num <= low.peek()) { lowSize--; delayed.put(num, delayed.getOrDefault(num,0)+1); prune(low); }
        else { highSize--; delayed.put(num, delayed.getOrDefault(num,0)+1); prune(high); }
        rebalance();
    }

    private void rebalance(){
        if (lowSize > highSize + 1) { high.offer(low.poll()); lowSize--; highSize++; prune(low); }
        else if (highSize > lowSize) { low.offer(high.poll()); highSize--; lowSize++; prune(high); }
    }

    private void prune(PriorityQueue<Integer> heap){
        while(!heap.isEmpty()){
            int x = heap.peek();
            Integer c = delayed.get(x);
            if (c==null || c==0) break;
            heap.poll();
            if (c==1) delayed.remove(x); else delayed.put(x, c-1);
        }
    }

    private double getMedian(){
        if (lowSize==highSize) return ((double)low.peek() + (double)high.peek())/2.0;
        return (double)low.peek();
    }

    public double[] medianSlidingWindow(int[] nums, int k){
        double[] ans = new double[nums.length - k + 1];
        for (int i=0;i<nums.length;i++){
            add(nums[i]);
            if (i>=k-1){
                ans[i - (k-1)] = getMedian();
                remove(nums[i-(k-1)]);
            }
        }
        return ans;
    }

    public static void main(String[] args){
        SlidingWindowMedian s = new SlidingWindowMedian();
        System.out.println(Arrays.toString(
            s.medianSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7},3)
        )); // [1,-1,-1,3,5,6]
        System.out.println(Arrays.toString(
            s.medianSlidingWindow(new int[]{1,2,3,4},2)
        )); // [1.5,2.5,3.5]
    }
}
