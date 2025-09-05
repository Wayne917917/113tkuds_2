public class lt_36_valid_sudoku {
    public static boolean isValidSudoku(char[][] board) {
        boolean[][] row=new boolean[9][9], col=new boolean[9][9], box=new boolean[9][9];
        for(int i=0;i<9;i++) for(int j=0;j<9;j++){
            char c=board[i][j]; if(c=='.') continue;
            int d=c-'1', b=(i/3)*3+(j/3);
            if(row[i][d]||col[j][d]||box[b][d]) return false;
            row[i][d]=col[j][d]=box[b][d]=true;
        }
        return true;
    }
    public static void main(String[] args) {
        char[][] board = {
          {'5','3','.','.','7','.','.','.','.'},
          {'6','.','.','1','9','5','.','.','.'},
          {'.','9','8','.','.','.','.','6','.'},
          {'8','.','.','.','6','.','.','.','3'},
          {'4','.','.','8','.','3','.','.','1'},
          {'7','.','.','.','2','.','.','.','6'},
          {'.','6','.','.','.','.','2','8','.'},
          {'.','.','.','4','1','9','.','.','5'},
          {'.','.','.','.','8','.','.','7','9'}
        };
        System.out.println(isValidSudoku(board)); // true
    }
}
