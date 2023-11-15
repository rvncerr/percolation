package org.rvncerr.percolation;

import java.util.LinkedList;
import java.util.Random;

public class Percolation {
    private int dim;
    private int edge;

    public int cellsTotal;
    private int cellsOnLevel;
    private int topIndex;
    private int bottomIndex;

    private UnionFind unionFind;
    private boolean[] cellsState;

    public int statOpened;

    private Random random;

    private void linkTopAndBottom() {
        for(int i = 0; i < cellsOnLevel; i++) unionFind.union(i, topIndex);
        for(int i = cellsTotal - cellsOnLevel; i < cellsTotal; i++) unionFind.union(i, bottomIndex);
    }

    private LinkedList<Integer> findNeighbours(int cellNo) {
        // Высчитывание пространственных координат.
        int[] coordinates = new int[dim];
        for(int i = 0; i < dim; i++) {
            coordinates[i] = cellNo % edge;
            cellNo = cellNo / edge;
        }

        // Нахождение соседей.
        LinkedList<Integer> neighbours = new LinkedList<Integer>();
        for(int i = 0; i < dim; i++) {
            if(coordinates[i] < edge-1) {
                coordinates[i]++;
                int neighbourNo = 0;
                for (int j = 0; j < dim; j++) neighbourNo += coordinates[j] * Math.pow(edge, j);
                neighbours.push(neighbourNo);
                coordinates[i]--;
            }
            if(coordinates[i] > 0) {
                coordinates[i]--;
                int neighbourNo = 0;
                for (int j = 0; j < dim; j++) neighbourNo += coordinates[j] * Math.pow(edge, j);
                neighbours.push(neighbourNo);
                coordinates[i]++;
            }
        }
        return neighbours;
    }

    public void open(int cellNo) {
        cellsState[cellNo] = true;
        statOpened++;
        for(Integer neighbourNo : findNeighbours(cellNo)) {
            if(cellsState[neighbourNo]) {
                unionFind.union(cellNo, neighbourNo);
            }
        }
    }

    public void open() {
        while(true) {
            int cellNo = random.nextInt(cellsTotal);
            if(!cellsState[cellNo]) {
                open(cellNo);
                break;
            }
        }
    }

    public boolean percolates() {
        return unionFind.isConnected(topIndex, bottomIndex);
    }

    Percolation(int dim, int edge) {
        this.dim = dim;
        this.edge = edge;

        // Количество ячеек всего и на одном уровне.
        cellsTotal = (int)Math.pow(edge, dim);
        cellsOnLevel = (int)Math.pow(edge, dim - 1);

        // Индексы верхней и нижней объединенной грани.
        topIndex = cellsTotal;
        bottomIndex = cellsTotal + 1;

        // Основные структуры - массив состояний ячеек и UnionFind.
        cellsState = new boolean[cellsTotal];
        unionFind = new UnionFind(cellsTotal + 2);
        linkTopAndBottom();

        // Рандомизатор.
        random = new Random();
    }
}

