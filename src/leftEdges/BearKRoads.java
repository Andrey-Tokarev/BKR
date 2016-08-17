package leftEdges;

import java.util.ArrayList;

public class BearKRoads {
	int[] pops;  // cities populations
	ArrayList<ArrayList<Road>> adjL;
	ArrayList<Road> roads;
	
	public class Road {
		int i;
		int j;

		Road(int i, int j) {
			this.i = i;
			this.j = j;
		}

		public int roadPop() { // road population (sum of the connected cities)
			return pops[i] + pops[j];
		}

	}
	
	private Road getMaxRoad() {
		Road maxRoad = roads.get(0);
		for (Road road : roads) {
			if (road.roadPop() > maxRoad.roadPop()) {
				maxRoad = road;
			}
		}
		return maxRoad;
	}

	private int workMaxHappy(int K) {
		// firstly with top
		if (K == 0) {
			return 0;
		}
		Road topRoad = getMaxRoad();
		// checking all roads connected to the left city of the top road
		// (including top itself)
		ArrayList<Road> leftRoads = adjL.get(topRoad.i);
		int res = 0;
		for (Road leftRoad : leftRoads) {
			int roadPop = leftRoad.roadPop();
			if (roadPop != 0) {
				int prevIPop = pops[leftRoad.i];
				int prevJPop = pops[leftRoad.j];
				pops[leftRoad.i] = pops[leftRoad.j] = 0;
				res = Math.max(res, roadPop + workMaxHappy(K - 1));
				pops[leftRoad.i] = prevIPop;
				pops[leftRoad.j] = prevJPop;
			}
		}
		return res;
	}
	
	public int maxHappy(int[] x, int[] a, int[] b, int K) {
		pops = x;
		adjL = new ArrayList<>();
		for(int i = 0; i < x.length; ++i) {
			adjL.add(new ArrayList<Road>());
		}
		roads = new ArrayList<Road>(); 
		int M = a.length;
		for(int l = 0; l < M; ++l) {
			Road road = new Road(a[l], b[l]);
			roads.add(road);
			(adjL.get(a[l])).add(road);
			(adjL.get(b[l])).add(road);
		}
		return workMaxHappy(K);
	}

		
	public static void main(String[] args) {
		long startTime = System.nanoTime();
		BearKRoads bkr = new BearKRoads();
		int[] x = {8,18,14,10,7,16,4,19,6,12,17,10,12,3,15,8,15,12};
		int[] a = {0,15,1,5,7,3,17,4,15,3,13,14,4,7};
		int[] b = {8,10,16,13,2,10,2,10,11,13,0,9,3,6};
		int K = 7;
		System.out.println(bkr.maxHappy(x, a, b, K));
		long endTime = System.nanoTime();
		double estTime = (endTime-startTime) / 1000000000.0;
		System.out.println(estTime);
	}

}
