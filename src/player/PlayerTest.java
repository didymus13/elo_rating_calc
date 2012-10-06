/**
 * 
 */
package player;


import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.*;

/**
 * @author maddox
 *
 */
public class PlayerTest extends TestCase{

	Player player = new Player("Mr Test");
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		int[] opponents = new int[26];
		int i;
		for (i=0; i <= 25; i++) {
			opponents[i] = 1000;
		}
		
		this.player.applyResults(opponents, 13, 13);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testProvisionalRating() {
		Player newPlayer = new Player("Mr Test");
		this.assertEquals(0, newPlayer.getTotalGames());
		
		int[] opponents = {1000,};
		
		int wins = 1; // Check Win
		int losses = 0;
		this.assertEquals(1400, newPlayer.calcProvisionalRating(opponents, wins, losses));
		
		wins = 0; // Check Losses  
		losses = 1;
		this.assertEquals(600, newPlayer.calcProvisionalRating(opponents, wins, losses));
		
		wins = 0; // Check Draw  
		losses = 0;
		this.assertEquals(1000, newPlayer.calcProvisionalRating(opponents, wins, losses));
		
		int[] opponents2 = {750, 1500}; // check multiple
		wins = 1;
		losses = 0;
		this.assertEquals(1325, newPlayer.calcProvisionalRating(opponents2, wins, losses));
		
		// Check update
		newPlayer.applyResults(opponents2, wins, losses);
		this.assertEquals(1325, newPlayer.getRating());
		this.assertEquals(2, newPlayer.getTotalGames());
	}
	
	@Test
	public void testCalcEffectiveGames() {
		this.assertEquals(12.741179785940638, this.player.calcEffectiveGames());	
	}
	
	@Test
	public void testCalcK() {
		this.assertEquals(58.21916403557461, this.player.calcK(this.player.calcEffectiveGames(), 1));
	}
	
	@Test
	public void testCalcBonus() {
		double s = 3.5;
		double e = 1.363;
		double k = 38.89;
		int m = 4;
		this.assertEquals(63.107929999999996, this.player.calcBonus(m, k, s, e));
	}
	
	@Test
	public void testWinExpectancy() {
		int[] opponents = {1000,};
		this.assertEquals(0.5, this.player.calcWinExpectancy(opponents));
		int[] opponents2 = {1500,};
		this.assertEquals(0.05324021520202245, this.player.calcWinExpectancy(opponents2));
		int[] opponents3 = {0,};
		this.assertEquals(0.9968476908167399, this.player.calcWinExpectancy(opponents3));
	}
	
	@Test
	public void testEstablishedRating() {
		this.assertTrue(this.player.isEstablished());
		this.assertEquals(26, this.player.getTotalGames());
		this.assertEquals(1000, this.player.getRating());
		int[] opponents = {1000,};
		// post-Win Rating
		int win = 1;
		int loss = 0;
		this.assertEquals(1029, this.player.calcEffectiveRating(opponents, win, loss));

		// post-loss Rating
		win = 0;
		loss = 1;
		this.assertEquals(971, this.player.calcEffectiveRating(opponents, win, loss));
		
		// post-draw Rating
		win = 0;
		loss = 0;
		this.assertEquals(1000, this.player.calcEffectiveRating(opponents, win, loss));
		
		this.player.applyResults(opponents, win, loss);
		this.assertEquals(27, this.player.getTotalGames());
		this.assertEquals(1000, this.player.getRating());
	}
	
	@Test
	public void testRatingFloor() {
		Player newPlayer = new Player(0,0,0,0);
		int[] r = {0};
		int w = 0;
		int l = 1;
		this.assertEquals(100, newPlayer.calcProvisionalRating(r, w, l));
	}

}
