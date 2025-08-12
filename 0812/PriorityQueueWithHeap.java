import java.util.*;

public class PriorityQueueWithHeap {
    static class Task {
        final String name;
        int priority;
        final long seq; // tie-breaker (smaller = earlier)
        Task(String n, int p, long s){ name=n; priority=p; seq=s; }
    }

    private long counter = 0;
    private PriorityQueue<Task> pq = new PriorityQueue<>(
        new Comparator<Task>(){
            public int compare(Task a, Task b){
                if (a.priority != b.priority) return b.priority - a.priority;
                return Long.compare(a.seq, b.seq);
            }
        }
    );
    private Map<String, Task> map = new HashMap<>();

    public void addTask(String name, int priority){
        counter++;
        Task t = new Task(name, priority, counter);
        pq.offer(t);
        map.put(name, t);
    }

    public String executeNext(){
        if (pq.isEmpty()) return null;
        Task t = pq.poll();
        map.remove(t.name);
        return t.name;
    }

    public String peek(){
        return pq.isEmpty() ? null : pq.peek().name;
    }

    public void changePriority(String name, int newPriority){
        Task t = map.get(name);
        if (t == null) return;
        // 重建：移除所有，更新該任務後重建（簡潔可靠）
        t.priority = newPriority;
        List<Task> all = new ArrayList<>(pq);
        pq.clear();
        pq.addAll(all);
    }

    public static void main(String[] args){
        PriorityQueueWithHeap q = new PriorityQueueWithHeap();
        q.addTask("備份",1);
        q.addTask("緊急修復",5);
        q.addTask("更新",3);
        System.out.println(q.executeNext()); // 緊急修復
        System.out.println(q.executeNext()); // 更新
        System.out.println(q.executeNext()); // 備份
    }
}
