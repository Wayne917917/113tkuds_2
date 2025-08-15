public class AVLBasicExercise {
    class Node {
        int value, height;
        Node left, right;
        Node(int val) {
            this.value = val;
            this.height = 1;
        }
    }

    private Node root;

    // 插入節點
    public void insert(int val) {
        root = insertRec(root, val);
    }

    private Node insertRec(Node node, int val) {
        // TODO: 標準 BST 插入 + 平衡檢查
        return node;
    }

    // 搜尋節點
    public boolean search(int val) {
        // TODO: BST 搜尋
        return false;
    }

    // 計算高度
    public int getHeight() {
        return heightRec(root);
    }

    private int heightRec(Node node) {
        // TODO: 遞迴計算高度
        return 0;
    }

    // 驗證是否為有效 AVL
    public boolean isValidAVL() {
        return checkAVL(root) != -1;
    }

    private int checkAVL(Node node) {
        // TODO: 驗證平衡因子範圍
        return 0;
    }

    public static void main(String[] args) {
        AVLBasicExercise tree = new AVLBasicExercise();
        // TODO: 測試插入、搜尋、高度、驗證
    }
}
