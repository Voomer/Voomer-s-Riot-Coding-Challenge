package models.statistics.historical;

import model.achievementchecker.Statistic;
import models.game.Counter;

public class GamesPlayed implements Statistic, Counter {
	int value = 0;
	@Override
	public int value() {
		return value;
	}
	
	@Override
	public void increment(int number) {
		value += number;
	}
}
