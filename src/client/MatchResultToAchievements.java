package client;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import services.achievement.Achievement;
import services.achievement.TransformAchievementsToMap;

import models.game.GameMatchResult;
import models.game.Player;

/**
 * A helper class to take a player's Statistics and retrieve any achievements
 * that player right have earned.
 */
public class MatchResultToAchievements {

	private AchievementResolver resolver;
	private TransformAchievementsToMap transformer;
	
	/**
	 * Instantiates an instance of the MatchResultToAchievements class.
	 * @param resolver A resolver to send statistics
	 * @param transformer Maps the Statistics to a form the resolver can take
	 */
	public MatchResultToAchievements(AchievementResolver resolver, TransformAchievementsToMap transformer) {
		this.resolver = resolver;
		this.transformer = transformer;
	}
	
	/**
	 * 
	 * @param result GameMatchResult to evaluate for player Achievements
	 * @return A map of achievements for the players and their Achievements
	 */
	public Map<Player, Collection<Achievement>> getAchievementsForMatchResult(GameMatchResult result) {
		Map<Player, Collection<Achievement>> map =
				new HashMap<Player, Collection<Achievement>>();
		
		for(Player player: result.team1) {
			map.put(player, resolver.resolveAchievements(
								transformer.transformToMap(player.statistics())));
		}
		
		for(Player player: result.team2) {
			map.put(player, resolver.resolveAchievements(
								transformer.transformToMap(player.statistics())));
		}
		return map;
	}
}
