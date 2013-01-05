package models.game;

import java.util.ArrayList;
import java.util.Collection;

import model.achievementchecker.Statistic;

/**
 * Represents a player at the end of a game. The user can store Statistics associated to the player
 * for Achievement evaluation. A structure to hold information with no logic.
 *
 */
public class Player {
	private String id;
	private Collection<Statistic> trackedStats;
	/**
	 * Instantiates an instance of the Player class.
	 * @param id The player's handle
	 */
	public Player(String id) {
		this(id,new ArrayList<Statistic>());
	}
	
	/**
	  * Instantiates an instance of the Player class.
	 * @param id The player's handle
	 * @param trackedStats A list of Statistics to track
	 */
	public Player(String id, Collection<Statistic> trackedStats)
	{
		this.id = id;
		this.trackedStats = trackedStats;
	}
	
	/**
	 * Statistics associated to the player
	 * @return the Statistics collection associate to the player
	 */
	public Collection<Statistic> statistics() {
		return trackedStats;
	}
	
	@Override
	public String toString() {
		return id;
	}
}
