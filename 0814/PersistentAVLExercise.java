public class PersistentAVLExercise {
    static class Node {
        final int val, height;
        final Node left, right;
        Node(int val, Node left, Node right, int height) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.height = height;
        }
    }

    private Node[] versions = new Node[100];
    private int versionCount = 0;

    public void insert(int version, int val) {
        versions[++versionCount] = insertRec(versions[version], val);
    }

    private Node insertRec(Node node, int val) {
        // TODO: 路徑複製實作
        return node;
    }

    public void printVersion(int version) {
        // TODO: 輸出指定版本
    }

    public static void main(String[] args) {
        PersistentAVLExercise tree = new PersistentAVLExercise();
        // TODO: 測試多版本插入與查詢
    }
}
