package inet;

import java.util.ArrayList;

public class BearKRoads {
  private int N;
  ArrayList<Integer>[] graph;
  int[] a, b;

  int dfs(int[] x, int k) {
    if (k == 0) return 0;
    ArrayList<int[]> ab = new ArrayList<>();
    int max = 0;
    for (int i = 0; i < a.length; i++) {
      int h = x[a[i]] + x[b[i]];
      if (h == 0) continue;
      if (max > h) continue;
      if (max < h) {
        ab.clear();
        max = h;
      }
      ab.add(new int[]{a[i], b[i]});
    }

    int ret = 0;
    for (int[] e : ab) {
      int p = e[0];
      if (x[e[0]] < x[e[1]]) p = e[1];
      for (int u : graph[p]) {
        int t1 = x[p];
        int t2 = x[u];
        int t = t1 + t2;
        x[p] = 0;
        x[u] = 0;
        t += dfs(x, k - 1);
        x[p] = t1;
        x[u] = t2;
        ret = Math.max(ret, t);
      }
    }
    return ret;
  }

  public int maxHappy(int[] x, int[] a, int[] b, int K) {
    N = x.length;
    this.a = a;
    this.b = b;
    graph = new ArrayList[N];
    for (int i = 0; i < N; i++) graph[i] = new ArrayList<>();
    for (int i = 0; i < a.length; i++) {
      graph[a[i]].add(b[i]);
      graph[b[i]].add(a[i]);
    }
    return dfs(x, K);
  }

	public static void main(String[] args) {
	BearKRoads bkr = new BearKRoads();
	int[] x = {8,18,14,10,7,16,4,19,6,12,17,10,12,3,15,8,15,12};
	int[] a = {0,15,1,5,7,3,17,4,15,3,13,14,4,7};
	int[] b = {8,10,16,13,2,10,2,10,11,13,0,9,3,6};
	int K = 7;
	System.out.println(bkr.maxHappy(x, a, b, K));
}

}