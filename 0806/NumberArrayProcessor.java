import java.util.*;

public class NumberArrayProcessor {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 2, 3, 4, 4, 5};
        int[] arr2 = {3, 5, 6, 7};

        System.out.println("原陣列移除重複：");
        System.out.println(Arrays.toString(removeDuplicates(arr1)));

        System.out.println("合併兩排序陣列：");
        System.out.println(Arrays.toString(mergeSortedArrays(arr1, arr2)));

        System.out.println("最常出現元素：");
        System.out.println(mostFrequentElement(arr1));

        System.out.println("分割成兩個總和近似子陣列：");
        splitArray(arr1);
    }

    static int[] removeDuplicates(int[] arr) {
        return Arrays.stream(arr).distinct().toArray();
    }

    static int[] mergeSortedArrays(int[] a, int[] b) {
        int[] merged = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;

        while (i < a.length && j < b.length)
            merged[k++] = (a[i] < b[j]) ? a[i++] : b[j++];
        while (i < a.length) merged[k++] = a[i++];
        while (j < b.length) merged[k++] = b[j++];
        return merged;
    }

    static int mostFrequentElement(int[] arr) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int n : arr) countMap.put(n, countMap.getOrDefault(n, 0) + 1);
        return Collections.max(countMap.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    static void splitArray(int[] arr) {
        int total = Arrays.stream(arr).sum();
        int half = total / 2;
        List<Integer> part1 = new ArrayList<>();
        int sum = 0;
        for (int n : arr) {
            if (sum + n <= half) {
                part1.add(n);
                sum += n;
            }
        }
        List<Integer> part2 = new ArrayList<>();
        for (int n : arr) {
            if (!part1.contains(n) || Collections.frequency(part1, n) < Collections.frequency(Arrays.asList(Arrays.stream(arr).boxed().toArray(Integer[]::new)), n))
                part2.add(n);
        }

        System.out.println("子陣列1：" + part1);
        System.out.println("子陣列2：" + part2);
    }
}
