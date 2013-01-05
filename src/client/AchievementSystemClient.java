package client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import model.achievementchecker.*;
import models.achievements.*;
import models.game.GameMatchResult;
import models.game.Player;
import models.statistics.game.*;
import models.statistics.historical.*;

import org.picocontainer.*;
import org.picocontainer.behaviors.*;

import services.achievement.Achievement;
import services.achievement.AchievementChecker;
import services.achievement.DefaultAchievementResolver;
import services.achievement.TransformAchievementsToMap;

public class AchievementSystemClient {

	public static void main(String[] args) {
		MutablePicoContainer pico = new DefaultPicoContainer(new OptInCaching()); 
		
		//Register AchievementCheckers
		pico.as(Characteristics.CACHE).addComponent(BigWinner.class);
		pico.as(Characteristics.CACHE).addComponent(Bruiser.class);
		pico.as(Characteristics.CACHE).addComponent(BulletHell.class);
		pico.as(Characteristics.CACHE).addComponent(SharpShooter.class);
		pico.as(Characteristics.CACHE).addComponent(Veteran.class);
		
		//Register AchievementAwards 
		pico.as(Characteristics.CACHE).addComponent(BigWinnerAward.class);
		pico.as(Characteristics.CACHE).addComponent(BruiserAward.class);
		pico.as(Characteristics.CACHE).addComponent(BulletHellAward.class);
		pico.as(Characteristics.CACHE).addComponent(SharpShooterAward.class);
		pico.as(Characteristics.CACHE).addComponent(VeteranAward.class);
		
		//supporting interfaces/classes
		pico.addComponent(TransformAchievementsToMap.class);
		
		Collection<AchievementChecker> checkers = pico.getComponents(AchievementChecker.class);
	
		/*
		 * It is my belief that the achievement system should only take a collection of statistics
		 * and return the awards satisfied. If you didn't want check against every achievement after
		 * every game, you should load up a resolver with only the AchievementCheckers that you want
		 * to check against based on some criteria i.e filtering out achievements already on a per 
		 * player basis.
		 */
		AchievementResolver resolver = new DefaultAchievementResolver(checkers);

		// I realized these are all terrible names, the unit tests 
		//  are better for understanding the API
		Assists assist25 = new Assists();
		assist25.increment(25);
		Assists assist0 = new Assists();
		
		AttemptedAttacks aa500 = new AttemptedAttacks();
		aa500.increment(500);
		
		Hits hit0 = new Hits();
		Hits hit500 = new Hits();
		hit500.increment(500);
		
		GamesWon gw1000 = new GamesWon();
		gw1000.increment(1000);
		
		DamageDone ddover9000 = new DamageDone();
		ddover9000.increment(9001);
		
		DamageDone dd50 = new DamageDone();
		dd50.increment(50);
		
		NearMisses misses40 = new NearMisses();
		misses40.increment(40);
		
		GamesPlayed gp5000 = new GamesPlayed();
		gp5000.increment(5000);
		
		GamesPlayed gp10000 = new GamesPlayed();
		gp10000.increment(10000);
		
		SpellDamage sd100000 = new SpellDamage();
		sd100000.increment(100000);
		
		//team 1
		Collection<Statistic> r1list = new ArrayList<Statistic>();
		r1list.add(gp10000);
		r1list.add(gw1000);
		Player r1 = new Player("Patchouli", r1list);
		
		Collection<Statistic> r2list = new ArrayList<Statistic>();
		r2list.add(hit500);
		r2list.add(aa500);
		r2list.add(sd100000);
		Player r2 = new Player("Star Platinium", r2list);
		
		Collection<Statistic> r3list = new ArrayList<Statistic>();
		r3list.add(hit500);
		r3list.add(ddover9000);
		Player r3 = new Player("Cassandra Cain", r3list);
		
		//team 2
		Collection<Statistic> b1list = new ArrayList<Statistic>();
		b1list.add(misses40);
		b1list.add(hit500);
		Player b1 = new Player("Battler", b1list);
		
		Collection<Statistic> b2list = new ArrayList<Statistic>();
		b2list.add(hit0);
		b2list.add(assist25);
		b2list.add(dd50);
		Player b2 = new Player("Andrew Ng", b2list);
		
		Collection<Statistic> b3list = new ArrayList<Statistic>();
		b3list.add(assist0);
		b3list.add(ddover9000);
		Player b3 = new Player("William Tecumseh Sherman", b3list);
		
		GameMatchResult results = 
				new GameMatchResult(
						new Player[]{r1, r2, r3}, new Player[] {b1, b2, b3});
		TransformAchievementsToMap transformer = new TransformAchievementsToMap();
		MatchResultToAchievements matchResultToAchievements = 
				new MatchResultToAchievements(resolver, transformer);
		Map<Player, Collection<Achievement>> playerAchievements = 
				matchResultToAchievements.getAchievementsForMatchResult(results);
		
		for (Entry<Player, Collection<Achievement>> entry: playerAchievements.entrySet()) {
			System.out.println(entry.getKey() + " has earned the following achievements: " 
		+ Arrays.toString(entry.getValue().toArray()));
		}
		
		
		
		
		
		
	}
}
