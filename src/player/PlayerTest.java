/**
 * 
 */
package player;


import junit.framework.TestCase;

import org.junit.*;

/**
 * @author maddox
 *
 */
public class PlayerTest extends TestCase{

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
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

}
