/**
 * 
 */
package player;

/**
 * @author maddox
 *
 */
public class Player {
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
	
	public Player(int rating, int win, int loss, int draw) {
		this.rating = rating;
		this.win = win;
		this.loss = loss;
		this.draw = draw;
	}

	public double calcK(double Ne, int m) {
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
				) 
				/ (this.getTotalGames()+m) ;
		rPost = Math.round(rPost);
		return (int) Math.max(100, (int) rPost); // can't have less than 100
	}
	
	private double averageArray(int[] list) {
		int total = 0;
		for (int i: list)
			total = total + i;
		return (double) total / list.length;
	}

	public void applyResults(int[] r, int wNew, int lNew) {
		if (this.isEstablished() ) 
			this.rating = this.calcEffectiveRating(r, wNew, lNew);
		else
			this.rating = this.calcProvisionalRating(r, wNew, lNew);
		this.win = this.win + wNew;
		this.loss = this.loss + lNew;
		this.draw = this.draw + (r.length - wNew - lNew);
	}
	
	public boolean isEstablished() {
		return (this.getTotalGames() > 25) ? true : false;
	}

	public double calcEffectiveGames() {
		double nr;
		if (this.rating >= 2200 || this.getTotalGames() > 50) 
			nr = 50;
		else
			nr = 50 / Math.sqrt((1+Math.pow(2200 - this.rating, 2)/100000));
		return (double) Math.min(this.getTotalGames(), nr);
	}
	
	public int calcEffectiveRating(int[] r, int wNew, int lNew) {
		double k = this.calcK(this.calcEffectiveGames(), r.length);
		double s = wNew;
		if (wNew + lNew != r.length) // calc Drawn games if any
			s = s + (r.length - wNew - lNew)*0.5; 
		double e = this.calcWinExpectancy(r);
		double b = this.calcBonus(r.length, k, s, e);
		double newRating = Math.round(this.rating + k*(s-e) + b);
		return (int) Math.max(100, newRating);
	}
	
	public double calcWinExpectancy(int[] opponents) {
		double we = 0;
		for (int i = 0; i < opponents.length; i++) {
			we = we + (1 / (Math.pow((double) 1/10, (double) (this.rating - opponents[i])/400 ) +1) );
		}
		return we;
	}

	/**
	 * @param m int number of games
	 * @param k double calculated K value
	 * @param s double calculated tournament score
	 * @param e double calculated performance score estimate
	 * @return int bonus value
	 */
	public double calcBonus(int m, double k, double s, double e) {
		if (m < 3) 
			return 0;
		
		double bonus = k*(s-e)-10*Math.sqrt(m);
		return Math.max(0, bonus);
	}

}
