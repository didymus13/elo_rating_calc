import java.io.Console;
import java.util.Arrays;
import java.util.Scanner;

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
	 * Required arguments: 
	 * 		-r=X,Y,Z comma delimited list of opponent ratings for each game played 
	 * 		-w=X -l=X number of wins , number of losses
	 * 		-p=A,B,C,D comma delimited list of original player rating,wins,losses,draws
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		int[] ratings = null;
		int win = 0;
		int loss = 0;
		int[] playerStats = new int[4];
		boolean runIt = true;
		
		while (runIt) {
			Console in = System.console(); 
			if (in == null) {
			    System.err.println("No console.");
	            System.exit(1);
			}

			if (args.length > 0) {
				runIt = false; // process args once
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
			} else { // Prompt
				System.out.println("\n===================");
				System.out.println("Enter player stats");
				System.out.println("===================\n");
				playerStats[0] = Integer.parseInt(in.readLine("Rating (enter 0 for unrated)? "));
				playerStats[1] = Integer.parseInt(in.readLine("Total lifetime games won? "));
				playerStats[2] = Integer.parseInt(in.readLine("Total lifetime games lost? "));
				playerStats[3] = Integer.parseInt(in.readLine("Total lifetime games drawn? "));
				
				System.out.println("\nEnter new results:");
				System.out.println("===================\n");
				ratings = splitIntegerArrayArg(in.readLine("Comma delimited list of opponent ratings:"));
				win = Integer.parseInt(in.readLine("Number of new wins? "));
				loss = Integer.parseInt(in.readLine("Number of new losses? "));
			}
			
			Player player = new Player(playerStats[0], playerStats[1], playerStats[2], playerStats[3]);
			player.applyResults(ratings, win, loss);
			System.out.printf("Rating: %d\nWin: %d\nLoss: %d\nDraw: %d\nTotal Games: %d\n"
					, player.getRating(), player.getWin(), player.getLoss()
					, player.getDraw(), player.getTotalGames());
			
			if (!runIt || in.readLine("\nProcess another (Y/n)? ").equals(("n"))); {
				System.out.println("Thank you");
				runIt = false; 
			}
		}
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
		String[] stringRatings = null;
		if (arg.contains("=")) { // process commandline switch
			String[] argRatings = arg.split("=");
			stringRatings = argRatings[1].split(",");
		} else { // read straigh list of ints
			stringRatings = arg.split(",");
		}
		
		int[] ratings = new int[stringRatings.length];
		int i = 0;
		for (String r: stringRatings) {
			ratings[i++] = Integer.parseInt(r);
		}
		return ratings;
	}

}
