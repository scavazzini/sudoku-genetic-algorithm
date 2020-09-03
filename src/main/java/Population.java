public class Population {

    private Board[] boards;

    public Population(int[][] initialBoard, int populationSize) {
        this.boards = new Board[populationSize];

        for (int i = 0; i < populationSize; i++) {
            this.boards[i] = new Board(initialBoard);
        }
    }

}
