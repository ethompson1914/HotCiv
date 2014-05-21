package hotciv.standard;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import hotciv.framework.LoggingGame;
import hotciv.framework.LoggingState;
import hotciv.framework.Position;
import hotciv.standard.semi.SemiCivFactory;

import org.junit.Before;
import org.junit.Test;

public class TestLoggingGame {
	private LoggingGame game;
	
	
	@Before
	public void setup(){
		game = new LoggingGameImpl(new GameImpl(new SemiCivFactory()));
	}
	
	
	@Test
	public void checkingLogForRedSettler() {
		Position p = new Position(5,5);
		game.performUnitActionAt(p);
		assertEquals("should display log", "RED has performed unit action at [5,5].", game.getLastMessage());
	}
	
	@Test
	public void checkingLogForRedSettler2() {
		Position p = new Position(5,5);
		game.createCity(p);
		assertEquals("should display log", "RED has built a city at [5,5].",game.getLastMessage());
	}
	
	@Test
	public void checkingLogForEndOfTurn() {
		game.endOfTurn();
		assertEquals("should display log", "Turn has ended for player RED.",game.getLastMessage());
	}
	
	@Test
	public void checkingLogForChangeWorkForceFocusInCity() {
		Position p = new Position(8,12);
		game.changeWorkForceFocusInCityAt(p, "production");
		assertEquals("should display log", "RED changes work focus in city at [8,12] to production.",game.getLastMessage());
	}
	
	@Test
	public void checkingLogForChangeProductionInCityAt() {
		Position p = new Position(8,12);
		game.changeProductionInCityAt(p, "legion");
		assertEquals("should display log", "RED changes production in city at [8,12] to legion.",game.getLastMessage());
	}
	
	@Test
	public void checkingLogForAttack(){
		Position blue = new Position(4,4);
		Position red = new Position(5,5);
		game.endOfTurn();
		game.attack(blue, red);
		assertEquals("should display log", "BLUE Legion at [4,4] attacks RED Settler at [5,5].",game.getLastMessage());
	}
	
	@Test
	public void shouldNotLogAfterStateSwitch() {
		game.switchLoggingState();
		LoggingState s = game.getLoggingState();
		assertTrue("Current state should not be logging", NoLoggingState.class.isInstance(s));
	}
}
