public class Main {

    public static void main(String[] args) {

        int[][] initialBoard = {
            {0, 9, 4, 0, 8, 5, 6, 1, 0},
            {6, 0, 0, 0, 0, 2, 4, 0, 5},
            {2, 5, 0, 0, 0, 9, 0, 0, 8},
            {1, 4, 9, 0, 0, 0, 0, 0, 0},
            {5, 0, 0, 0, 0, 0, 0, 0, 6},
            {0, 0, 0, 0, 0, 0, 9, 2, 1},
            {9, 0, 0, 1, 0, 0, 0, 8, 3},
            {8, 0, 3, 5, 0, 0, 0, 0, 4},
            {0, 2, 7, 8, 3, 0, 1, 5, 0},
        };

        new Generation(initialBoard, 300)
                .setMutationRate(.03)
                .setTournamentRate(.08)
                .setCrossoverRate(.6)
                .setSeed(666)
                .run(10_000);
    }

}
