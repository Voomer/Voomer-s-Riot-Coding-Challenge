package services.achievement;

import java.util.Map;

import model.achievementchecker.Statistic;

/**
 * The user of this interface can check a map of Statistics to identify 
 * if an Achievement has been achieved. It was intended use is to be used
 * with a maestro type object that retrieves achievements after verifying
 * Statistics
 */
public interface AchievementChecker {

	/**
	 * Checks to see if an achievement criteria has been met.
	 * @param map a Map of associated Classes and Statistics
	 * @return True is an Achievement criteria has been met.
	 */
	boolean isAchieved(Map<Class<? extends Statistic>, Statistic> map);
	
	/**
	 * Gets the associated achievement
	 * @return The associated Achievement
	 */
	Achievement achievement();
}
