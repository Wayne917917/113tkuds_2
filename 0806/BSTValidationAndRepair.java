import java.util.*;

public class BSTValidationAndRepair {

    static TreeNode first, second, prev;

    public static boolean isValidBST(TreeNode root) {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean isValid(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return isValid(node.left, min, node.val) && isValid(node.right, node.val, max);
    }

    public static void recoverBST(TreeNode root) {
        first = second = prev = null;
        inorder(root);
        if (first != null && second != null) {
            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }
    }

    private static void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        if (prev != null && root.val < prev.val) {
            if (first == null) first = prev;
            second = root;
        }
        prev = root;
        inorder(root.right);
    }

    public static int nodesToRemove(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorderCollect(root, list);
        int len = list.size();
        int[] dp = new int[len];
        int maxLen = 0;
        for (int i = 0; i < len; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++)
                if (list.get(j) < list.get(i))
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            maxLen = Math.max(maxLen, dp[i]);
        }
        return len - maxLen;
    }

    private static void inorderCollect(TreeNode root, List<Integer> list) {
        if (root == null) return;
        inorderCollect(root.left, list);
        list.add(root.val);
        inorderCollect(root.right, list);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2); // BST錯誤節點

        System.out.println("是否為合法BST：" + isValidBST(root));
        recoverBST(root);
        System.out.println("修復後是否為合法BST：" + isValidBST(root));
        System.out.println("最少需移除節點數：" + nodesToRemove(root));
    }
}
