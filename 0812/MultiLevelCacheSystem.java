import java.util.*;

// 簡化版多層級 LRU + 頻率評分，使用 heap 管理逐出優先
public class MultiLevelCacheSystem {

    static class Entry {
        final int key;
        String value;
        int freq;
        long time;  // 最近存取時間(遞增)
        int level;  // 1/2/3
        Entry(int k, String v, int lv, long t){ key=k; value=v; level=lv; time=t; freq=1; }
    }

    static class Level {
        final int cap;
        final int cost; // 存取成本（僅用於評分）
        final Map<Integer, Entry> map = new HashMap<>();
        // min-heap by score：較小分數先被逐出。分數依據 freq 與 recency 與層級成本
        final PriorityQueue<Entry> heap = new PriorityQueue<>(new Comparator<Entry>(){
            public int compare(Entry a, Entry b){ return Long.compare(score(a), score(b)); }
        });
        long score(Entry e){
            // 較低頻、較久未用、較低層(高成本) → 分數小 → 優先逐出
            // 這裡以 time 為遞增，越小表示越舊；freq 越小越容易逐出；成本越大越不想放在低層 → 讓低層分數略小。
            return (long)e.freq * 1000000000L + e.time / (cost);
        }
        Level(int c, int s){ cap=c; cost=s; }
        void put(Entry e){ map.put(e.key, e); heap.offer(e); }
        void remove(Entry e){ map.remove(e.key); heap.remove(e); }
        boolean full(){ return map.size() > cap; }
        Entry evict(){ Entry e = heap.poll(); if (e!=null) map.remove(e.key); return e; }
        Entry get(int key){ return map.get(key); }
    }

    private final Level L1 = new Level(2, 1);
    private final Level L2 = new Level(5, 3);
    private final Level L3 = new Level(10, 10);
    private final Map<Integer, Entry> dir = new HashMap<>();
    private long tick = 0;

    public String get(int key){
        tick++;
        Entry e = dir.get(key);
        if (e==null) return null;
        touch(e);
        promote(e);
        return e.value;
    }

    public void put(int key, String value){
        tick++;
        Entry e = dir.get(key);
        if (e!=null){
            e.value = value;
            touch(e);
            promote(e);
            return;
        }
        // 新資料進入 L2（可調策略）
        e = new Entry(key, value, 2, tick);
        dir.put(key, e);
        L2.put(e);
        adjustLevels();
    }

    private void touch(Entry e){
        e.freq++;
        e.time = tick;
        // 更新該層 heap（移除後重放以更新排序）
        Level lv = level(e.level);
        lv.heap.remove(e);
        lv.heap.offer(e);
    }

    private void promote(Entry e){
        // 頻繁存取 → 往上移；很久不用會被逐出而往下（簡化為重新放入較低層）
        if (e.level==2 && e.freq>=3){ move(e, L2, L1, 1); }
        else if (e.level==3 && e.freq>=2){ move(e, L3, L2, 2); }
        adjustLevels();
    }

    private void move(Entry e, Level from, Level to, int toLevel){
        from.remove(e);
        e.level = toLevel;
        to.put(e);
    }

    private Level level(int x){ return x==1? L1 : (x==2? L2 : L3); }

    private void adjustLevels(){
        // 若層級滿，逐出到下一層；L3 滿則淘汰最差者
        while(L1.full()){
            Entry ev = L1.evict();
            if (ev==null) break;
            ev.level = 2; L2.put(ev);
        }
        while(L2.full()){
            Entry ev = L2.evict();
            if (ev==null) break;
            ev.level = 3; L3.put(ev);
        }
        while(L3.full()){
            Entry ev = L3.evict();
            if (ev==null) break;
            dir.remove(ev.key); // 淘汰
        }
    }

    // 狀態輸出
    public String dump(){
        return "L1:" + L1.map.keySet() + " L2:" + L2.map.keySet() + " L3:" + L3.map.keySet();
    }

    public static void main(String[] args){
        MultiLevelCacheSystem c = new MultiLevelCacheSystem();
        c.put(1,"A"); c.put(2,"B"); c.put(3,"C");
        System.out.println(c.dump()); // L1:[?], L2:[?,?], L3:[]

        c.get(1); c.get(1); c.get(2);
        System.out.println(c.dump()); // 1 應提升至 L1

        c.put(4,"D"); c.put(5,"E"); c.put(6,"F");
        System.out.println(c.dump()); // 根據頻率與成本調整分布
    }
}
