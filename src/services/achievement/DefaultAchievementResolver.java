package services.achievement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import client.AchievementResolver;

import model.achievementchecker.Statistic;

/**
 * This class will evaluate the Statistics given to it against all the AchievementCheckers
 * 
 */
public class DefaultAchievementResolver implements AchievementResolver {
	Collection<AchievementChecker> achievments;
	public DefaultAchievementResolver(Collection<AchievementChecker> achievments) {
		this.achievments = achievments;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<Achievement> resolveAchievements(Map<Class<? extends Statistic>, Statistic> map) {
		Collection<Achievement> list = new ArrayList<Achievement>();
		for (AchievementChecker checker : achievments) {
			if (checker.isAchieved(map)) {
				list.add(checker.achievement());
			}
		}
		return list;	
	};
}
