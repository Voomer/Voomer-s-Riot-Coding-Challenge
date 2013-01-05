package model.achievementchecker;

import java.util.Map;

import services.achievement.AchievementChecker;
import models.achievements.BigWinnerAward;
import models.statistics.historical.GamesWon;

public class BigWinner implements AchievementChecker {

	private Class<GamesWon> games;
	private final int achievementThreshold = 200;
	private BigWinnerAward award;
	public BigWinner(BigWinnerAward award) {
		games = GamesWon.class;
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
	public BigWinnerAward achievement() {
		return award;
	}
}