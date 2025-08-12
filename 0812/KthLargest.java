import java.util.*;

public class KthLargest {

    // 自訂 repeat（相容 Java 8）
    private static String repeat(char ch, int count) {
        StringBuilder sb = new StringBuilder(count);
        for (int i = 0; i < count; i++) sb.append(ch);
        return sb.toString();
    }

    // 方法1：使用 Min Heap 找第 K 大元素
    public static int findKthLargest(int[] nums, int k) {
        if (k < 1 || k > nums.length) throw new IllegalArgumentException("k out of range");
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        System.out.println("處理過程:");
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            minHeap.offer(num);
            System.out.print("加入 " + num + ": " + minHeap);
            if (minHeap.size() > k) {
                int removed = minHeap.poll();
                System.out.print(" -> 移除 " + removed + ": " + minHeap);
            }
            System.out.println();
        }
        return minHeap.peek();
    }

    // 方法2：建立完整 Max Heap 後取出 K 次
    public static int findKthLargestByMaxHeap(int[] nums, int k) {
        if (k < 1 || k > nums.length) throw new IllegalArgumentException("k out of range");
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(11, new Comparator<Integer>() {
            public int compare(Integer a, Integer b) { return b - a; }
        });

        for (int num : nums) maxHeap.offer(num);
        System.out.println("Max Heap: " + maxHeap);

        for (int i = 0; i < k - 1; i++) {
            int removed = maxHeap.poll();
            System.out.println("取出第 " + (i + 1) + " 大: " + removed + ", 剩餘: " + maxHeap);
        }
        return maxHeap.peek();
    }

    // 方法3：快速選擇（平均 O(n)）
    public static int findKthLargestByQuickSelect(int[] nums, int k) {
        if (k < 1 || k > nums.length) throw new IllegalArgumentException("k out of range");
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    private static int quickSelect(int[] nums, int left, int right, int kIndex) {
        if (left == right) return nums[left];
        int pivotIndex = partition(nums, left, right);
        if (kIndex == pivotIndex) return nums[kIndex];
        else if (kIndex < pivotIndex) return quickSelect(nums, left, pivotIndex - 1, kIndex);
        else return quickSelect(nums, pivotIndex + 1, right, kIndex);
    }

    // Lomuto 分割
    private static int partition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int i = left;
        for (int j = left; j < right; j++) {
            if (nums[j] <= pivot) {
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums, i, right);
        return i;
    }

    private static void swap(int[] nums, int i, int j) {
        int t = nums[i]; nums[i] = nums[j]; nums[j] = t;
    }

    // 測試和比較不同方法
    public static void testKthLargest(int[] nums, int k) {
        System.out.println("=== 找第 " + k + " 大元素 ===");
        System.out.println("陣列: " + Arrays.toString(nums));

        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        System.out.println("排序後: " + Arrays.toString(sorted));
        System.out.println("答案應該是: " + sorted[sorted.length - k]);

        System.out.println("\n方法1 - Min Heap (推薦):");
        int result1 = findKthLargest(nums.clone(), k);
        System.out.println("結果: " + result1);

        System.out.println("\n方法2 - Max Heap:");
        int result2 = findKthLargestByMaxHeap(nums.clone(), k);
        System.out.println("結果: " + result2);

        System.out.println("\n方法3 - 快速選擇:");
        int result3 = findKthLargestByQuickSelect(nums.clone(), k);
        System.out.println("結果: " + result3);

        System.out.println("\n時間複雜度比較:");
        System.out.println("Min Heap: O(n log k), 空間 O(k)");
        System.out.println("Max Heap: O(n log n), 空間 O(n)");
        System.out.println("快速選擇: 平均 O(n), 最壞 O(n^2), 空間 O(1)");
        System.out.println("\n" + repeat('=', 42) + "\n");
    }

    public static void main(String[] args) {
        testKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2);
        testKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4);
        testKthLargest(new int[]{1}, 1);
    }
}
