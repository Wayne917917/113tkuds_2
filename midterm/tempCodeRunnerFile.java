import java.util.*;

public class M01_BuildHeap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String type = sc.next(); // "max" 或 "min"
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = sc.nextInt();
        sc.close();

        buildHeap(a, type);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0)
                sb.append(' ');
            sb.append(a[i]);
        }
        System.out.print(sb.toString());
    }

    // Bottom-up：O(n)
    static void buildHeap(int[] a, String type) {
        int n = a.length;
        for (int i = n / 2 - 1; i >= 0; i--)
            heapifyDown(a, n, i, type);
    }

    // 迭代下沉：單次 O(log n)
    static void heapifyDown(int[] a, int n, int i, String type) {
        boolean isMax = "max".equals(type);
        while (true) {
            int extreme = i, l = 2 * i + 1, r = 2 * i + 2;
            if (l < n && (isMax ? a[l] > a[extreme] : a[l] < a[extreme]))
                extreme = l;
            if (r < n && (isMax ? a[r] > a[extreme] : a[r] < a[extreme]))
                extreme = r;
            if (extreme == i)
                break;
            int tmp = a[i];
            a[i] = a[extreme];
            a[extreme] = tmp;
            i = extreme;
        }
    }
}

/*
 * Complexity:
 * - Build-Heap (bottom-up): O(n)
 * - heapifyDown (single call): O(log n)
 * - Space: O(1) extra space (iterative)
 */
