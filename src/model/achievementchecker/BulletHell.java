package model.achievementchecker;

import java.util.Map;

import models.achievements.BulletHellAward;
import models.statistics.game.Hits;
import models.statistics.game.NearMisses;
import services.achievement.AchievementChecker;

public class BulletHell implements AchievementChecker {
	
	private BulletHellAward award;
	private Class<Hits> hits;
	private Class<NearMisses> misses;
	private final int achievementThreshold = 30;

	public BulletHell(BulletHellAward award) {
		this.misses = NearMisses.class;
		this.hits = Hits.class;
		this.award = award;
	}
	
	@Override
	public boolean isAchieved(Map<Class<? extends Statistic>, Statistic> map) {
		if (map.containsKey(misses) && map.containsKey(hits)) {
			if(map.get(misses).value() >= achievementThreshold && map.get(hits).value() >= achievementThreshold)
			return true;
		}
		return false;
	}

	@Override
	public BulletHellAward achievement() {
		return award;
	}

}
