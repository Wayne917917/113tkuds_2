public class RecursionVsIteration {
    public static int binomialRecursive(int n, int k) {
        if (k == 0 || k == n) return 1;
        return binomialRecursive(n - 1, k - 1) + binomialRecursive(n - 1, k);
    }

    public static int binomialIterative(int n, int k) {
        int[][] C = new int[n+1][k+1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= Math.min(i,k); j++) {
                if (j == 0 || j == i)
                    C[i][j] = 1;
                else
                    C[i][j] = C[i-1][j-1] + C[i-1][j];
            }
        }
        return C[n][k];
    }

    public static int productRecursive(int[] arr, int idx) {
        if (idx >= arr.length) return 1;
        return arr[idx] * productRecursive(arr, idx + 1);
    }

    public static int productIterative(int[] arr) {
        int result = 1;
        for (int n : arr) result *= n;
        return result;
    }

    public static int countVowelsRecursive(String s, int idx) {
        if (idx >= s.length()) return 0;
        char c = Character.toLowerCase(s.charAt(idx));
        return (isVowel(c) ? 1 : 0) + countVowelsRecursive(s, idx + 1);
    }

    public static int countVowelsIterative(String s) {
        int count = 0;
        for (char c : s.toLowerCase().toCharArray())
            if (isVowel(c)) count++;
        return count;
    }

    public static boolean isBalanced(String str, int count) {
        if (count < 0) return false;
        if (str.isEmpty()) return count == 0;
        char c = str.charAt(0);
        if (c == '(') return isBalanced(str.substring(1), count + 1);
        else if (c == ')') return isBalanced(str.substring(1), count - 1);
        else return isBalanced(str.substring(1), count);
    }

    private static boolean isVowel(char c) {
        return "aeiou".indexOf(c) != -1;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 4};
        System.out.println("Binomial (recursive) C(5,2): " + binomialRecursive(5, 2));
        System.out.println("Binomial (iterative) C(5,2): " + binomialIterative(5, 2));

        System.out.println("Product (recursive): " + productRecursive(arr, 0));
        System.out.println("Product (iterative): " + productIterative(arr));

        String test = "Education";
        System.out.println("Vowels (recursive): " + countVowelsRecursive(test, 0));
        System.out.println("Vowels (iterative): " + countVowelsIterative(test));

        System.out.println("Balanced '(()())': " + isBalanced("(()())", 0));
        System.out.println("Balanced '(()))(': " + isBalanced("(()))(", 0));
    }
}
