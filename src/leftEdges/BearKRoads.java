package leftEdges;

import java.util.ArrayList;
import java.util.Collections;

public class BearKRoads {
	int[] pops;  // cities populations
	ArrayList<ArrayList<Road>> adjL;
	
	public class Road implements Comparable<Road>{
		int i;
		int j;
		
		Road(int i, int j) {
			this.i = i;
			this.j = j;
		}
		
		Road(Road road) {
			this.i = road.i;
			this.j = road.j;
		}
		
		public int roadPop() {  // road population (which is sum of the connected cities 
			return pops[i] + pops[j];
		}
		
		@Override
		public int compareTo(Road o) {
			return this.roadPop() - o.roadPop();
		}
	   
		@Override
	    public boolean equals(Object object)
	    {
	        boolean sameSame = false;

	        if (object != null && object instanceof Road)
	        {
	            sameSame = (this.i == ((Road) object).i && this.j == ((Road) object).j);
	        }

	        return sameSame;
	    }	}
	
	private ArrayList<Road> cloneRoads(ArrayList<Road> roads) {
		ArrayList<Road> cloneRoads = new ArrayList<Road>(roads.size());
		for(Road road: roads) {
			cloneRoads.add(new Road(road));
		}
		return cloneRoads;
	}

	private Road getMaxRoad(ArrayList<Road> roads) {
		Road maxRoad = roads.get(0);
		for(Road road: roads) {
			if(road.roadPop() > maxRoad.roadPop()) {
				maxRoad = road;
			}
		}
		return maxRoad;
	}
	
	private int workMaxHappy(ArrayList<Road> roads, int K) {
		// firstly with top
		if(roads.isEmpty() || K == 0) {
			return 0;
		}
		Road topRoad = getMaxRoad(roads);
		// checking all roads connected to the left city of the top road (including top itself)
		int roadPop = 0;
		ArrayList<Road> leftRoads = adjL.get(topRoad.i);
		ArrayList<Integer> leftRes = new ArrayList<>();
		leftRes.add(0);
		for(Road leftRoad: leftRoads) {
			if(roads.contains(leftRoad)) {
				ArrayList<Road> copyRoads = cloneRoads(roads);
				copyRoads.remove(leftRoad);
				int prevIPop = pops[leftRoad.i];
				int prevJPop = pops[leftRoad.j];
				roadPop = leftRoad.roadPop();
				pops[leftRoad.i] = pops[leftRoad.j] = 0;
				leftRes.add(roadPop + workMaxHappy(copyRoads, K - 1));
				pops[leftRoad.i] = prevIPop;
				pops[leftRoad.j] = prevJPop;
			}
		}
		return Collections.max(leftRes);
	}
	
	public int maxHappy(int[] x, int[] a, int[] b, int K) {
		pops = x;
		adjL = new ArrayList<>();
		for(int i = 0; i < x.length; ++i) {
			adjL.add(new ArrayList<Road>());
		}
		ArrayList<Road> roads = new ArrayList<Road>(); 
		int M = a.length;
		for(int l = 0; l < M; ++l) {
			Road road = new Road(a[l], b[l]);
			roads.add(road);
			(adjL.get(a[l])).add(road);
			(adjL.get(b[l])).add(road);
		}
		return workMaxHappy(roads, K);
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
