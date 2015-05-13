package org.rvncerr;

// Процеживание в многомерных кубах.

public class Main {
    public static void main(String[] args) {
	    UnionFind uf = new UnionFind(10);
        System.out.println(uf);
        uf.union(0, 1);
        System.out.println(uf);
        uf.union(2, 3);
        System.out.println(uf);
        uf.union(0, 2);
        System.out.println(uf);
    }
}
