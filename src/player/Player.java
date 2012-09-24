/**
 * 
 */
package player;

import java.util.List;

/**
 * @author maddox
 *
 */
public class Player {
	private int id;
	private String name;
	private int win = 0;
	private int loss = 0;
	private int draw = 0;
	private int rating = 0;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the win
	 */
	public int getWin() {
		return win;
	}

	/**
	 * @return the loss
	 */
	public int getLoss() {
		return loss;
	}

	/**
	 * @return the draw
	 */
	public int getDraw() {
		return draw;
	}

	/**
	 * @return the rating
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	public Player(String name) {
		this.name = name;
	}
	
	public double calcK(double Ne, int m) {
		// TODO
		return (double) 800 / (Ne + m); 
	}
	
	public int getTotalGames() {
		return (int) this.win + this.draw + this.loss;
	}
	
	public int calcProvisionalRating(int r[], int wNew, int lNew) {
		int m = r.length;
		double rPost = ( 
				this.getTotalGames()*this.rating 
				+ m*this.averageArray(r)
				+ (wNew - lNew)*400 
				) / (this.getTotalGames()+m) ;
		return (int) Math.round(rPost);
	}
	
	private int sumArray(int[] list) {
		int total = 0;
		for (int v: list) 
			total = total + v;
		return total;
	}
	
	private double averageArray(int[] list) {
		return (double) this.sumArray(list) / list.length;
	}

	public void applyResults(int[] r, int wNew, int lNew) {
		if (this.getTotalGames() <= 25 ) {
			// Calculate provisional rating
			this.rating = this.calcProvisionalRating(r, wNew, lNew);
			this.win = this.win + wNew;
			this.loss = this.loss + lNew;
			this.draw = this.draw + (r.length - wNew - lNew);
		} else {
			// Calculate Established rating
		}
	}

}
