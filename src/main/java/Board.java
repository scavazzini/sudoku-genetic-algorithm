import java.util.Random;

public class Board {
    private static final long SEED = 1834913443;

    private int[][] board;

    public Board(int[][] board) {
        setBoard(board);
    }

    public void setBoard(int[][] board) {
        this.board = board;
        fillEmptyCells();
    }

    private void fillEmptyCells() {
        Random random = new Random(SEED);
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board.length; j++) {
                if (this.board[i][j] == 0) {
                    this.board[i][j] = random.nextInt(9) + 1;
                }
            }
        }
    }

}
