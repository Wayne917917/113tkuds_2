import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int x) { val = x; }
}

public class BinaryTreeBasicOperations {

    public static int sum(TreeNode root) {
        if (root == null) return 0;
        return root.val + sum(root.left) + sum(root.right);
    }

    public static int count(TreeNode root) {
        if (root == null) return 0;
        return 1 + count(root.left) + count(root.right);
    }

    public static int max(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        return Math.max(root.val, Math.max(max(root.left), max(root.right)));
    }

    public static int min(TreeNode root) {
        if (root == null) return Integer.MAX_VALUE;
        return Math.min(root.val, Math.min(min(root.left), min(root.right)));
    }

    public static int maxWidth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int max = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            max = Math.max(max, size);
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
        }
        return max;
    }

    public static boolean isComplete(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        boolean end = false;
        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            if (curr == null) {
                end = true;
            } else {
                if (end) return false;
                q.add(curr.left);
                q.add(curr.right);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(7);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(8);

        System.out.println("總和：" + sum(root));
        System.out.println("平均：" + (sum(root) / (double) count(root)));
        System.out.println("最大值：" + max(root));
        System.out.println("最小值：" + min(root));
        System.out.println("最大寬度：" + maxWidth(root));
        System.out.println("是否為完全二元樹：" + isComplete(root));
    }
}
