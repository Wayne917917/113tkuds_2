import java.util.*;

public class LevelOrderTraversalVariations {
    public static void levelOrderByLevel(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                level.add(node.val);
                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
            result.add(level);
        }
        System.out.println("分層走訪：" + result);
    }

    public static void zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        boolean leftToRight = true;
        while (!q.isEmpty()) {
            int size = q.size();
            LinkedList<Integer> level = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (leftToRight) level.addLast(node.val);
                else level.addFirst(node.val);
                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
            leftToRight = !leftToRight;
            result.add(level);
        }
        System.out.println("之字形走訪：" + result);
    }

    public static void printRightmost(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        System.out.print("每層最後節點：");
        while (!q.isEmpty()) {
            int size = q.size();
            TreeNode last = null;
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (i == size - 1) last = node;
                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
            System.out.print(last.val + " ");
        }
        System.out.println();
    }

    public static void verticalOrder(TreeNode root) {
        if (root == null) return;
        Map<Integer, List<Integer>> map = new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(root, 0));
        while (!q.isEmpty()) {
            Pair p = q.poll();
            map.putIfAbsent(p.col, new ArrayList<>());
            map.get(p.col).add(p.node.val);
            if (p.node.left != null) q.add(new Pair(p.node.left, p.col - 1));
            if (p.node.right != null) q.add(new Pair(p.node.right, p.col + 1));
        }
        System.out.println("垂直層序走訪：" + map.values());
    }

    static class Pair {
        TreeNode node;
        int col;
        Pair(TreeNode n, int c) {
            node = n;
            col = c;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        levelOrderByLevel(root);
        zigzagLevelOrder(root);
        printRightmost(root);
        verticalOrder(root);
    }
}
