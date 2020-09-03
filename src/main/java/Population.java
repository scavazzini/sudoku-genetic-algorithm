import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Population {

    private Board[] boards;
    private double avgFitness;

    private final Random random;

    public Population(int[][] initialBoard, int populationSize, Random random) {
        this.boards = new Board[populationSize];
        this.random = random;

        for (int i = 0; i < populationSize; i++) {
            this.boards[i] = new Board(initialBoard, random);
        }

        this.calculateAvgFitness();
    }

    private void calculateAvgFitness() {
        for (Board board : this.boards) {
            this.avgFitness += board.getFitness();
        }
        this.avgFitness = this.avgFitness / this.boards.length;
    }

    public Board tournamentSelection(double rate) {

        int competitors = (int) (rate * this.boards.length);
        Board winner = null;

        for (int i = 0; i < competitors; i++) {
            Board competitor = this.boards[this.random.nextInt(this.boards.length)];

            if (winner == null || competitor.getFitness() > winner.getFitness()) {
                winner = competitor;
            }
        }

        return winner;
    }

    public Board getBest() {
        return Arrays.stream(this.boards)
                .max(Comparator.comparing(Board::getFitness))
                .orElse(null);
    }

    public Board[] getBoards() {
        return boards;
    }

    @Override
    public String toString() {
        return String.format("Average fitness: %f. Best: %d", this.avgFitness, this.getBest().getFitness());
    }
}
