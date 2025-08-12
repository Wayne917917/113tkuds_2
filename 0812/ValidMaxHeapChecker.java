import java.util.*;

public class ValidMaxHeapChecker {
    public static class Result {
        public final boolean valid;
        public final int index; // 若不合法，第一個違規索引
        Result(boolean v, int i){ valid=v; index=i; }
        public String toString(){
            return valid ? "true" : ("false (索引" + index + "違規)");
        }
    }

    public static Result isValidMaxHeap(int[] a) {
        int n = a.length;
        for (int i = (n - 2) / 2; i >= 0; i--) {
            int l = 2 * i + 1, r = 2 * i + 2;
            if (l < n && a[i] < a[l]) return new Result(false, l);
            if (r < n && a[i] < a[r]) return new Result(false, r);
        }
        return new Result(true, -1);
    }

    public static void main(String[] args) {
        System.out.println(isValidMaxHeap(new int[]{100,90,80,70,60,75,65}));
        System.out.println(isValidMaxHeap(new int[]{100,90,80,95,60,75,65}));
        System.out.println(isValidMaxHeap(new int[]{50}));
        System.out.println(isValidMaxHeap(new int[]{}));
    }
}
