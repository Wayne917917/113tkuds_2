// 檔名：AVLRotations.java
// 功能：AVL 樹左旋、右旋，並測試旋轉效果

class AVLNode {
    int value;
    AVLNode left, right;
    int height;

    AVLNode(int value) {
        this.value = value;
        this.height = 1; // 葉節點高度為 1
    }

    // 更新高度
    void updateHeight() {
        int lh = (left == null) ? 0 : left.height;
        int rh = (right == null) ? 0 : right.height;
        this.height = Math.max(lh, rh) + 1;
    }
}

public class AVLRotations {

    // 右旋操作
    public static AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        // 執行旋轉
        x.right = y;
        y.left = T2;

        // 更新高度
        y.updateHeight();
        x.updateHeight();

        return x; // 新的根節點
    }

    // 左旋操作
    public static AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        // 執行旋轉
        y.left = x;
        x.right = T2;

        // 更新高度
        x.updateHeight();
        y.updateHeight();

        return y; // 新的根節點
    }

    // 測試入口
    public static void main(String[] args) {
        // 建立 LL 失衡的樹
        AVLNode root = new AVLNode(10);
        root.left = new AVLNode(5);
        root.left.left = new AVLNode(3);
        root.updateHeight();
        root.left.updateHeight();

        System.out.println("=== LL 情況（右旋） ===");
        System.out.println("旋轉前根節點：" + root.value);
        root = rightRotate(root);
        System.out.println("旋轉後根節點：" + root.value);
        System.out.println("左子：" + (root.left != null ? root.left.value : "null"));
        System.out.println("右子：" + (root.right != null ? root.right.value : "null"));

        // 建立 RR 失衡的樹
        AVLNode root2 = new AVLNode(10);
        root2.right = new AVLNode(15);
        root2.right.right = new AVLNode(20);
        root2.updateHeight();
        root2.right.updateHeight();

        System.out.println("\n=== RR 情況（左旋） ===");
        System.out.println("旋轉前根節點：" + root2.value);
        root2 = leftRotate(root2);
        System.out.println("旋轉後根節點：" + root2.value);
        System.out.println("左子：" + (root2.left != null ? root2.left.value : "null"));
        System.out.println("右子：" + (root2.right != null ? root2.right.value : "null"));
    }
}
