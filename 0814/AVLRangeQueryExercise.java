import java.util.*;

public class AVLRangeQueryExercise {
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

    public void insert(int val) {
        root = insertRec(root, val);
    }

    private Node insertRec(Node node, int val) {
        if (node == null) return new Node(val);
        if (val < node.val) node.left = insertRec(node.left, val);
        else if (val > node.val) node.right = insertRec(node.right, val);
        else return node;
        node.updateHeight();
        int balance = node.getBalance();
        if (balance > 1 && val < node.left.val) return rightRotate(node);
        if (balance < -1 && val > node.right.val) return leftRotate(node);
        if (balance > 1 && val > node.left.val) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && val < node.right.val) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    public List<Integer> rangeQuery(int min, int max) {
        List<Integer> res = new ArrayList<>();
        rangeRec(root, min, max, res);
        return res;
    }

    private void rangeRec(Node node, int min, int max, List<Integer> res) {
        if (node == null) return;
        if (node.val > min) rangeRec(node.left, min, max, res);
        if (node.val >= min && node.val <= max) res.add(node.val);
        if (node.val < max) rangeRec(node.right, min, max, res);
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

    public static void main(String[] args) {
        AVLRangeQueryExercise tree = new AVLRangeQueryExercise();
        int[] vals = {20, 10, 30, 5, 15, 25, 35};
        for (int v : vals) tree.insert(v);
        System.out.println("範圍查詢 [12, 27]: " + tree.rangeQuery(12, 27));
    }
}
