public class QuickFindUF {
    private final int[] id;

    public QuickFindUF(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];

        for (int i = 0; i < id.length; i++) {
            // NOTE id[p] will change that's why we use pid instead of id[p]
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
    }

    public static void main(String[] args) {
        var quickFindUF = new QuickFindUF(10);

        quickFindUF.union(0, 1);
        quickFindUF.union(0, 8);
        quickFindUF.union(6, 5);
        quickFindUF.union(6, 9);
        quickFindUF.union(2, 7);

        if (!quickFindUF.connected(0, 1)) throw new AssertionError();
        if (!quickFindUF.connected(8, 1)) throw new AssertionError();
        if (!quickFindUF.connected(9, 6)) throw new AssertionError();

        if (quickFindUF.connected(8, 2)) throw new AssertionError();
        if (quickFindUF.connected(0, 9)) throw new AssertionError();

        System.out.println("[TEST] ALL PASSED");
    }
}
