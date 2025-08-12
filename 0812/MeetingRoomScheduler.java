import java.util.*;

public class MeetingRoomScheduler {
    // 最少會議室數：經典最小資源
    public static int minRooms(int[][] intervals){
        int n=intervals.length;
        int[] starts=new int[n], ends=new int[n];
        for (int i=0;i<n;i++){ starts[i]=intervals[i][0]; ends[i]=intervals[i][1]; }
        Arrays.sort(starts); Arrays.sort(ends);
        int i=0,j=0,rooms=0,ans=0;
        while(i<n){
            if (starts[i] < ends[j]) { rooms++; ans=Math.max(ans,rooms); i++; }
            else { rooms--; j++; }
        }
        return ans;
    }

    // 在最多 N 個會議室下，盡量排最多總時間（等權重）。貪心：超過 N 重疊時丟棄「最晚結束」的那個。
    public static List<int[]> maximizeTotalTime(int[][] intervals, int N){
        Arrays.sort(intervals, (a,b)-> a[0]!=b[0]? Integer.compare(a[0],b[0]) : Integer.compare(a[1],b[1]));
        // max-heap by end time（要丟掉結束最晚者）
        PriorityQueue<int[]> chosen = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] x,int[] y){ return Integer.compare(y[1], x[1]); }
        });
        int currentOverlaps=0;
        // sweep with a min-heap by end to track overlaps count
        PriorityQueue<int[]> activeByEnd = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] x,int[] y){ return Integer.compare(x[1], y[1]); }
        });

        for (int[] it : intervals){
            while(!activeByEnd.isEmpty() && activeByEnd.peek()[1] <= it[0]) activeByEnd.poll();
            activeByEnd.offer(it);
            chosen.offer(it);
            currentOverlaps = activeByEnd.size();
            if (currentOverlaps > N){
                // 移除一個最晚結束的
                int[] drop = chosen.poll();
                // 從 active 中移除該 drop（線性清理）
                List<int[]> buf = new ArrayList<>();
                while(!activeByEnd.isEmpty() && activeByEnd.peek()!=drop) buf.add(activeByEnd.poll());
                if (!activeByEnd.isEmpty()) activeByEnd.poll();
                for (int[] x: buf) activeByEnd.offer(x);
            }
        }
        // chosen 可能包含已被丟棄者，整理：重建以不超過 N 重疊
        List<int[]> all = new ArrayList<>(chosen);
        all.sort((a,b)-> a[0]!=b[0]? a[0]-b[0] : a[1]-b[1]);
        // 過濾為合法解（不超過N重疊）
        List<int[]> res = new ArrayList<>();
        PriorityQueue<Integer> ends = new PriorityQueue<>();
        for (int[] it: all){
            while(!ends.isEmpty() && ends.peek() <= it[0]) ends.poll();
            if (ends.size() < N) { res.add(it); ends.offer(it[1]); }
        }
        return res;
    }

    public static void main(String[] args){
        System.out.println(minRooms(new int[][]{{0,30},{5,10},{15,20}})); // 2
        System.out.println(minRooms(new int[][]{{9,10},{4,9},{4,17}}));  // 2
        System.out.println(minRooms(new int[][]{{1,5},{8,9},{8,9}}));    // 2

        // N=1 範例
        List<int[]> best = maximizeTotalTime(new int[][]{{1,4},{2,3},{4,6}}, 1);
        int total=0; for (int[] x: best) total += x[1]-x[0];
        // 期望選 [1,4] 與 [4,6] => 總時間 5
        System.out.println("選擇數: "+best.size()+", 總時間: "+total);
    }
}
