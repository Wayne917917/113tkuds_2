public class AVLBasicExercise {
    class Node {
        int value, height;
        Node left, right;
        Node(int val) {
            this.value = val;
            this.height = 1;
        }
        int getBalance() {
            int lh = (left != null) ? left.height : 0;
            int rh = (right != null) ? right.height : 0;
            return lh - rh;
        }
        void updateHeight() {
            int lh = (left != null) ? left.height : 0;
            int rh = (right != null) ? right.height : 0;
            this.height = Math.max(lh, rh) + 1;
        }
    }

    private Node root;

    public void insert(int val) {
        root = insertRec(root, val);
    }

    private Node insertRec(Node node, int val) {
        if (node == null) return new Node(val);

        if (val < node.value)
            node.left = insertRec(node.left, val);
        else if (val > node.value)
            node.right = insertRec(node.right, val);
        else
            return node; // 不插入重複值

        node.updateHeight();
        int balance = node.getBalance();

        // LL
        if (balance > 1 && val < node.left.value)
            return rightRotate(node);
        // RR
        if (balance < -1 && val > node.right.value)
            return leftRotate(node);
        // LR
        if (balance > 1 && val > node.left.value) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // RL
        if (balance < -1 && val < node.right.value) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    public boolean search(int val) {
        return searchRec(root, val);
    }

    private boolean searchRec(Node node, int val) {
        if (node == null) return false;
        if (val == node.value) return true;
        if (val < node.value) return searchRec(node.left, val);
        return searchRec(node.right, val);
    }

    public int getHeight() {
        return (root == null) ? 0 : root.height;
    }

    public boolean isValidAVL() {
        return checkAVL(root) != -1;
    }

    private int checkAVL(Node node) {
        if (node == null) return 0;
        int lh = checkAVL(node.left);
        int rh = checkAVL(node.right);
        if (lh == -1 || rh == -1) return -1;
        if (Math.abs(lh - rh) > 1) return -1;
        return Math.max(lh, rh) + 1;
    }

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

    public void printInOrder() {
        printRec(root);
        System.out.println();
    }

    private void printRec(Node node) {
        if (node != null) {
            printRec(node.left);
            System.out.print(node.value + "(" + node.getBalance() + ") ");
            printRec(node.right);
        }
    }

    public static void main(String[] args) {
        AVLBasicExercise tree = new AVLBasicExercise();
        tree.insert(30);
        tree.insert(20);
        tree.insert(10); // LL
        tree.insert(25);
        tree.insert(40);
        tree.insert(50); // RR
        System.out.print("樹中序遍歷: ");
        tree.printInOrder();
        System.out.println("高度: " + tree.getHeight());
        System.out.println("搜尋 25: " + tree.search(25));
        System.out.println("AVL 有效性: " + tree.isValidAVL());
    }
}
