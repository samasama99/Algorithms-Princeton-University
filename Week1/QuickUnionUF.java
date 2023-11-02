public class QuickUnionUF {
    private int[] id;

	public QuickUnionUF(int N) {
		id = new int[N];
		for (int i = 0;i < N;i++) {
			id[i] = i;
		}
	}

    private int root(int i) {
        // id[i]
        while (i != id[i])
            i = id[i];
        return i;
    }

	public boolean find(int p, int q) {
		return root(p) == root(q);
	}

	public void union(int p, int q) {
		int i = root(p);
		int j = root(q);
        id[i] = j;
	}


	public static void main(String[] args) {
		var quickUnionUF = new QuickUnionUF(10);

		quickUnionUF.union(0, 1);
		quickUnionUF.union(0, 8);
		quickUnionUF.union(6, 5);
		quickUnionUF.union(6, 9);
		quickUnionUF.union(2, 7);

		assert quickUnionUF.find(0, 1) == true;
		assert quickUnionUF.find(8, 1) == true;
		assert quickUnionUF.find(9, 6) == true;

		assert quickUnionUF.find(8, 2) == false;
		assert quickUnionUF.find(0, 9) == false;

		System.out.println("[TEST] ALL PASSED");
	}
}
