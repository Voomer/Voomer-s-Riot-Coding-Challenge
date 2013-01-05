package client;

import java.util.Collection;
import java.util.Map;

import services.achievement.Achievement;

import model.achievementchecker.Statistic;

/**
 * A resolver for achievements. The implementor of this interface has control over
 * how achievements will be awarded based on the Statistics it receives.
 */
public interface AchievementResolver {

	/**
	 * Returns a list of achievements based on the values of the Statistic objects in the map
	 * @param map A map with the Key being the class type of the Statistic and
	 *  the value being an instantiated object of that type
	 * @return A list of Achievements
	 */
	public Collection<Achievement> resolveAchievements(
			Map<Class<? extends Statistic>, Statistic> map);

}