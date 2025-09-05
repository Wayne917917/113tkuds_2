import java.util.*;


public class LC04_MedianOfTwoSortedArrays {
public static double findMedianSortedArrays(int[] A, int[] B) {
if (A.length > B.length) return findMedianSortedArrays(B, A);
int m = A.length, n = B.length;
int lo = 0, hi = m;
while (lo <= hi) {
int i = (lo + hi) >>> 1; // partition A
int j = (m + n + 1) / 2 - i; // partition B
int Aleft = (i == 0) ? Integer.MIN_VALUE : A[i-1];
int Aright = (i == m) ? Integer.MAX_VALUE : A[i];
int Bleft = (j == 0) ? Integer.MIN_VALUE : B[j-1];
int Bright = (j == n) ? Integer.MAX_VALUE : B[j];
if (Aleft <= Bright && Bleft <= Aright) {
int leftMax = Math.max(Aleft, Bleft);
int rightMin = Math.min(Aright, Bright);
if (((m + n) & 1) == 1) return leftMax;
return (leftMax + rightMin) / 2.0;
} else if (Aleft > Bright) {
hi = i - 1;
} else {
lo = i + 1;
}
}
throw new IllegalArgumentException("Invalid input");
}
public static void main(String[] args) {
System.out.println(findMedianSortedArrays(new int[]{1,3}, new int[]{2})); // 2.0
}
}