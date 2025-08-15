public class AVLDeleteExercise {
    class Node {
        int val, height;
        Node left, right;
        Node(int v) { val = v; height = 1; }
    }

    private Node root;

    public void insert(int val) {
        // TODO: 標準 AVL 插入
    }

    public void delete(int val) {
        root = deleteRec(root, val);
    }

    private Node deleteRec(Node node, int val) {
        // TODO: 三種刪除情況 + 平衡修復
        return node;
    }

    public static void main(String[] args) {
        AVLDeleteExercise tree = new AVLDeleteExercise();
        // TODO: 測試刪除葉節點、單子節點、雙子節點
    }
}
