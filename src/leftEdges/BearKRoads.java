package leftEdges;

import java.util.ArrayList;

public class BearKRoads {
	int[] pops;  // cities populations
	int[] c1; // city 1 of the roads
	int[] c2; // city 2 of the roads
	ArrayList<ArrayList<Integer>> adjL;
//	ArrayList<Road> roads;
	
//	public class Road {
//		int i;
//		int j;
//
//		Road(int i, int j) {
//			this.i = i;
//			this.j = j;
//		}
//
//		public int roadPop() { // road population (sum of the connected cities)
//			return pops[i] + pops[j];
//		}
//
//	}
//	
//	private Road getMaxRoad() {
//		Road maxRoad = roads.get(0);
//		for (Road road : roads) {
//			if (road.roadPop() > maxRoad.roadPop()) {
//				maxRoad = road;
//			}
//		}
//		return maxRoad;
//	}
//

	private int getMaxRoadInd() {
		int maxRoadInd = 0;
		int maxRoadPop = 0;
		for(int i = 0; i < c1.length; ++i) {
			int roadPop = pops[c1[i]] + pops[c2[i]]; 
			if (roadPop > maxRoadPop) {
				maxRoadInd = i;
				maxRoadPop = roadPop;
			}
		}
		return maxRoadInd;
	}

	private int workMaxHappy(int K) {
		// firstly with top
		if (K == 0) {
			return 0;
		}
		int topRoadInd = getMaxRoadInd();
		// checking all roads connected to the left city of the top road
		// (including top itself)
		ArrayList<Integer> leftRoads = adjL.get(c1[topRoadInd]);
		int res = 0;
		for (int leftRoad : leftRoads) {
			int roadPop = pops[c1[leftRoad]] + pops[c2[leftRoad]] ;
			if (roadPop != 0) {
				int prevIPop = pops[c1[leftRoad]];
				int prevJPop = pops[c2[leftRoad]];
				pops[c1[leftRoad]] = pops[c2[leftRoad]] = 0;
				res = Math.max(res, roadPop + workMaxHappy(K - 1));
				pops[c1[leftRoad]] = prevIPop;
				pops[c2[leftRoad]] = prevJPop;
			}
		}
		return res;
	}
	
	public int maxHappy(int[] x, int[] a, int[] b, int K) {
		pops = x;
		c1 = a;
		c2 = b;
		adjL = new ArrayList<>();
		for(int i = 0; i < x.length; ++i) {
			adjL.add(new ArrayList<Integer>());
		}
////		roads = new ArrayList<Road>(); 
//		int M = a.length;
		for(int l = 0; l < c1.length; ++l) {
//			Road road = new Road(a[l], b[l]);
//			roads.add(road);
			(adjL.get(c1[l])).add(c2[l]);
			(adjL.get(c2[l])).add(c1[l]);
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
