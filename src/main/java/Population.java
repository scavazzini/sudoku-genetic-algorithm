import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.Collectors;

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

    public Board[] getBest(int quantity) {
        return Arrays.stream(this.boards)
                .sorted(Comparator.comparing(Board::getFitness).reversed())
                .limit(quantity)
                .collect(Collectors.toList())
                .toArray(new Board[quantity]);
    }

    public void setBoard(int index, Board board) {
         this.boards[index].setBoard(board.getGrid());
    }

    public void setPopulation(Population nextPopulation) {
        for(int i = 0; i < this.boards.length; i++){
            this.boards[i].setBoard(nextPopulation.getBoards()[i].getGrid());
        }
        this.calculateAvgFitness();
    }

    public Board[] getBoards() {
        return boards;
    }

    @Override
    public String toString() {
        return String.format("Average fitness: %f. Best: %d/1944", this.avgFitness, this.getBest(1)[0].getFitness());
    }
}
