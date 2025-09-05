public class LC27_RemoveElement {
public static int removeElement(int[] nums, int val) {
int k = 0;
for (int x : nums) if (x != val) nums[k++] = x;
return k;
}
public static void main(String[] args) {
int[] a = {3,2,2,3};
int k = removeElement(a, 3);
System.out.println(k);
for (int i = 0; i < k; i++) System.out.print(a[i] + " ");
}
}