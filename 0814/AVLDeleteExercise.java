public class AVLDeleteExercise {
    static class Node {
        int val, height;
        Node left, right;
        Node(int v) { val = v; height = 1; }
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

    private Node root;

    // ===== 插入 =====
    public void insert(int val) {
        root = insertRec(root, val);
    }

    private Node insertRec(Node node, int val) {
        if (node == null) return new Node(val);

        if (val < node.val)
            node.left = insertRec(node.left, val);
        else if (val > node.val)
            node.right = insertRec(node.right, val);
        else
            return node;

        node.updateHeight();
        return rebalance(node, val);
    }

    // ===== 刪除 =====
    public void delete(int val) {
        root = deleteRec(root, val);
    }

    private Node deleteRec(Node node, int val) {
        if (node == null) return null;

        if (val < node.val) {
            node.left = deleteRec(node.left, val);
        } else if (val > node.val) {
            node.right = deleteRec(node.right, val);
        } else {
            // 找到要刪除的節點
            if (node.left == null || node.right == null) {
                Node temp = (node.left != null) ? node.left : node.right;
                return temp;
            } else {
                Node temp = findMin(node.right);
                node.val = temp.val;
                node.right = deleteRec(node.right, temp.val);
            }
        }

        node.updateHeight();
        return rebalanceAfterDelete(node);
    }

    private Node findMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    // ===== 平衡處理 =====
    private Node rebalance(Node node, int insertedVal) {
        int balance = node.getBalance();

        // LL
        if (balance > 1 && insertedVal < node.left.val)
            return rightRotate(node);

        // RR
        if (balance < -1 && insertedVal > node.right.val)
            return leftRotate(node);

        // LR
        if (balance > 1 && insertedVal > node.left.val) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL
        if (balance < -1 && insertedVal < node.right.val) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private Node rebalanceAfterDelete(Node node) {
        int balance = node.getBalance();

        // LL
        if (balance > 1 && node.left.getBalance() >= 0)
            return rightRotate(node);

        // LR
        if (balance > 1 && node.left.getBalance() < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RR
        if (balance < -1 && node.right.getBalance() <= 0)
            return leftRotate(node);

        // RL
        if (balance < -1 && node.right.getBalance() > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // ===== 旋轉操作 =====
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        y.updateHeight();
        x.updateHeight();
        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        x.updateHeight();
        y.updateHeight();
        return y;
    }

    // ===== 列印樹（中序） =====
    public void printTree() {
        printRec(root);
        System.out.println();
    }

    private void printRec(Node node) {
        if (node != null) {
            printRec(node.left);
            System.out.print(node.val + "(" + node.getBalance() + ") ");
            printRec(node.right);
        }
    }

    // ===== 測試 =====
    public static void main(String[] args) {
        AVLDeleteExercise tree = new AVLDeleteExercise();

        // 插入
        int[] vals = {30, 20, 40, 10, 25, 35, 50};
        for (int v : vals) tree.insert(v);
        System.out.print("插入後: ");
        tree.printTree();

        // 刪除葉節點
        tree.delete(10);
        System.out.print("刪除葉節點(10)後: ");
        tree.printTree();

        // 刪除只有一個子節點的節點
        tree.delete(50);
        System.out.print("刪除單子節點(50)後: ");
        tree.printTree();

        // 刪除有兩個子節點的節點
        tree.delete(30);
        System.out.print("刪除雙子節點(30)後: ");
        tree.printTree();
    }
}
