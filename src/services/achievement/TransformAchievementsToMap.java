package services.achievement;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import model.achievementchecker.Statistic;

/**
 * The user can transform a collection of Statistics into a map of {Class : Statistic}
 * Used to support AchievmentResolver
 */
public class TransformAchievementsToMap {

	/**
	 * Returns the Collection as Map in the form {Class: Statistic}
	 * @param stats A collection of Statistics
	 * @return A map in the form of {Class: Statistic}
	 */
	public Map<Class<? extends Statistic>, Statistic> transformToMap(Collection<Statistic> stats) {
		 Map<Class<? extends Statistic>, Statistic> map 
		 	= new HashMap<Class<? extends Statistic>, Statistic>();
		 for(Statistic stat : stats) {
			 map.put(stat.getClass(), stat);
		 }
		 return map;
	}
}
