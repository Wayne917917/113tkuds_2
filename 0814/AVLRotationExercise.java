public class AVLRotationExercise {
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

    // 右旋
    public static Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        y.updateHeight();
        x.updateHeight();
        return x;
    }

    // 左旋
    public static Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        x.updateHeight();
        y.updateHeight();
        return y;
    }

    // 左右旋
    public static Node leftRightRotate(Node node) {
        node.left = leftRotate(node.left);
        return rightRotate(node);
    }

    // 右左旋
    public static Node rightLeftRotate(Node node) {
        node.right = rightRotate(node.right);
        return leftRotate(node);
    }

    private static void printInfo(String msg, Node root) {
        System.out.printf("%s 根結點%d,高度%d,平衡因子%d%n",
                msg, root.val, root.height, root.getBalance());
    }

    public static void main(String[] args) {
        // LL → 右旋
        Node ll = new Node(30);
        ll.left = new Node(20);
        ll.left.left = new Node(10);
        ll.updateHeight(); ll.left.updateHeight();
        printInfo("LL 旋轉前", ll);
        ll = rightRotate(ll);
        printInfo("LL 旋轉後", ll);

        // RR → 左旋
        Node rr = new Node(10);
        rr.right = new Node(20);
        rr.right.right = new Node(30);
        rr.updateHeight(); rr.right.updateHeight();
        printInfo("RR 旋轉前", rr);
        rr = leftRotate(rr);
        printInfo("RR 旋轉後", rr);

        // LR → 左右旋
        Node lr = new Node(30);
        lr.left = new Node(10);
        lr.left.right = new Node(20);
        lr.updateHeight(); lr.left.updateHeight();
        printInfo("LR 旋轉前", lr);
        lr = leftRightRotate(lr);
        printInfo("LR 旋轉後", lr);

        // RL → 右左旋
        Node rl = new Node(10);
        rl.right = new Node(30);
        rl.right.left = new Node(20);
        rl.updateHeight(); rl.right.updateHeight();
        printInfo("RL 旋轉前", rl);
        rl = rightLeftRotate(rl);
        printInfo("RL 旋轉後", rl);
    }
}
