package tests;

import static org.junit.Assert.*;

import java.util.Map;

import model.achievementchecker.BigWinner;
import model.achievementchecker.Statistic;
import models.achievements.BigWinnerAward;
import models.statistics.historical.GamesWon;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class BigWinnerTests {

	private GamesWon games;
	private BigWinner winner;
	private Map<Class<? extends Statistic>, Statistic> mockMap;

	@SuppressWarnings("unchecked")
    @Before
	public void setUp() {
		 winner = new BigWinner(mock(BigWinnerAward.class));
		 games = mock(GamesWon.class);
		 mockMap = mock(Map.class, RETURNS_DEFAULTS);
	}

	@Test
	public void bigwinner_achievement_is_true_when_200_games_are_won() {	
		when(games.value()).thenReturn(200);
		when(mockMap.containsKey(GamesWon.class)).thenReturn(true);
		when(mockMap.get(GamesWon.class)).thenReturn(games);
		assertTrue(winner.isAchieved(mockMap));
	}
	
	@Test
	public void bigwinner_achievement_is_false_when_199_games_are_won() {
		when(games.value()).thenReturn(199);
		when(mockMap.containsKey(GamesWon.class)).thenReturn(true);
		when(mockMap.get(GamesWon.class)).thenReturn(games);
		assertFalse(winner.isAchieved(mockMap));
	}
	
	@Test
	public void bigwinner_achievement_is_false_when_map_is_missing_required_stats() {
		assertFalse(winner.isAchieved(mockMap));
	}
}
