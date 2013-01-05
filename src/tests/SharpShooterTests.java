package tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Map;

import model.achievementchecker.SharpShooter;
import model.achievementchecker.Statistic;
import models.achievements.SharpShooterAward;
import models.statistics.game.AttemptedAttacks;
import models.statistics.game.Hits;

import org.junit.Before;
import org.junit.Test;

public class SharpShooterTests {
	private SharpShooter shooter;
	private AttemptedAttacks attack;
	private Hits hits;
	private Map<Class<? extends Statistic>, Statistic> mockMap;
	
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		 shooter = new SharpShooter(mock(SharpShooterAward.class));
		 attack = mock(AttemptedAttacks.class);
		 hits = mock(Hits.class);
		 mockMap = mock(Map.class, RETURNS_DEFAULTS);
	}

	@Test
	public void sharpshooter_achievement_is_true_when_ratio_above_seventy_five() {
		when(hits.value()).thenReturn(25);
		when(attack.value()).thenReturn(30);
		when(mockMap.get(AttemptedAttacks.class)).thenReturn(attack);
		when(mockMap.get(Hits.class)).thenReturn(hits);
		when(mockMap.containsKey(AttemptedAttacks.class)).thenReturn(true);
		when(mockMap.containsKey(Hits.class)).thenReturn(true);
		assertTrue(shooter.isAchieved(mockMap));
	}
	
	@Test
	public void sharpshooter_achievement_is_false_when_attempted_attacks_are_zero() {
		when(hits.value()).thenReturn(25);
		when(attack.value()).thenReturn(0);
		when(mockMap.get(AttemptedAttacks.class)).thenReturn(attack);
		when(mockMap.get(Hits.class)).thenReturn(hits);
		when(mockMap.containsKey(AttemptedAttacks.class)).thenReturn(true);
		when(mockMap.containsKey(Hits.class)).thenReturn(true);
		assertTrue(shooter.isAchieved(mockMap));
	}
	
	@Test
	public void sharpshooter_achievement_is_false_when_ratio_above_seventy_five() {
		when(hits.value()).thenReturn(0);
		when(attack.value()).thenReturn(30);
		when(mockMap.get(AttemptedAttacks.class)).thenReturn(attack);
		when(mockMap.get(Hits.class)).thenReturn(hits);
		when(mockMap.containsKey(AttemptedAttacks.class)).thenReturn(true);
		when(mockMap.containsKey(Hits.class)).thenReturn(true);
		assertFalse(shooter.isAchieved(mockMap));
	}
	
	@Test
	public void sharpshooter_achievement_is_false_when_map_is_missing_required_stats() {
		assertFalse(shooter.isAchieved(mockMap));
	}

}
