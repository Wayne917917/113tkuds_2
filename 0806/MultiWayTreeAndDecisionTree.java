import java.util.*;

class MNode {
    String name;
    List<MNode> children = new ArrayList<>();
    MNode(String n) { name = n; }
}

public class MultiWayTreeAndDecisionTree {

    public static void dfs(MNode node) {
        if (node == null) return;
        System.out.print(node.name + " ");
        for (MNode child : node.children)
            dfs(child);
    }

    public static void bfs(MNode root) {
        Queue<MNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            MNode node = q.poll();
            System.out.print(node.name + " ");
            q.addAll(node.children);
        }
    }

    public static int height(MNode node) {
        if (node == null) return 0;
        int max = 0;
        for (MNode child : node.children)
            max = Math.max(max, height(child));
        return max + 1;
    }

    public static void degree(MNode node, Map<String, Integer> degrees) {
        if (node == null) return;
        degrees.put(node.name, node.children.size());
        for (MNode child : node.children)
            degree(child, degrees);
    }

    public static void main(String[] args) {
        MNode root = new MNode("Start");
        MNode n1 = new MNode("Guess < 50");
        MNode n2 = new MNode("Guess >= 50");
        root.children.add(n1);
        root.children.add(n2);
        n1.children.add(new MNode("Too Low"));
        n2.children.add(new MNode("Correct"));
        n2.children.add(new MNode("Too High"));

        System.out.print("DFS："); dfs(root); System.out.println();
        System.out.print("BFS："); bfs(root); System.out.println();
        System.out.println("樹高度：" + height(root));

        Map<String, Integer> degrees = new HashMap<>();
        degree(root, degrees);
        System.out.println("每節點度數：" + degrees);
    }
}
