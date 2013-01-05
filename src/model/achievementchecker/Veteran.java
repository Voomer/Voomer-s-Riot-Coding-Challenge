package model.achievementchecker;

import java.util.Map;

import services.achievement.AchievementChecker;
import models.achievements.VeteranAward;
import models.statistics.historical.GamesPlayed;

public class Veteran implements AchievementChecker {
	private Class<GamesPlayed> games;
	private final int achievementThreshold = 1000;
	private VeteranAward award;
	public Veteran(VeteranAward award) {
		this.games = GamesPlayed.class;
		this.award = award;
	}

	@Override
	public boolean isAchieved(
			Map<Class<? extends Statistic>, Statistic> map) {
		if (map.containsKey(games)) {
			return achievementThreshold <= map.get(games).value();
		}
		return false;
	}
	@Override
	public VeteranAward achievement() {
		return award;
	}
}
