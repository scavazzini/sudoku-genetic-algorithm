public class Population {

    private Board[] boards;
    private double avgFitness;

    public Population(int[][] initialBoard, int populationSize) {
        this.boards = new Board[populationSize];

        for (int i = 0; i < populationSize; i++) {
            this.boards[i] = new Board(initialBoard);
        }

        this.calculateAvgFitness();
    }

    private void calculateAvgFitness() {
        for (Board board : this.boards) {
            this.avgFitness += board.getFitness();
        }
        this.avgFitness = this.avgFitness / this.boards.length;
    }

    public Board[] getBoards() {
        return boards;
    }

    @Override
    public String toString() {
        return "Average fitness: " + this.avgFitness;
    }
}
