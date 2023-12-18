public class Percolation {

    // n is the size of the graph n x n
    private final int n;

    // the open sites
    private int openSites = 0;

    // my data structure to use in QuickFind
    private final int[][] graph;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        this.n = n;
        this.graph = new int[n][n];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                this.graph[row][col] = -1;
            }
        }
    }

    // connect two nodes
    private void connect(int row1, int col1, int row2, int col2) {
        int id1 = graph[row1][col1];
        int id2 = graph[row2][col2];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (graph[r][c] == id2)
                    graph[r][c] = id1;
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {

        int correctedRow = row - 1;
        int correctedCol = col - 1;

        if (correctedRow < 0 || correctedRow >= n)
            throw new IllegalArgumentException();
        if (correctedCol < 0 || correctedCol >= n)
            throw new IllegalArgumentException();

        if (isOpen(row, col)) return;

        this.openSites++;
        graph[correctedRow][correctedCol] = (correctedRow * n) + correctedCol;

        if (col > 1 && isOpen(row, col - 1)) {
            connect(correctedRow, correctedCol, correctedRow, correctedCol - 1);
        }
        if (row > 1 && isOpen(row - 1, col)) {
            connect(correctedRow, correctedCol, correctedRow - 1, correctedCol);
        }
        if (col < n && isOpen(row, col + 1)) {
            connect(correctedRow, correctedCol, correctedRow, correctedCol + 1);
        }
        if (row < n && isOpen(row + 1, col)) {
            connect(correctedRow, correctedCol, correctedRow + 1, correctedCol);
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > n) throw new IllegalArgumentException();
        if (col < 1 || col > n) throw new IllegalArgumentException();

        int correctedRow = row - 1;
        int correctedCol = col - 1;

        return graph[correctedRow][correctedCol] != -1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || row > n) throw new IllegalArgumentException();
        if (col < 1 || col > n) throw new IllegalArgumentException();

        if (!isOpen(row, col)) return false;

        int correctedRow = row - 1;
        int correctedCol = col - 1;

        for (int i = 0; i < n; i++) {
            if (graph[correctedRow][correctedCol] == graph[0][i]) return true;
        }

        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        for (int down = 1; down <= n; down++) {
            if (isFull(n, down))
                return true;
        }
        return false;
    }

    // test client (optional)
    public static void main(String[] args) {

        final int N = 5;
        Percolation percolation = new Percolation(N);

        if (percolation.numberOfOpenSites() != 0) throw new AssertionError();
        if (percolation.percolates()) throw new AssertionError();

        percolation.open(1, 1);
        if (percolation.numberOfOpenSites() != 1) throw new AssertionError();
        if (!percolation.isOpen(1, 1)) throw new AssertionError();
        if (!percolation.isFull(1, 1)) throw new AssertionError();
        if (percolation.percolates()) throw new AssertionError();

        percolation.open(2, 2);
        if (percolation.numberOfOpenSites() != 2) throw new AssertionError();
        if (!percolation.isOpen(2, 2)) throw new AssertionError();
        if (percolation.isFull(2, 2)) throw new AssertionError();
        if (percolation.percolates()) throw new AssertionError();


        percolation.open(2, 1);
        if (percolation.numberOfOpenSites() != 3) throw new AssertionError();
        if (!percolation.isOpen(2, 1)) throw new AssertionError();
        if (!percolation.isFull(2, 1)) throw new AssertionError();
        if (percolation.percolates()) throw new AssertionError();

        if (!percolation.isFull(2, 2)) throw new AssertionError();

        percolation.open(5, 3);
        if (percolation.numberOfOpenSites() != 4) throw new AssertionError();
        if (!percolation.isOpen(5, 3)) throw new AssertionError();
        if (!percolation.isFull(2, 1)) throw new AssertionError();
        if (percolation.percolates()) throw new AssertionError();

        percolation.open(4, 2);
        if (percolation.numberOfOpenSites() != 5) throw new AssertionError();
        if (!percolation.isOpen(4, 2)) throw new AssertionError();
        if (percolation.isFull(4, 2)) throw new AssertionError();
        if (percolation.percolates()) throw new AssertionError();

        percolation.open(4, 3);
        if (percolation.numberOfOpenSites() != 6) throw new AssertionError();
        if (!percolation.isOpen(4, 3)) throw new AssertionError();
        if (percolation.isFull(4, 3)) throw new AssertionError();
        if (percolation.percolates()) throw new AssertionError();

        percolation.open(3, 2);
        if (percolation.numberOfOpenSites() != 7) throw new AssertionError();
        if (!percolation.isOpen(3, 2)) throw new AssertionError();
        if (!percolation.isFull(3, 2)) throw new AssertionError();
        if (!percolation.percolates()) throw new AssertionError();

        System.out.println("TESTS PASSED");
    }
}






























