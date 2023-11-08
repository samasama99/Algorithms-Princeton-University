public class BetterQuickUnionUF {
    private final int[] id;
    private final int[] sz;

    public BetterQuickUnionUF(int N) {
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    private int root(int i) {
        while (i != id[i]) {
            // Path Compression helps keep the tree almost flat
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    public boolean find(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i == j) return;
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
    }


    public static void main(String[] args) {
        var quickUnionUF = new BetterQuickUnionUF(10);

        quickUnionUF.union(0, 1);
        quickUnionUF.union(0, 8);
        quickUnionUF.union(6, 5);
        quickUnionUF.union(6, 9);
        quickUnionUF.union(2, 7);

        if (!quickUnionUF.find(0, 1)) throw new AssertionError();
        if (!quickUnionUF.find(8, 1)) throw new AssertionError();
        if (!quickUnionUF.find(9, 6)) throw new AssertionError();

        if (quickUnionUF.find(8, 2)) throw new AssertionError();
        if (quickUnionUF.find(0, 9)) throw new AssertionError();

        System.out.println("[TEST] ALL PASSED");
    }
}
