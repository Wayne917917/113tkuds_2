public class PersistentAVLExercise {
    static class Node {
        final int val, height;
        final Node left, right;
        Node(int v, Node l, Node r, int h) {
            val = v; left = l; right = r; height = h;
        }
    }

    private Node[] versions = new Node[100];
    private int versionCount = 0;

    public void insert(int version, int val) {
        versions[++versionCount] = insertRec(versions[version], val);
    }

    private Node insertRec(Node node, int val) {
        if (node == null) return new Node(val, null, null, 1);
        if (val < node.val) {
            Node newLeft = insertRec(node.left, val);
            return balance(new Node(node.val, newLeft, node.right, 0));
        } else if (val > node.val) {
            Node newRight = insertRec(node.right, val);
            return balance(new Node(node.val, node.left, newRight, 0));
        }
        return node;
    }

    private Node balance(Node node) {
        int lh = height(node.left);
        int rh = height(node.right);
        int newH = Math.max(lh, rh) + 1;
        node = new Node(node.val, node.left, node.right, newH);
        int bf = lh - rh;
        if (bf > 1) {
            if (height(node.left.left) >= height(node.left.right)) {
                return rightRotate(node);
            } else {
                return rightRotate(new Node(node.val, leftRotate(node.left), node.right, 0));
            }
        } else if (bf < -1) {
            if (height(node.right.right) >= height(node.right.left)) {
                return leftRotate(node);
            } else {
                return leftRotate(new Node(node.val, node.left, rightRotate(node.right), 0));
            }
        }
        return node;
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        return new Node(x.val, x.left, new Node(y.val, T2, y.right, 0), 0);
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        return new Node(y.val, new Node(x.val, x.left, T2, 0), y.right, 0);
    }

    private int height(Node n) {
        return (n == null) ? 0 : n.height;
    }

    public void printVersion(int version) {
        printInOrder(versions[version]);
        System.out.println();
    }

    private void printInOrder(Node node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.val + " ");
            printInOrder(node.right);
        }
    }

    public static void main(String[] args) {
        PersistentAVLExercise pavl = new PersistentAVLExercise();
        pavl.insert(0, 10);
        pavl.insert(1, 20);
        pavl.insert(2, 5);
        pavl.insert(3, 15);
        System.out.println("版本 0:"); pavl.printVersion(0);
        System.out.println("版本 1:"); pavl.printVersion(1);
        System.out.println("版本 2:"); pavl.printVersion(2);
        System.out.println("版本 3:"); pavl.printVersion(3);
        System.out.println("版本 4:"); pavl.printVersion(4);
    }
}
