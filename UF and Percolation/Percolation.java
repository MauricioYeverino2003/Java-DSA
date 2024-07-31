/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */


import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF uf;
    private boolean[][] grid;
    private int size;
    private int topVirtual;
    private int bottomVirtual;
    private int openSites = 0;

    public Percolation(int n) {
        if (n < 1) throw new IllegalArgumentException();
        size = n;
        uf = new WeightedQuickUnionUF((n * n) + 2);
        grid = new boolean[n][n];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                grid[r][c] = false;
            }
        }
        topVirtual = 0;
        bottomVirtual = (n * n) + 1;
    }

    private boolean outOfRange(int row, int col) {
        if (row > size || row <= 0) {
            return true;
        }
        if (col > size || col <= 0) {
            return true;
        }
        return false;
    }

    private int flattenArray(int row, int col) {
        int i = row - 1;
        int j = col - 1;
        return (i * size + j + 1);
    }

    public void open(int row, int col) {
        if (outOfRange(row, col)) {
            throw new IllegalArgumentException();
        }
        if (isOpen(row, col)) return;

        int flattenValue = flattenArray(row, col);

        int r = row - 1;
        int c = col - 1;

        grid[r][c] = true;
        ++openSites;

        // if you open from top row
        if (row == 1) {
            uf.union(topVirtual, flattenValue);
        }
        // if you open from bottom row
        if (row == size) {
            uf.union(bottomVirtual, flattenValue);
        }
        // necessary connections if possible and needed
        if (!outOfRange(row + 1, col) && isOpen(row + 1, col)) {
            uf.union(flattenArray(row + 1, col), flattenValue);
        }
        if (!outOfRange(row - 1, col) && isOpen(row - 1, col)) {
            uf.union(flattenArray(row - 1, col), flattenValue);
        }
        if (!outOfRange(row, col + 1) && isOpen(row, col + 1)) {
            uf.union(flattenArray(row, col + 1), flattenValue);
        }
        if (!outOfRange(row, col - 1) && isOpen(row, col - 1)) {
            uf.union(flattenArray(row, col - 1), flattenValue);
        }
    }

    public boolean isOpen(int row, int col) {
        if (outOfRange(row, col)) {
            throw new IllegalArgumentException();
        }
        int r = row - 1;
        int c = col - 1;
        return grid[r][c];
    }

    public boolean isFull(int row, int col) {
        if (outOfRange(row, col)) {
            throw new IllegalArgumentException();
        }
        return (uf.find(topVirtual) == uf.find(flattenArray(row, col)));
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        return (uf.find(topVirtual) == uf.find(bottomVirtual));
    }

    public static void main(String[] args) {
        Percolation system = new Percolation(5);
        system.open(1, 1);
        system.open(1, 2);
        system.open(1, 3);
        system.open(1, 4);
        system.open(1, 5);
        system.open(2, 5);
        system.open(3, 5);
        system.open(4, 5);
        system.open(5, 5);
        System.out.print(system.percolates());
    }
}
