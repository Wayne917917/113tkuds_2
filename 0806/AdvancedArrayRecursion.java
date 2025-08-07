import java.util.*;

public class AdvancedArrayRecursion {
    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pi = partition(arr, left, right);
            quickSort(arr, left, pi - 1);
            quickSort(arr, pi + 1, right);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high], i = low - 1;
        for (int j = low; j < high; j++)
            if (arr[j] <= pivot)
                swap(arr, ++i, j);
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
    }

    public static int[] mergeSorted(int[] a, int[] b) {
        if (a.length == 0) return b;
        if (b.length == 0) return a;
        if (a[0] < b[0])
            return merge(new int[]{a[0]}, mergeSorted(Arrays.copyOfRange(a, 1, a.length), b));
        else
            return merge(new int[]{b[0]}, mergeSorted(a, Arrays.copyOfRange(b, 1, b.length)));
    }

    private static int[] merge(int[] a, int[] b) {
        int[] res = new int[a.length + b.length];
        System.arraycopy(a, 0, res, 0, a.length);
        System.arraycopy(b, 0, res, a.length, b.length);
        return res;
    }

    public static int kthSmallest(int[] arr, int k) {
        int[] copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copy);
        return copy[k - 1];
    }

    public static boolean subsetSum(int[] arr, int target, int idx) {
        if (target == 0) return true;
        if (idx >= arr.length || target < 0) return false;
        return subsetSum(arr, target - arr[idx], idx + 1) || subsetSum(arr, target, idx + 1);
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 4, 2};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("QuickSorted: " + Arrays.toString(arr));

        int[] merged = mergeSorted(new int[]{1, 3, 5}, new int[]{2, 4, 6});
        System.out.println("Merged: " + Arrays.toString(merged));

        System.out.println("3rd smallest: " + kthSmallest(arr, 3));

        System.out.println("Subset sum exists for 6: " + subsetSum(arr, 6, 0));
    }
}
