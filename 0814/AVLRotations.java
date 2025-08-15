class AVLNode {
    int value;
    AVLNode left, right;
    int height;

    AVLNode(int value) {
        this.value = value;
        this.height = 1; // 葉節點高度為 1
    }

    void updateHeight() {
        int lh = (left == null) ? 0 : left.height;
        int rh = (right == null) ? 0 : right.height;
        this.height = Math.max(lh, rh) + 1;
    }

    int getBalance() {
        int lh = (left == null) ? 0 : left.height;
        int rh = (right == null) ? 0 : right.height;
        return lh - rh;
    }
}

public class AVLRotations {

    public static AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;
        x.right = y;
        y.left = T2;
        y.updateHeight();
        x.updateHeight();
        return x;
    }

    public static AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;
        y.left = x;
        x.right = T2;
        x.updateHeight();
        y.updateHeight();
        return y;
    }

    static void printInfo(String prefix, AVLNode node) {
        System.out.printf("%s 根結點%d,高度%d 平衡因子%d%n",
                prefix, node.value, node.height, node.getBalance());
    }

    public static void main(String[] args) {
        // === 測試右旋 ===
        AVLNode root = new AVLNode(30);
        root.left = new AVLNode(20);
        root.left.left = new AVLNode(10);
        root.updateHeight();
        root.left.updateHeight();

        System.out.print("測試右旋結果 ");
        printInfo("右旋前", root);
        root = rightRotate(root);
        printInfo("右旋後", root);

        // === 測試左旋 ===
        AVLNode root2 = new AVLNode(10);
        root2.right = new AVLNode(20);
        root2.right.right = new AVLNode(30);
        root2.updateHeight();
        root2.right.updateHeight();

        System.out.print("測試左旋結果 ");
        printInfo("左旋前", root2);
        root2 = leftRotate(root2);
        printInfo("左旋後", root2);
    }
}
