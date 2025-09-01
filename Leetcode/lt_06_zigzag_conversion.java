public class lt_06_zigzag_conversion {

    public static String convert(String s, int numRows) {
        if (numRows == 1 || numRows >= s.length()) return s;

        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) rows[i] = new StringBuilder();

        int r = 0, dir = 1; // dir: 1 往下, -1 往上
        for (int i = 0; i < s.length(); i++) {
            rows[r].append(s.charAt(i));
            if (r == 0) dir = 1;
            else if (r == numRows - 1) dir = -1;
            r += dir;
        }

        StringBuilder ans = new StringBuilder();
        for (StringBuilder row : rows) ans.append(row);
        return ans.toString();
    }

    // 測試
    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING", 3)); // PAHNAPLSIIGYIR
        System.out.println(convert("PAYPALISHIRING", 4)); // PINALSIGYAHRPI
        System.out.println(convert("A", 1));              // A
        System.out.println(convert("AB", 1));             // AB
    }
}
