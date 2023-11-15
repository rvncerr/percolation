package org.rvncerr.percolation;

public class Main {
    public static void main(String[] args) {
        if(args.length != 3) {
            System.out.println("usage: java -jar Percolation.jar <dim> <edge> <iter_count>");
            return;
        }
        int dim = Integer.parseInt(args[0]);
        int edgeSize = Integer.parseInt(args[1]);
        int iterCount = Integer.parseInt(args[2]);
        int cellCount = (int)Math.pow(edgeSize, dim);
        int neighbourCount = (int)Math.pow(2, dim);

        double[] results = new double[iterCount];
        double mean = 0;
        double var = 0;

        for (int i = 0; i < iterCount; i++) {
            Percolation p = new Percolation(dim, edgeSize);
            while (!p.percolates()) p.open();
            results[i] = 1. * p.statOpened / p.cellsTotal;
            mean += results[i];
        }
        mean /= iterCount;

        for (int i = 0; i < iterCount; i++) {
            var += Math.pow(results[i] - mean, 2);
        }
        var /= (iterCount - 1);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("dimension = ").append(dim)
                .append("\nedge_size = ").append(edgeSize)
                .append("\niter_count = ").append(iterCount)
                .append("\ncell_count = ").append(cellCount)
                .append("\nneighbour_count = ").append(neighbourCount)
                .append("\n\nmean = ").append(mean)
                .append("\nvar = ").append(var);
        System.out.println(stringBuilder);
    }
}
