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
		int[] opponents = {1000, 1100, 900};
		int wins = 1;
		int losses = 1;
		
		this.assertEquals(0, newPlayer.getTotalGames());
		this.assertEquals(1000, newPlayer.calcProvisionalRating(opponents, wins, losses));
		newPlayer.applyResults(opponents, wins, losses);
		this.assertEquals(1, newPlayer.getWin());
		this.assertEquals(1, newPlayer.getLoss());
		this.assertEquals(1, newPlayer.getDraw());
		this.assertEquals(3, newPlayer.getTotalGames());
		this.assertEquals(1000, newPlayer.getRating());
		
		int[] opponents2 = {750, 1500};
		newPlayer.applyResults(opponents2, 1, 0);
		this.assertEquals(1130, newPlayer.getRating());
		this.assertEquals(5, newPlayer.getTotalGames());
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
