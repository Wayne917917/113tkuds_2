
public class lt_11_container_most_water {

    public static int maxArea(int[] height) {
        int l = 0, r = height.length - 1, ans = 0;
        while (l < r) {
            int h = Math.min(height[l], height[r]);
            ans = Math.max(ans, h * (r - l));
            if (height[l] < height[r]) l++;
            else r--;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{1,8,6,2,5,4,8,3,7})); // 49
        System.out.println(maxArea(new int[]{1,1}));               // 1
    }
}

/*
[Explanation]
雙指針夾逼：面積 = (r-l)*min(h[l],h[r])。
想擴大面積需提高短板 → 移動較短的一側。
Complexity：Time O(n)，Space O(1)。
*/
