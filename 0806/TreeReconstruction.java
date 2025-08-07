
public class TreeReconstruction {

    static int preIndex = 0;

    public static TreeNode buildFromPreIn(int[] pre, int[] in, int inStart, int inEnd) {
        if (inStart > inEnd) return null;
        TreeNode root = new TreeNode(pre[preIndex++]);
        int inIndex = findIndex(in, inStart, inEnd, root.val);
        root.left = buildFromPreIn(pre, in, inStart, inIndex - 1);
        root.right = buildFromPreIn(pre, in, inIndex + 1, inEnd);
        return root;
    }

    public static TreeNode buildFromPostIn(int[] post, int[] in, int inStart, int inEnd, int[] postIndex) {
        if (inStart > inEnd) return null;
        TreeNode root = new TreeNode(post[postIndex[0]--]);
        int inIndex = findIndex(in, inStart, inEnd, root.val);
        root.right = buildFromPostIn(post, in, inIndex + 1, inEnd, postIndex);
        root.left = buildFromPostIn(post, in, inStart, inIndex - 1, postIndex);
        return root;
    }

    public static TreeNode buildFromLevel(int[] level) {
        if (level.length == 0) return null;
        TreeNode[] nodes = new TreeNode[level.length];
        for (int i = 0; i < level.length; i++) {
            if (level[i] != -1) nodes[i] = new TreeNode(level[i]);
        }
        for (int i = 0; i < level.length; i++) {
            if (nodes[i] != null) {
                int left = 2 * i + 1;
                int right = 2 * i + 2;
                if (left < level.length) nodes[i].left = nodes[left];
                if (right < level.length) nodes[i].right = nodes[right];
            }
        }
        return nodes[0];
    }

    private static int findIndex(int[] arr, int start, int end, int val) {
        for (int i = start; i <= end; i++)
            if (arr[i] == val) return i;
        return -1;
    }

    public static void inorderPrint(TreeNode root) {
        if (root == null) return;
        inorderPrint(root.left);
        System.out.print(root.val + " ");
        inorderPrint(root.right);
    }

    public static void main(String[] args) {
        int[] pre = {3, 9, 20, 15, 7};
        int[] in = {9, 3, 15, 20, 7};
        TreeNode root1 = buildFromPreIn(pre, in, 0, in.length - 1);
        System.out.print("前+中建樹中序：");
        inorderPrint(root1);
        System.out.println();

        int[] post = {9, 15, 7, 20, 3};
        int[] in2 = {9, 3, 15, 20, 7};
        TreeNode root2 = buildFromPostIn(post, in2, 0, in2.length - 1, new int[]{post.length - 1});
        System.out.print("後+中建樹中序：");
        inorderPrint(root2);
        System.out.println();

        int[] level = {1, 2, 3, 4, 5, -1, 6};
        TreeNode root3 = buildFromLevel(level);
        System.out.print("層序建完全樹中序：");
        inorderPrint(root3);
        System.out.println();
    }
}
