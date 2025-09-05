public class lt_37_sudoku_solver {
    static int[] row=new int[9], col=new int[9], box=new int[9]; static char[][] b;
    public static void solveSudoku(char[][] board){ b=board;
        for(int i=0;i<9;i++) for(int j=0;j<9;j++) if(board[i][j]!='.'){
            int d=board[i][j]-'1',m=1<<d,k=(i/3)*3+(j/3); row[i]|=m; col[j]|=m; box[k]|=m;
        }
        dfs(0,0);
    }
    static boolean dfs(int i,int j){
        if(i==9) return true; int ni=(j==8)?i+1:i, nj=(j+1)%9;
        if(b[i][j]!='.') return dfs(ni,nj);
        int k=(i/3)*3+(j/3), used=row[i]|col[j]|box[k];
        for(int d=0; d<9; d++){
            int m=1<<d; if((used&m)==0){
                b[i][j]=(char)('1'+d); row[i]|=m; col[j]|=m; box[k]|=m;
                if(dfs(ni,nj)) return true;
                row[i]&=~m; col[j]&=~m; box[k]&=~m; b[i][j]='.';
            }
        } return false;
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
        solveSudoku(board);
        System.out.println(board[0][0]); // just show solved value
    }
}
