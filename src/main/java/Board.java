import java.util.Random;

public class Board {
    private static final long SEED = 1834913443;

    private int[][] board;
    private int fitness;

    public Board(int[][] board) {
        setBoard(board);
    }

    public void setBoard(int[][] board) {
        this.board = board;
        this.fillEmptyCells();
        this.fitness = this.doFitness();
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

    public int doFitness() {
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < board.length; i++) {

            if (i % 3 == 0) {
                stringBuilder.append("\n");
            }

            for (int j = 0; j < board.length; j++) {
                if (j % 3 == 0) {
                    stringBuilder.append(" ");
                }

                if (this.board[i][j] == 0) {
                    stringBuilder.append("_ ");
                    continue;
                }
                stringBuilder.append(this.board[i][j]);
                stringBuilder.append(' ');
            }
            stringBuilder.append('\n');
        }

        return stringBuilder.toString();
    }

}
