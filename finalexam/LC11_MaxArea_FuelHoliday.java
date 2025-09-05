

public class LC11_MaxArea_FuelHoliday {
public static int maxArea(int[] h) {
int l = 0, r = h.length - 1, best = 0;
while (l < r) {
int width = r - l;
int minH = Math.min(h[l], h[r]);
best = Math.max(best, width * minH);
if (h[l] < h[r]) l++; else r--;
}
return best;
}
public static void main(String[] args) {
int[] a = {1,8,6,2,5,4,8,3,7};
System.out.println(maxArea(a)); // 49
}
}