import java.util.*;

public class BSTKthElement {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    static int count = 0;
    static int result = -1;

    public static void kthSmallest(TreeNode root, int k) {
        if (root == null) return;
        kthSmallest(root.left, k);
        count++;
        if (count == k) result = root.val;
        kthSmallest(root.right, k);
    }

    public static void kthLargest(TreeNode root, int k) {
        if (root == null) return;
        kthLargest(root.right, k);
        count++;
        if (count == k) result = root.val;
        kthLargest(root.left, k);
    }

    public static List<Integer> rangeKth(TreeNode root, int k, int j) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list.subList(k - 1, j);
    }

    public static void inorder(TreeNode root, List<Integer> list) {
        if (root == null) return;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    public static TreeNode insert(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) root.left = insert(root.left, val);
        else root.right = insert(root.right, val);
        return root;
    }

    public static TreeNode delete(TreeNode root, int key) {
        if (root == null) return null;
        if (key < root.val) root.left = delete(root.left, key);
        else if (key > root.val) root.right = delete(root.right, key);
        else {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;
            TreeNode minNode = findMin(root.right);
            root.val = minNode.val;
            root.right = delete(root.right, minNode.val);
        }
        return root;
    }

    private static TreeNode findMin(TreeNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    public static void main(String[] args) {
        TreeNode root = null;
        for (int val : new int[]{20, 10, 30, 5, 15, 25, 35}) {
            root = insert(root, val);
        }

        count = 0;
        kthSmallest(root, 3);
        System.out.println("第3小元素：" + result);

        count = 0;
        kthLargest(root, 2);
        System.out.println("第2大元素：" + result);

        System.out.println("第3到第5小元素：" + rangeKth(root, 3, 5));

        root = delete(root, 25);
        count = 0;
        kthSmallest(root, 3);
        System.out.println("刪除25後第3小元素：" + result);
    }
}
