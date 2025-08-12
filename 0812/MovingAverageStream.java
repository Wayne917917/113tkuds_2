import java.util.*;

public class MovingAverageStream {
    private final int size;
    private final Deque<Integer> window = new ArrayDeque<>();
    private long sum = 0;

    // median with two-heaps + lazy delete
    private PriorityQueue<Integer> low = new PriorityQueue<>(Collections.reverseOrder());
    private PriorityQueue<Integer> high = new PriorityQueue<>();
    private Map<Integer,Integer> delayed = new HashMap<>();
    private int lowSize=0, highSize=0;

    // min/max with monotonic deques
    private Deque<Integer> minQ = new ArrayDeque<>();
    private Deque<Integer> maxQ = new ArrayDeque<>();

    public MovingAverageStream(int size){ this.size=size; }

    public double next(int val){
        window.offerLast(val);
        sum += val;

        // min/max
        while(!minQ.isEmpty() && minQ.peekLast() > val) minQ.pollLast();
        minQ.offerLast(val);
        while(!maxQ.isEmpty() && maxQ.peekLast() < val) maxQ.pollLast();
        maxQ.offerLast(val);

        // median add
        add(val);

        if (window.size() > size){
            int old = window.pollFirst();
            sum -= old;

            // pop from min/max
            if (!minQ.isEmpty() && minQ.peekFirst()==old) minQ.pollFirst();
            if (!maxQ.isEmpty() && maxQ.peekFirst()==old) maxQ.pollFirst();

            // median remove
            remove(old);
        }
        int denom = Math.max(1, Math.min(size, window.size()));
        return ((double)sum)/denom;
    }

    public double getMedian(){
        if (window.isEmpty()) return 0.0;
        if (lowSize==highSize) return ((double)low.peek() + (double)high.peek())/2.0;
        return (double)low.peek();
    }

    public int getMin(){ if (window.isEmpty()) throw new NoSuchElementException(); return minQ.peekFirst(); }
    public int getMax(){ if (window.isEmpty()) throw new NoSuchElementException(); return maxQ.peekFirst(); }

    // ===== two-heaps helpers =====
    private void add(int num){
        if (low.isEmpty() || num <= low.peek()) { low.offer(num); lowSize++; }
        else { high.offer(num); highSize++; }
        rebalance();
    }
    private void remove(int num){
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

    public static void main(String[] args){
        MovingAverageStream ma = new MovingAverageStream(3);
        System.out.println(ma.next(1));   // 1.0
        System.out.println(ma.next(10));  // 5.5
        System.out.println(ma.next(3));   // 4.666...
        System.out.println(ma.next(5));   // 6.0
        System.out.println(ma.getMedian()); // 5.0
        System.out.println(ma.getMin());    // 3
        System.out.println(ma.getMax());    // 10
    }
}
