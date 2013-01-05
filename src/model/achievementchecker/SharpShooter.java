package model.achievementchecker;

import java.util.Map;

import services.achievement.AchievementChecker;
import models.achievements.SharpShooterAward;
import models.statistics.game.AttemptedAttacks;
import models.statistics.game.Hits;

public class SharpShooter implements AchievementChecker {
	
	private Class<AttemptedAttacks> attemptedAttacks;
	private Class<Hits> hits;
	private final double achievementThreshold = .75;
	private SharpShooterAward award;
	public SharpShooter(SharpShooterAward award) {
		this.hits = Hits.class;
		this.attemptedAttacks = AttemptedAttacks.class;
		this.award = award;
	}
	
	@Override
	public boolean isAchieved(
			Map<Class<? extends Statistic>, Statistic> map) {
		if (map.containsKey(attemptedAttacks) && map.containsKey(hits)) {
			if (map.get(attemptedAttacks).value() == 0) {
				return false;
			}
			else {
				double value =  map.get(hits).value()
						/ (double) map.get(attemptedAttacks).value();			
				return achievementThreshold <= value;
			}
		}
		return false;
	}

	@Override
	public SharpShooterAward achievement() {
		return award;
	}
}
