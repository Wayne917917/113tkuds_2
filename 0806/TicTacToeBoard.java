import java.util.Scanner;

public class TicTacToeBoard {
    static char[][] board = new char[3][3];

    public static void main(String[] args) {
        initBoard();
        Scanner sc = new Scanner(System.in);
        char player = 'X';

        while (true) {
            printBoard();
            System.out.println("Player " + player + ", enter row and col (0-2): ");
            int row = sc.nextInt(), col = sc.nextInt();

            if (!isValidMove(row, col)) {
                System.out.println("Invalid move. Try again.");
                continue;
            }

            board[row][col] = player;

            if (checkWin(player)) {
                printBoard();
                System.out.println("Player " + player + " wins!");
                break;
            }

            if (isBoardFull()) {
                printBoard();
                System.out.println("It's a tie!");
                break;
            }

            player = (player == 'X') ? 'O' : 'X';
        }
        sc.close();
    }

    static void initBoard() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = '.';
    }

    static void printBoard() {
        for (char[] row : board) {
            for (char c : row)
                System.out.print(c + " ");
            System.out.println();
        }
    }

    static boolean isValidMove(int r, int c) {
        return r >= 0 && r < 3 && c >= 0 && c < 3 && board[r][c] == '.';
    }

    static boolean checkWin(char p) {
        for (int i = 0; i < 3; i++)
            if ((board[i][0] == p && board[i][1] == p && board[i][2] == p) ||
                (board[0][i] == p && board[1][i] == p && board[2][i] == p))
                return true;
        return (board[0][0] == p && board[1][1] == p && board[2][2] == p) ||
               (board[0][2] == p && board[1][1] == p && board[2][0] == p);
    }

    static boolean isBoardFull() {
        for (char[] row : board)
            for (char c : row)
                if (c == '.') return false;
        return true;
    }
}
