import java.util.Random;

public class Generation {

    private Population currentPopulation;
    private Population nextPopulation;

    private final Random random;
    private final int[][] initialBoard;
    private final int populationSize;
    private double mutationRate = .03;
    private double tournamentRate = .06;
    private double crossoverRate = .6;

    public Generation(int[][] initialBoard, int populationSize, Random random) {
        this.initialBoard = initialBoard;
        this.populationSize = populationSize;
        this.random = random;
    }

    public void run() {

        Board[] bestBoards = null;

        this.currentPopulation = new Population(this.initialBoard, this.populationSize, this.random);
        for (int gen = 0; gen < 10_000; gen++) {

            System.out.println("Generation " + gen + ": " + this.currentPopulation);
            bestBoards = this.currentPopulation.getBest(2);

            this.nextPopulation = new Population(this.initialBoard, this.populationSize, this.random);

            if (bestBoards[0].getFitness() == 81) {
                break;
            }

            this.nextPopulation.setBoard(0, bestBoards[0]);
            this.nextPopulation.setBoard(1, bestBoards[1]);

            for (int i = 2; i < this.populationSize; i += 2) {

                Board parent1 = currentPopulation.tournamentSelection(this.tournamentRate);
                Board parent2 = currentPopulation.tournamentSelection(this.tournamentRate);

                if (this.random.nextDouble() < crossoverRate){
                    crossover(parent1, parent2);
                }
                parent1.doMutation(mutationRate);
                parent2.doMutation(mutationRate);

                this.nextPopulation.setBoard(i, parent1);
                this.nextPopulation.setBoard(i + 1, parent2);
            }

            this.currentPopulation.setPopulation(this.nextPopulation);
        }

        System.out.printf("\nResult (%d/81):%n", bestBoards[0].getFitness());
        System.out.println(bestBoards[0]);
    }

    /**
     * Example of what this crossover does:
     *
     * 1 1 1 1 1 1 1 1 1     2 2 2 2 2 2 2 2 2     1 1 1 1 1 1 1 1 1       2 2 2 2 2 2 2 2 2
     * 1 1 1 1 1 1 1 1 1     2 2 2 2 2 2 2 2 2     2 2 2 2 2 2 2 2 2       1 1 1 1 1 1 1 1 1
     * 1 1 1 1 1 1 1 1 1     2 2 2 2 2 2 2 2 2     1 1 1 1 1 1 1 1 1       2 2 2 2 2 2 2 2 2
     * 1 1 1 1 1 1 1 1 1     2 2 2 2 2 2 2 2 2     2 2 2 2 2 2 2 2 2       1 1 1 1 1 1 1 1 1
     * 1 1 1 1 1 1 1 1 1  +  2 2 2 2 2 2 2 2 2  =  1 1 1 1 1 1 1 1 1  and  2 2 2 2 2 2 2 2 2
     * 1 1 1 1 1 1 1 1 1     2 2 2 2 2 2 2 2 2     2 2 2 2 2 2 2 2 2       1 1 1 1 1 1 1 1 1
     * 1 1 1 1 1 1 1 1 1     2 2 2 2 2 2 2 2 2     1 1 1 1 1 1 1 1 1       2 2 2 2 2 2 2 2 2
     * 1 1 1 1 1 1 1 1 1     2 2 2 2 2 2 2 2 2     2 2 2 2 2 2 2 2 2       1 1 1 1 1 1 1 1 1
     * 1 1 1 1 1 1 1 1 1     2 2 2 2 2 2 2 2 2     1 1 1 1 1 1 1 1 1       2 2 2 2 2 2 2 2 2
     *
     *
     * @param parent1 First parent
     * @param parent2 Second parent
     */
    public void crossover(Board parent1, Board parent2) {

        int[][][] parents = {parent1.getGrid(), parent2.getGrid()};
        int[][][] children = {new int[9][9], new int[9][9]};

        for (int p = 0; p < 2; p++) {
            for (int i = 0; i < parent1.getGrid().length; i++) {
                for (int j = 0; j < parent1.getGrid().length; j++) {
                    children[((i+p) % 2)][i][j] = parents[p][i][j];
                }
            }
        }

        parent1.setBoard(children[0]);
        parent2.setBoard(children[1]);
    }

    public void setMutationRate(double mutationRate) {
        this.mutationRate = mutationRate;
    }

    public void setTournamentRate(double tournamentRate) {
        this.tournamentRate = tournamentRate;
    }

    public void setCrossoverRate(double crossoverRate) {
        this.crossoverRate = crossoverRate;
    }
}
