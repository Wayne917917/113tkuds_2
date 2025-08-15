class AVLNode {
    int data;
    int height;
    AVLNode left, right;

    AVLNode(int data) {
        this.data = data;
        this.height = 1; // 新節點高度設為 1
    }

    void updateHeight() {
        int lh = (left != null) ? left.height : 0;
        int rh = (right != null) ? right.height : 0;
        height = Math.max(lh, rh) + 1;
    }

    int getBalance() {
        int lh = (left != null) ? left.height : 0;
        int rh = (right != null) ? right.height : 0;
        return lh - rh;
    }
}

// 旋轉工具類別
class AVLRotations {
    // 右旋
    public static AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = (x != null) ? x.right : null;
        x.right = y;
        y.left = T2;
        y.updateHeight();
        x.updateHeight();
        return x;
    }

    // 左旋
    public static AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = (y != null) ? y.left : null;
        y.left = x;
        x.right = T2;
        x.updateHeight();
        y.updateHeight();
        return y;
    }
}

// AVL 樹類別
public class AVLTree {
    private AVLNode root;

    // 插入
    public void insert(int data) {
        root = insertNode(root, data);
    }

    private AVLNode insertNode(AVLNode node, int data) {
        if (node == null) return new AVLNode(data);

        if (data < node.data)
            node.left = insertNode(node.left, data);
        else if (data > node.data)
            node.right = insertNode(node.right, data);
        else
            return node; // 不插入重複值

        node.updateHeight();
        int balance = node.getBalance();

        // LL
        if (balance > 1 && data < node.left.data)
            return AVLRotations.rightRotate(node);

        // RR
        if (balance < -1 && data > node.right.data)
            return AVLRotations.leftRotate(node);

        // LR
        if (balance > 1 && data > node.left.data) {
            node.left = AVLRotations.leftRotate(node.left);
            return AVLRotations.rightRotate(node);
        }

        // RL
        if (balance < -1 && data < node.right.data) {
            node.right = AVLRotations.rightRotate(node.right);
            return AVLRotations.leftRotate(node);
        }

        return node;
    }

    // 刪除
    public void delete(int data) {
        root = deleteNode(root, data);
    }

    private AVLNode deleteNode(AVLNode node, int data) {
        if (node == null) return null;

        if (data < node.data) {
            node.left = deleteNode(node.left, data);
        } else if (data > node.data) {
            node.right = deleteNode(node.right, data);
        } else {
            // 單子或無子
            if (node.left == null || node.right == null) {
                AVLNode temp = (node.left != null) ? node.left : node.right;
                return temp; // 直接回傳替代節點
            } else {
                // 兩子節點 → 找右子樹最小值替換
                AVLNode temp = findMin(node.right);
                node.data = temp.data;
                node.right = deleteNode(node.right, temp.data);
            }
        }

        node.updateHeight();
        int balance = node.getBalance();

        // LL
        if (balance > 1 && (node.left != null && node.left.getBalance() >= 0))
            return AVLRotations.rightRotate(node);

        // LR
        if (balance > 1 && (node.left != null && node.left.getBalance() < 0)) {
            node.left = AVLRotations.leftRotate(node.left);
            return AVLRotations.rightRotate(node);
        }

        // RR
        if (balance < -1 && (node.right != null && node.right.getBalance() <= 0))
            return AVLRotations.leftRotate(node);

        // RL
        if (balance < -1 && (node.right != null && node.right.getBalance() > 0)) {
            node.right = AVLRotations.rightRotate(node.right);
            return AVLRotations.leftRotate(node);
        }

        return node;
    }

    private AVLNode findMin(AVLNode node) {
        while (node.left != null)
            node = node.left;
        return node;
    }

    // 搜尋
    public boolean search(int data) {
        return searchNode(root, data);
    }

    private boolean searchNode(AVLNode node, int data) {
        if (node == null) return false;
        if (data == node.data) return true;
        return data < node.data
            ? searchNode(node.left, data)
            : searchNode(node.right, data);
    }

    // 驗證 AVL
    public boolean isValidAVL() {
        return checkAVL(root) != -1;
    }

    private int checkAVL(AVLNode node) {
        if (node == null) return 0;
        int lh = checkAVL(node.left);
        int rh = checkAVL(node.right);
        if (lh == -1 || rh == -1) return -1;
        if (Math.abs(lh - rh) > 1) return -1;
        return Math.max(lh, rh) + 1;
    }

    // 印樹
    public void printTree() {
        printInOrder(root);
        System.out.println();
    }

    private void printInOrder(AVLNode node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.data + "(" + node.getBalance() + ") ");
            printInOrder(node.right);
        }
    }

    // 測試
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        // 插入測試
        tree.insert(30);
        tree.insert(20);
        tree.insert(10); // 觸發右旋
        System.out.print("插入後: ");
        tree.printTree();

        // 刪除測試
        tree.delete(20);
        System.out.print("刪除後: ");
        tree.printTree();

        // 搜尋測試
        System.out.println("搜尋 10: " + tree.search(10));
        System.out.println("搜尋 50: " + tree.search(50));

        // 驗證 AVL
        System.out.println("AVL 有效性: " + tree.isValidAVL());
    }
}
