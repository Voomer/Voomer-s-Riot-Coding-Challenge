package models.statistics.game;

import model.achievementchecker.Statistic;
import models.game.Counter;

public class NearMisses implements Statistic , Counter {

	int value = 0;
	public int value() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public void increment(int number) {
		value += number;
	}

}
