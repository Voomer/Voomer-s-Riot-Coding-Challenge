package model.achievementchecker;

import java.util.Map;

import services.achievement.AchievementChecker;
import models.achievements.BruiserAward;
import models.statistics.game.DamageDone;

public class Bruiser implements AchievementChecker {

	private Class<DamageDone> damage;
	private BruiserAward award;
	private final int achievementThreshold = 500;
	
	public Bruiser(BruiserAward award) {
		this.damage = DamageDone.class;
		this.award = award;
	}
	@Override
	public boolean isAchieved(
			Map<Class<? extends Statistic>, Statistic> map) {
		if (map.containsKey(damage)) {
			return achievementThreshold <= map.get(damage).value();
		}
		return false;
	}
	@Override
	public BruiserAward achievement() {
		return award;
	}
}
