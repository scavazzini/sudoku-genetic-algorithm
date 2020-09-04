public class Generation {

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
}
