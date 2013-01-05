package tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.RETURNS_DEFAULTS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Map;

import model.achievementchecker.Statistic;
import model.achievementchecker.Veteran;
import models.achievements.VeteranAward;
import models.statistics.historical.GamesPlayed;

import org.junit.Before;
import org.junit.Test;

public class VeteranTests {

	private Veteran veteran;
	private GamesPlayed games;
	private Map<Class<? extends Statistic>, Statistic> mockMap;
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		veteran = new Veteran(mock(VeteranAward.class));
		 games = mock(GamesPlayed.class);
		 mockMap = mock(Map.class, RETURNS_DEFAULTS);
	}

	@Test
	public void veteran_achievement_is_true_when_1000_games_are_won() {
		when(games.value()).thenReturn(1000);
		when(mockMap.containsKey(GamesPlayed.class)).thenReturn(true);
		when(mockMap.get(GamesPlayed.class)).thenReturn(games);
		assertTrue(veteran.isAchieved(mockMap));
	}
	
	@Test
	public void veteran_achievement_is_false_when_games_are_999() {
		when(games.value()).thenReturn(999);
		when(mockMap.containsKey(GamesPlayed.class)).thenReturn(true);
		when(mockMap.get(GamesPlayed.class)).thenReturn(games);
		assertFalse(veteran.isAchieved(mockMap));
	}
	
	@Test
	public void veteran_achievement_is_false_when_map_is_missing_required_stats() {
		assertFalse(veteran.isAchieved(mockMap));
	}

}
