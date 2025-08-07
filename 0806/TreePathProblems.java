import java.util.*;

public class TreePathProblems {

    public static void printPaths(TreeNode root, List<Integer> path) {
        if (root == null) return;
        path.add(root.val);
        if (root.left == null && root.right == null) {
            System.out.println("根到葉路徑：" + path);
        } else {
            printPaths(root.left, new ArrayList<>(path));
            printPaths(root.right, new ArrayList<>(path));
        }
    }

    public static boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return root.val == sum;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    public static int maxRootToLeafSum(TreeNode root) {
        if (root == null) return 0;
        return root.val + Math.max(maxRootToLeafSum(root.left), maxRootToLeafSum(root.right));
    }

    static int maxPathSum = Integer.MIN_VALUE;
    public static int findMaxPathSum(TreeNode root) {
        maxGain(root);
        return maxPathSum;
    }

    private static int maxGain(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, maxGain(node.left));
        int right = Math.max(0, maxGain(node.right));
        maxPathSum = Math.max(maxPathSum, left + right + node.val);
        return node.val + Math.max(left, right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.right.right = new TreeNode(11);

        printPaths(root, new ArrayList<>());
        System.out.println("是否存在總和為18的路徑：" + hasPathSum(root, 18));
        System.out.println("最大根到葉總和：" + maxRootToLeafSum(root));
        System.out.println("任兩節點最大路徑和：" + findMaxPathSum(root));
    }
}
