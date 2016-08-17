package leftEdges;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TesterB {

	BearKRoads bkr;
	
	@Before
	public void setUp() throws Exception {
		bkr = new BearKRoads();
	}

	@Test
	public void testMaxHappy0() { 	
		int[] x = {10, 50, 50, 10};
		int[] a = {0, 1, 2};
		int[] b = {1, 2, 3};
		int K = 1;
		int res = 100;
		assertEquals("Checking test 0", (Integer)res, (Integer)bkr.maxHappy(x, a, b, K));
	}

	@Test
	public void testMaxHappy1() { 	
		int[] x = {10, 50, 50, 10};
		int[] a = {0, 1, 2};
		int[] b = {1, 2, 3};
		int K = 2;
		int res = 120;
		assertEquals("Checking test 1", (Integer)res, (Integer)bkr.maxHappy(x, a, b, K));
	}

	@Test
	public void testMaxHappy2() { 	
		int[] x = {42, 100, 500};
		int[] a = {0, 1};
		int[] b = {1, 2};
		int K = 2;
		int res = 642;
		assertEquals("Checking test 2", (Integer)res, (Integer)bkr.maxHappy(x, a, b, K));
	}

	@Test
	public void testMaxHappy3() { 	
		int[] x = {42, 100, 500, 999, 999, 999, 999};
		int[] a = {0, 1};
		int[] b = {1, 2};
		int K = 2;
		int res = 642;
		assertEquals("Checking test 3", (Integer)res, (Integer)bkr.maxHappy(x, a, b, K));
	}

	@Test
	public void testMaxHappy4() { 	
		int[] x = {969,467,808,263,179,428,595,557,406,80};
		int[] a = {5,4,9,7,9,3};
		int[] b = {4,0,8,8,0,1};
		int K = 3;
		int res = 2841;
		assertEquals("Checking test 4", (Integer)res, (Integer)bkr.maxHappy(x, a, b, K));
	}

	@Test
	public void testMaxHappy5() { 	
		int[] x = {1,2,3,4};
		int[] a = {0,0,0};
		int[] b = {1, 2, 3};
		int K = 2;
		int res = 8;
		assertEquals("Checking test 5", (Integer)res, (Integer)bkr.maxHappy(x, a, b, K));
	}

	@Test
	public void testMaxHappy6() { 	
		int[] x = {1,2,3,4,2};
		int[] a = {0,0,0,1};
		int[] b = {1,2,3,4};
		int K = 2;
		int res = 9;
		assertEquals("Checking test 6", (Integer)res, (Integer)bkr.maxHappy(x, a, b, K));
	}

	@Test
	public void testMaxHappy7() { 	
		int[] x = {8,18,14,10,7,16,4,19,6,12,17,10,12,3,15,8,15,12};
		int[] a = {0,15,1,5,7,3,17,4,15,3,13,14,4,7};
		int[] b = {8,10,16,13,2,10,2,10,11,13,0,9,3,6};
		int K = 7;
		int res = 173;
		assertEquals("Checking test 7", (Integer)res, (Integer)bkr.maxHappy(x, a, b, K));
	}

}
