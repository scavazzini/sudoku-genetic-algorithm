import java.util.Arrays;
import java.util.Random;

public class Board {

    private final int[][] fixedCells;
    private int[][] board;
    private int fitness;

    private final Random random;

    public Board(int[][] board, Random random) {
        this.random = random;
        this.fixedCells = Arrays.stream(board).map(int[]::clone).toArray(int[][]::new);
        setBoard(board);
    }

    public void setBoard(int[][] board) {
        this.board = Arrays.stream(board).map(int[]::clone).toArray(int[][]::new);
        this.fillEmptyCells();
        this.doFitness();
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

        this.fitness = fitness;
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

    public void doMutation(double mutationRate) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {

                if (this.fixedCells[i][j] != 0)
                    continue;

                if (this.random.nextDouble() < mutationRate) {

                    if (this.random.nextDouble() < .5) {
                        if (this.board[i][j] == 9)
                            this.board[i][j] = 1;
                        else
                            this.board[i][j]++;
                    }
                    else {
                        if (this.board[i][j] == 1)
                            this.board[i][j] = 9;
                        else
                            this.board[i][j]--;
                    }

                }

            }
        }
        this.doFitness();

    }

    public int getFitness() {
        return this.fitness;
    }

    @Override
    public String toString() {
        return this.stringify(this.board);
    }

    public int[][] getGrid() {
        return this.board;
    }

    public String fixedBoardToString() {
        return this.stringify(this.fixedCells);
    }

    private String stringify(int[][] board) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < board.length; i++) {

            if (i % 3 == 0) {
                stringBuilder.append("\n");
            }

            for (int j = 0; j < board.length; j++) {
                if (j % 3 == 0) {
                    stringBuilder.append(" ");
                }

                if (board[i][j] == 0) {
                    stringBuilder.append("_ ");
                    continue;
                }
                stringBuilder.append(board[i][j]);
                stringBuilder.append(' ');
            }
            stringBuilder.append('\n');
        }

        return stringBuilder.toString();
    }
}
