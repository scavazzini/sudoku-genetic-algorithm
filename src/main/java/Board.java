import java.util.Random;

public class Board {

    private int[][] board;
    private int fitness;

    private final Random random;

    public Board(int[][] board) {
        this.random = new Random();
        setBoard(board);
    }

    public Board(int[][] board, long seed) {
        this.random = new Random(seed);
        setBoard(board);
    }

    public void setBoard(int[][] board) {
        this.board = board;
        this.fillEmptyCells();
        this.fitness = this.doFitness();
    }

    private void fillEmptyCells() {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board.length; j++) {
                if (this.board[i][j] == 0) {
                    this.board[i][j] = this.random.nextInt(9) + 1;
                }
            }
        }
    }

    /**
     * Fitness is increased by one for every valid cell
     * @return board fitness
     */
    public int doFitness() {
        int fitness = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {

                if (this.board[i][j] < 1 || this.board[i][j] > board.length)
                    return 0;

                if (this.isValidCell(i, j))
                    fitness++;

            }
        }

        return fitness;
    }

    private boolean isValidCell(int i, int j) {

        // Evaluate row
        for (int k = 0; k < board.length; k++) {
            if (k != j && this.board[i][j] == this.board[i][k]) {
                return false;
            }
        }

        // Evaluate column
        for (int l = 0; l < board.length; l++) {
            if (l != i && this.board[i][j] == this.board[l][j]) {
                return false;
            }
        }

        // Evaluate 3x3 blocks
        int iBlock = Math.floorDiv(i, 3) * 3;
        int jBlock = Math.floorDiv(j, 3) * 3;
        for (int k = 0; k < 3; k++) {
            for (int l = 0; l < 3; l++) {
                if (i != iBlock+k && j != jBlock+l && this.board[i][j] == this.board[iBlock+k][jBlock+l]) {
                    return false;
                }
            }
        }

        return true;
    }

    public int getFitness() {
        return this.fitness;
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
