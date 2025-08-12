import java.util.*;

public class BasicMinHeapPractice {
    private final List<Integer> heap = new ArrayList<>();

    public void insert(int val) {
        heap.add(val);
        heapifyUp(heap.size() - 1);
    }

    public int extractMin() {
        if (isEmpty()) throw new NoSuchElementException("Heap is empty");
        int min = heap.get(0);
        int last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }
        return min;
    }

    public int getMin() {
        if (isEmpty()) throw new NoSuchElementException("Heap is empty");
        return heap.get(0);
    }

    public int size() { return heap.size(); }

    public boolean isEmpty() { return heap.isEmpty(); }

    private void heapifyUp(int i) {
        while (i > 0) {
            int p = (i - 1) / 2;
            if (heap.get(i) >= heap.get(p)) break;
            swap(i, p);
            i = p;
        }
    }

    private void heapifyDown(int i) {
        int n = heap.size();
        while (true) {
            int l = 2 * i + 1, r = 2 * i + 2, m = i;
            if (l < n && heap.get(l) < heap.get(m)) m = l;
            if (r < n && heap.get(r) < heap.get(m)) m = r;
            if (m == i) break;
            swap(i, m);
            i = m;
        }
    }

    private void swap(int i, int j) {
        int t = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, t);
    }

    public static void main(String[] args) {
        BasicMinHeapPractice h = new BasicMinHeapPractice();
        int[] input = {15, 10, 20, 8, 25, 5};
        for (int x : input) h.insert(x);
        List<Integer> out = new ArrayList<>();
        while (!h.isEmpty()) out.add(h.extractMin());
        System.out.println(out); // 期望 [5, 8, 10, 15, 20, 25]
    }
}
