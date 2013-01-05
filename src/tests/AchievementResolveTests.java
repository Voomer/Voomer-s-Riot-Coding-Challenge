package tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.Collection;
import java.util.LinkedList;

import model.achievementchecker.BigWinner;
import model.achievementchecker.Bruiser;
import model.achievementchecker.Statistic;
import models.achievements.BigWinnerAward;
import models.achievements.BruiserAward;
import models.statistics.game.DamageDone;
import models.statistics.historical.GamesWon;

import org.junit.Before;
import org.junit.Test;

import client.AchievementResolver;

import services.achievement.Achievement;
import services.achievement.AchievementChecker;
import services.achievement.DefaultAchievementResolver;
import services.achievement.TransformAchievementsToMap;

public class AchievementResolveTests {
	
	Collection<AchievementChecker> achievementsCheckers;
	private LinkedList<Statistic> statistics;
	private TransformAchievementsToMap transformer;
	
    @Before
	public void setUp() {
		achievementsCheckers = new LinkedList<AchievementChecker>();
		statistics = new LinkedList<Statistic>();
		transformer = new TransformAchievementsToMap();
	}
	
	@Test
	public void resolveAchievement_returns_emptyCollection_when_checkers_are_not_satisfied() {
		achievementsCheckers.add(mock(AchievementChecker.class));
		achievementsCheckers.add(mock(AchievementChecker.class));
		AchievementResolver resolver = new DefaultAchievementResolver(achievementsCheckers);
		Collection<Achievement> list = resolver.resolveAchievements(transformer.transformToMap(statistics));
		assertTrue(list.isEmpty());
	}
	
	
	@Test
	//Integration
	public void resolveAchievement_returns_proper_achievements_when_checkers_are_satisfied() {
		BruiserAward bruiserAward = new BruiserAward();
		BigWinnerAward bigWinnerAward = new BigWinnerAward();
		achievementsCheckers.add(new Bruiser(bruiserAward));
		achievementsCheckers.add(new BigWinner(bigWinnerAward));
		AchievementResolver resolver = new DefaultAchievementResolver(achievementsCheckers);
		GamesWon gamesWon = new GamesWon();
		gamesWon.increment(99999);
		DamageDone damage = new DamageDone();
		damage.increment(99999);
		statistics.add(damage);
		statistics.add(gamesWon);
		Collection<Achievement> list = resolver.resolveAchievements(transformer.transformToMap(statistics));
		assertTrue(list.contains(bruiserAward));
		assertTrue(list.contains(bigWinnerAward));
	}
}
