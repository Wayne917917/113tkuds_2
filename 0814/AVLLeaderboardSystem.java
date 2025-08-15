import java.util.*;

public class AVLLeaderboardSystem {
    class Node {
        int score, size, height;
        String player;
        Node left, right;
        Node(String player, int score) {
            this.player = player;
            this.score = score;
            this.size = 1;
            this.height = 1;
        }
    }

    private Node root;

    public void addOrUpdatePlayer(String player, int score) {
        // TODO: 插入或更新玩家分數
    }

    public int getRank(String player) {
        // TODO: 查詢玩家排名
        return -1;
    }

    public List<String> getTopK(int k) {
        // TODO: 前 K 名玩家
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        AVLLeaderboardSystem lb = new AVLLeaderboardSystem();
        // TODO: 測試添加、更新、查詢排名、前 K
    }
}
