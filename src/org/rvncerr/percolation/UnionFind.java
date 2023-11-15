package org.rvncerr.percolation;

public class UnionFind {
    private int[] root;
    private int[] rank;

    UnionFind(int size) {
        root = new int[size];
        rank = new int[size];
        for(int i = 0; i < size; i++) {
            root[i] = i;
        }
    }

    void union(int p, int q) {
        p = find(p);
        q = find(q);
        if(p == q) return;
        if(rank[p] <= rank[q]) {
            root[p] = q;
            if(rank[p] == rank[q]) rank[q]++;
        } else {
            root[q] = p;
        }
    }

    int find(int p) {
        while(p != root[p]) {
            root[p] = root[root[p]];
            p = root[p];
        }
        return p;
    }

    boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[ ");
        for(int i = 0; i < root.length; i++) stringBuilder.append(i).append(":(").append(find(i)).append(",").append(rank[find(i)]).append(") ");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
