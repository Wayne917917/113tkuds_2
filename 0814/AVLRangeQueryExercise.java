import java.util.*;

public class AVLRangeQueryExercise {
    class Node {
        int val, height;
        Node left, right;
        Node(int v) { val = v; height = 1; }
    }

    private Node root;

    public void insert(int val) {
        // TODO: AVL 插入
    }

    public List<Integer> rangeQuery(int min, int max) {
        List<Integer> result = new ArrayList<>();
        rangeQueryRec(root, min, max, result);
        return result;
    }

    private void rangeQueryRec(Node node, int min, int max, List<Integer> res) {
        // TODO: 中序遍歷 + 剪枝
    }

    public static void main(String[] args) {
        AVLRangeQueryExercise tree = new AVLRangeQueryExercise();
        // TODO: 插入數據並測試範圍查詢
    }
}
