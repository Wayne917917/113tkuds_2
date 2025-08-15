import java.util.*;

public class AVLLeaderboardSystem {
    static class Node {
        String player;
        int score, size, height;
        Node left, right;
        Node(String p, int s) {
            player = p; score = s; size = 1; height = 1;
        }
        void updateHeightSize() {
            int lh = (left != null) ? left.height : 0;
            int rh = (right != null) ? right.height : 0;
            height = Math.max(lh, rh) + 1;
            int ls = (left != null) ? left.size : 0;
            int rs = (right != null) ? right.size : 0;
            size = ls + rs + 1;
        }
        int getBalance() {
            int lh = (left != null) ? left.height : 0;
            int rh = (right != null) ? right.height : 0;
            return lh - rh;
        }
    }

    private Node root;
    private Map<String, Integer> scoreMap = new HashMap<>();

    public void addOrUpdatePlayer(String player, int score) {
        if (scoreMap.containsKey(player)) {
            root = deleteRec(root, scoreMap.get(player), player);
        }
        scoreMap.put(player, score);
        root = insertRec(root, player, score);
    }

    private Node insertRec(Node node, String p, int s) {
        if (node == null) return new Node(p, s);
        if (s > node.score) node.left = insertRec(node.left, p, s);
        else if (s < node.score) node.right = insertRec(node.right, p, s);
        else {
            if (p.compareTo(node.player) < 0) node.left = insertRec(node.left, p, s);
            else if (p.compareTo(node.player) > 0) node.right = insertRec(node.right, p, s);
            else return node;
        }
        node.updateHeightSize();
        int balance = node.getBalance();
        if (balance > 1 && s > node.left.score) return rightRotate(node);
        if (balance < -1 && s < node.right.score) return leftRotate(node);
        if (balance > 1 && s < node.left.score) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && s > node.right.score) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    private Node deleteRec(Node node, int s, String p) {
        if (node == null) return null;
        if (s > node.score) node.left = deleteRec(node.left, s, p);
        else if (s < node.score) node.right = deleteRec(node.right, s, p);
        else {
            if (p.equals(node.player)) {
                if (node.left == null || node.right == null) {
                    return (node.left != null) ? node.left : node.right;
                }
                Node temp = findMax(node.right);
                node.player = temp.player;
                node.score = temp.score;
                node.right = deleteRec(node.right, temp.score, temp.player);
            } else if (p.compareTo(node.player) < 0) node.left = deleteRec(node.left, s, p);
            else node.right = deleteRec(node.right, s, p);
        }
        node.updateHeightSize();
        int balance = node.getBalance();
        if (balance > 1 && node.left.getBalance() >= 0) return rightRotate(node);
        if (balance > 1 && node.left.getBalance() < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && node.right.getBalance() <= 0) return leftRotate(node);
        if (balance < -1 && node.right.getBalance() > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    private Node findMax(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    public int getRank(String player) {
        if (!scoreMap.containsKey(player)) return -1;
        return getRankRec(root, scoreMap.get(player), player) + 1;
    }

    private int getRankRec(Node node, int s, String p) {
        if (node == null) return 0;
        if (s > node.score) return getRankRec(node.left, s, p);
        else if (s < node.score) return size(node.left) + 1 + getRankRec(node.right, s, p);
        else {
            if (p.equals(node.player)) return size(node.left);
            else if (p.compareTo(node.player) < 0) return getRankRec(node.left, s, p);
            else return size(node.left) + 1 + getRankRec(node.right, s, p);
        }
    }

    public List<String> getTopK(int k) {
        List<String> res = new ArrayList<>();
        getTopKRec(root, k, res);
        return res;
    }

    private void getTopKRec(Node node, int k, List<String> res) {
        if (node == null || res.size() >= k) return;
        getTopKRec(node.left, k, res);
        if (res.size() < k) res.add(node.player + "(" + node.score + ")");
        getTopKRec(node.right, k, res);
    }

    private int size(Node node) {
        return (node != null) ? node.size : 0;
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        y.updateHeightSize();
        x.updateHeightSize();
        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        x.updateHeightSize();
        y.updateHeightSize();
        return y;
    }

    public static void main(String[] args) {
        AVLLeaderboardSystem lb = new AVLLeaderboardSystem();
        lb.addOrUpdatePlayer("Alice", 90);
        lb.addOrUpdatePlayer("Bob", 85);
        lb.addOrUpdatePlayer("Carol", 95);
        lb.addOrUpdatePlayer("Dave", 88);
        System.out.println("Top 3: " + lb.getTopK(3));
        System.out.println("Rank of Bob: " + lb.getRank("Bob"));
        lb.addOrUpdatePlayer("Bob", 97);
        System.out.println("Top 3 after Bob update: " + lb.getTopK(3));
    }
}
