
class DLLNode {
    int val;
    DLLNode prev, next;
    DLLNode(int v) { val = v; }
}

public class BSTConversionAndBalance {

    static DLLNode head = null, prevNode = null;

    public static void convertToDLL(TreeNode root) {
        if (root == null) return;
        convertToDLL(root.left);
        DLLNode node = new DLLNode(root.val);
        if (prevNode == null) head = node;
        else {
            prevNode.next = node;
            node.prev = prevNode;
        }
        prevNode = node;
        convertToDLL(root.right);
    }

    public static TreeNode sortedArrayToBST(int[] arr, int l, int r) {
        if (l > r) return null;
        int mid = (l + r) / 2;
        TreeNode root = new TreeNode(arr[mid]);
        root.left = sortedArrayToBST(arr, l, mid - 1);
        root.right = sortedArrayToBST(arr, mid + 1, r);
        return root;
    }

    public static boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }

    private static int height(TreeNode node) {
        if (node == null) return 0;
        int l = height(node.left);
        int r = height(node.right);
        if (l == -1 || r == -1 || Math.abs(l - r) > 1) return -1;
        return 1 + Math.max(l, r);
    }

    public static int transformToGreaterSum(TreeNode root, int sum) {
        if (root == null) return sum;
        sum = transformToGreaterSum(root.right, sum);
        root.val += sum;
        sum = root.val;
        return transformToGreaterSum(root.left, sum);
    }

    public static void printDLL(DLLNode node) {
        System.out.print("雙向串列：");
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    public static void main(String[] args) {
        TreeNode bst = new TreeNode(5);
        bst.left = new TreeNode(3);
        bst.right = new TreeNode(8);

        convertToDLL(bst);
        printDLL(head);

        int[] sorted = {1, 2, 3, 4, 5, 6, 7};
        TreeNode balanced = sortedArrayToBST(sorted, 0, sorted.length - 1);
        System.out.println("是否為平衡BST：" + isBalanced(balanced));

        transformToGreaterSum(bst, 0);
        System.out.print("轉換為 greater sum tree：");
        inorder(bst);
        System.out.println();
    }
}
