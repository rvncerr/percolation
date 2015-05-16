package org.rvncerr;

// Процеживание в многомерных кубах.

public class Main {
    public static final int DIM_MIN = 1;
    public static final int DIM_MAX = 10;
    public static final int EDGE_MIN = 1;
    public static final int EDGE_MAX = 100;
    public static final int ITER_COUNT = 10;
    public static final int CELL_LIMIT = 1000000;
    public static void main(String[] args) {

        System.out.println("dim;neig;edge;cell;mean;var;");
        for(int d = DIM_MIN; d <= DIM_MAX; d++) {
            for(int e = EDGE_MIN; e <= EDGE_MAX; e++) {
                int c = (int)Math.pow(e, d);
                int n = (int)Math.pow(2, d);
                if(c > CELL_LIMIT) continue;

                double[] x = new double[ITER_COUNT];
                double mean = 0;
                for (int i = 0; i < ITER_COUNT; i++) {
                    Percolation p = new Percolation(d, e);
                    while (!p.percolates()) p.open();
                    x[i] = 1. * p.statOpened / p.cellsTotal;
                    mean += x[i];
                }
                mean /= ITER_COUNT;
                double var = 0;
                for (int i = 0; i < ITER_COUNT; i++) {
                    var += Math.pow(x[i] - mean, 2);
                }
                var /= (ITER_COUNT - 1);

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(d).append(';').append(n)
                        .append(';').append(e).append(';')
                        .append(c).append(';').append(mean)
                        .append(';').append(var).append(';');
                System.out.println(stringBuilder);
            }


        }
    }
}
