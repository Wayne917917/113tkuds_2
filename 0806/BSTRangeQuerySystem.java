import java.util.*;

class BSTNode {
    int val;
    BSTNode left, right;
    BSTNode(int x) { val = x; }
}

public class BSTRangeQuerySystem {

    public static void rangeQuery(BSTNode root, int min, int max, List<Integer> result) {
        if (root == null) return;
        if (root.val > min) rangeQuery(root.left, min, max, result);
        if (root.val >= min && root.val <= max) result.add(root.val);
        if (root.val < max) rangeQuery(root.right, min, max, result);
    }

    public static int rangeCount(BSTNode root, int min, int max) {
        List<Integer> res = new ArrayList<>();
        rangeQuery(root, min, max, res);
        return res.size();
    }

    public static int rangeSum(BSTNode root, int min, int max) {
        List<Integer> res = new ArrayList<>();
        rangeQuery(root, min, max, res);
        return res.stream().mapToInt(Integer::intValue).sum();
    }

    public static int closestValue(BSTNode root, int target) {
        int closest = root.val;
        while (root != null) {
            if (Math.abs(root.val - target) < Math.abs(closest - target)) {
                closest = root.val;
            }
            root = (target < root.val) ? root.left : root.right;
        }
        return closest;
    }

    public static void main(String[] args) {
        BSTNode root = new BSTNode(20);
        root.left = new BSTNode(10);
        root.right = new BSTNode(30);
        root.left.left = new BSTNode(5);
        root.left.right = new BSTNode(15);
        root.right.left = new BSTNode(25);
        root.right.right = new BSTNode(35);

        List<Integer> result = new ArrayList<>();
        rangeQuery(root, 12, 27, result);
        System.out.println("範圍查詢結果 [12, 27]：" + result);
        System.out.println("節點數：" + rangeCount(root, 12, 27));
        System.out.println("總和：" + rangeSum(root, 12, 27));
        System.out.println("最接近 24 的值：" + closestValue(root, 24));
    }
}
