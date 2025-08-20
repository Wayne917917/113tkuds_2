import java.util.*;

public class M01_BuildHeap {
    static boolean better(int a, int b, boolean isMax) {
        return isMax ? a > b : a < b;
    }

    static void heapifyDown(int[] a, int n, int i, boolean isMax) {
        while (true) {
            int l = 2 * i + 1, r = l + 1, best = i;
            if (l < n && better(a[l], a[best], isMax))
                best = l;
            if (r < n && better(a[r], a[best], isMax))
                best = r;
            if (best == i)
                break;
            int tmp = a[i];
            a[i] = a[best];
            a[best] = tmp;
            i = best;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String type = sc.next();
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = sc.nextInt();
        boolean isMax = type.equalsIgnoreCase("max");

        for (int i = (n / 2) - 1; i >= 0; i--)
            heapifyDown(a, n, i, isMax);

        for (int i = 0; i < n; i++) {
            if (i > 0)
                System.out.print(" ");
            System.out.print(a[i]);
        }
        System.out.println();
    }
}

/*
 * Time Complexity: O(n)
 * 說明：自底向上建堆，所有節點下沉高度總和 O(n)，優於逐一插入 O(n log n)。
 */
