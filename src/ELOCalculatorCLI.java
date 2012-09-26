import player.Player;

/**
 * 
 */

/**
 * @author maddox
 *
 */
public class ELOCalculatorCLI {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] ratings = null;
		int win = 0;
		int loss = 0;
		int[] playerStats = null;
		
		// process args
		for (String arg: args) {
			if (arg.toLowerCase().startsWith("-w"))
				win = splitIntegerArg(arg);
			if (arg.startsWith("-l"))
				loss = splitIntegerArg(arg);
			if (arg.startsWith("-r"))
				ratings = splitIntegerArrayArg(arg);
			if (arg.startsWith("-p")) {
				playerStats = splitIntegerArrayArg(arg);
			}
		}
		
		Player player = new Player(playerStats[0], playerStats[1], playerStats[2], playerStats[3]);
		player.applyResults(ratings, win, loss);
		System.out.printf("Rating: %d\nWin: %d\nLoss: %d\nDraw: %d\nTotal Games: %d\n"
				, player.getRating(), player.getWin(), player.getLoss()
				, player.getDraw(), player.getTotalGames());

	}
	
	/**
	 * Split a command line argument containg a single integer
	 * @param arg
	 * @return
	 */
	public static int splitIntegerArg(String arg) {
		String[] intArg = arg.split("=");
		return Integer.parseInt(intArg[1]);
	}

	/**
	 * Split a command line argument containing an array of integers in a string
	 * @param arg a string containing a switch = comma delimited array of integers string
	 * @return int[]
	 */
	public static int[] splitIntegerArrayArg(String arg) {
		String[] argRatings = arg.split("=");
		String[] stringRatings = argRatings[1].split(",");
		
		int[] ratings = new int[stringRatings.length];
		int i = 0;
		for (String r: stringRatings) {
			ratings[i++] = Integer.parseInt(r);
		}
		return ratings;
	}

}
