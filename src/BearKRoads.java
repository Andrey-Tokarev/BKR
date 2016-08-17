import java.util.*;

public class BearKRoads {
	static int[] pops = null;  // cities populations
	
	public class Road implements Comparable<Road>{
		int i;
		int j;
		
		Road(int i, int j) {
			this.i = i;
			this.j = j;
		}
		
		public int roadPop() {  // road population (which is sum of the connected cities 
			return BearKRoads.pops[i] + BearKRoads.pops[j];
		}
		
		@Override
		public int compareTo(Road o) {
			return this.roadPop() - o.roadPop();
		}
	}
	
	public int maxHappy(int[] x, int[] a, int[] b, int K) {
		BearKRoads.pops = x;
		ArrayList<Road> roads = new ArrayList<Road>(); 
		int M = a.length;
		for(int l = 0; l < M; ++l) {
			Road road = new Road(a[l], b[l]);
			roads.add(road);
		}
		int mh = 0;  // final answer
		for(int l = 0; l < K; ++l) {
			Collections.sort(roads, Collections.reverseOrder());
			Road topRoad = roads.get(0); 
			mh += topRoad.roadPop();
			pops[topRoad.i] = 0;
			pops[topRoad.j] = 0;
		}
		return mh;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BearKRoads bkr = new BearKRoads();
		int[] x = {10, 50, 50, 10};
		int[] a = {0, 1, 2};
		int[] b = {1, 2, 3};
		System.out.println(bkr.maxHappy(x, a, b, 2));
	}

}
