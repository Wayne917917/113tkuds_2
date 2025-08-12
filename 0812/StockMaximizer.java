import java.util.*;

public class StockMaximizer {
    // 正確版：DP O(nK)
    public static int maxProfitDP(int[] prices, int K){
        if (prices.length==0 || K==0) return 0;
        if (K >= prices.length/2) { // 轉成不限次數
            int sum=0; for(int i=1;i<prices.length;i++) if (prices[i]>prices[i-1]) sum+=prices[i]-prices[i-1];
            return sum;
        }
        int n=prices.length;
        int[] buy = new int[K+1];
        int[] sell = new int[K+1];
        Arrays.fill(buy, Integer.MIN_VALUE/4);
        Arrays.fill(sell, 0);
        for (int p: prices){
            for (int k=1;k<=K;k++){
                buy[k] = Math.max(buy[k], sell[k-1]-p);
                sell[k] = Math.max(sell[k], buy[k]+p);
            }
        }
        return sell[K];
    }

    // 示意的堆積輔助：擷取所有上升段利潤到 max-heap，取前K大（可能非最優，但直觀）
    public static int maxProfitHeapGreedy(int[] prices, int K){
        PriorityQueue<Integer> gains = new PriorityQueue<>(Collections.reverseOrder());
        int i=0, n=prices.length;
        while(i<n){
            while(i+1<n && prices[i+1]<=prices[i]) i++;
            int valley = i++;
            while(i<n && prices[i]>=prices[i-1]) i++;
            int peak = i-1;
            if (peak>valley) gains.offer(prices[peak]-prices[valley]);
        }
        int res=0; while(K-->0 && !gains.isEmpty()) res+=gains.poll();
        return res;
    }

    public static void main(String[] args){
        System.out.println(maxProfitDP(new int[]{2,4,1},2)); // 2
        System.out.println(maxProfitDP(new int[]{3,2,6,5,0,3},2)); // 7
        System.out.println(maxProfitDP(new int[]{1,2,3,4,5},2)); // 4
    }
}
