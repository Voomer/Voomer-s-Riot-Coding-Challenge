package tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.RETURNS_DEFAULTS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Map;

import model.achievementchecker.Bruiser;
import model.achievementchecker.Statistic;
import models.achievements.BruiserAward;
import models.statistics.game.DamageDone;

import org.junit.Before;
import org.junit.Test;

public class BruiserTest {

	private DamageDone damage;
	private Bruiser bruiser;
	private Map<Class<? extends Statistic>, Statistic> mockMap;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		 bruiser = new Bruiser(mock(BruiserAward.class));
		 damage= mock(DamageDone.class);
		 mockMap = mock(Map.class, RETURNS_DEFAULTS);
	}

	@Test
	public void bruiser_achievement_is_true_when_500_damage() {
		when(damage.value()).thenReturn(500);
		when(mockMap.containsKey(DamageDone.class)).thenReturn(true);
		when(mockMap.get(DamageDone.class)).thenReturn(damage);
		assertTrue(bruiser.isAchieved(mockMap));
	}
	
	@Test
	public void bruiser_achievement_is_false_when_damage_is_400() {
		when(damage.value()).thenReturn(499);
		when(mockMap.containsKey(DamageDone.class)).thenReturn(true);
		when(mockMap.get(DamageDone.class)).thenReturn(damage);
		assertFalse(bruiser.isAchieved(mockMap));
	}
	
	@Test
	public void bigwinner_achievement_is_false_when_map_is_missing_required_stats() {
		assertFalse(bruiser.isAchieved(mockMap));
	}

}
