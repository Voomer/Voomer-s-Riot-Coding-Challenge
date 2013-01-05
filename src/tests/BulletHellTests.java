package tests;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.Map;

import model.achievementchecker.BulletHell;
import model.achievementchecker.Statistic;
import models.achievements.BulletHellAward;
import models.statistics.game.AttemptedAttacks;
import models.statistics.game.Hits;
import models.statistics.game.NearMisses;

import org.junit.Before;
import org.junit.Test;

public class BulletHellTests {
	private BulletHell hell;
	private NearMisses misses;
	private Hits hits;
	private Map<Class<? extends Statistic>, Statistic> mockMap;
	
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		 hell = new BulletHell(mock(BulletHellAward.class));
		 misses = mock(NearMisses.class);
		 hits = mock(Hits.class);
		 mockMap = mock(Map.class, RETURNS_DEFAULTS);
	}
	
	@Test
	public void bulletHell_achievement_is_false_when_threshold_is_not_met() {
		when(hits.value()).thenReturn(29);
		when(misses.value()).thenReturn(40);
		when(mockMap.get(NearMisses.class)).thenReturn(misses);
		when(mockMap.get(Hits.class)).thenReturn(hits);
		when(mockMap.containsKey(AttemptedAttacks.class)).thenReturn(true);
		when(mockMap.containsKey(Hits.class)).thenReturn(true);
		assertFalse(hell.isAchieved(mockMap));
	}
	
	@Test
	public void sharpshooter_achievement_is_true_when_ratio_above_seventy_five() {
		when(hits.value()).thenReturn(41);
		when(misses.value()).thenReturn(40);
		when(mockMap.get(NearMisses.class)).thenReturn(misses);
		when(mockMap.get(Hits.class)).thenReturn(hits);
		when(mockMap.containsKey(AttemptedAttacks.class)).thenReturn(true);
		when(mockMap.containsKey(Hits.class)).thenReturn(true);
		assertTrue(hell.isAchieved(mockMap));
	}
	
	@Test
	public void sharpshooter_achievement_is_false_when_map_is_missing_required_stats() {
		assertFalse(hell.isAchieved(mockMap));
	}
}
