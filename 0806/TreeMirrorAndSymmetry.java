
public class TreeMirrorAndSymmetry {

    public static boolean isSymmetric(TreeNode root) {
        return root == null || isMirror(root.left, root.right);
    }

    private static boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val) &&
                isMirror(t1.left, t2.right) &&
                isMirror(t1.right, t2.left);
    }

    public static TreeNode mirror(TreeNode root) {
        if (root == null) return null;
        TreeNode temp = root.left;
        root.left = mirror(root.right);
        root.right = mirror(temp);
        return root;
    }

    public static boolean areMirror(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return (a.val == b.val) &&
                areMirror(a.left, b.right) &&
                areMirror(a.right, b.left);
    }

    public static boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) return false;
        if (isSameTree(s, t)) return true;
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private static boolean isSameTree(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return (a.val == b.val) &&
                isSameTree(a.left, b.left) &&
                isSameTree(a.right, b.right);
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        t1.left = new TreeNode(2);
        t1.right = new TreeNode(2);
        t1.left.left = new TreeNode(3);
        t1.right.right = new TreeNode(3);

        System.out.println("是否為對稱樹：" + isSymmetric(t1));

        TreeNode t2 = mirror(t1);
        System.out.println("鏡像轉換完成。是否與原樹鏡像：" + areMirror(t1, t2));

        TreeNode sub = new TreeNode(2);
        sub.right = new TreeNode(3);
        System.out.println("是否為子樹：" + isSubtree(t1, sub));
    }
}
