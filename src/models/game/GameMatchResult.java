package models.game;

/**
 * A data structure to hold the players of the team after a Game has completed.
 */
public class GameMatchResult {
	
	public Player[] team1;
	public Player[] team2;
	
	/**
	 * Instantiates an instance of the GameMatchResult class
	 * @param team1 A team in a game
	 * @param team2 A team in a game
	 */
	public GameMatchResult(Player[] team1, Player[]team2) {
		// assert(team1.length == team2.length);	
		this.team1 = team1;
		this.team2 = team2;
	}
}
