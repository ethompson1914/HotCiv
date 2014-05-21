package hotciv.standard;

import static org.junit.Assert.*;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.alpha.AlphaCivFactory;
import hotciv.standard.epsilon.EpsilonCivFactory;

import org.junit.Before;
import org.junit.Test;

public class TestMoveHeuristics {
	private Game game;
	
	@Before
	public void setUp() {
		game = new GameImpl(new EpsilonCivFactory());
	}
	
	@Test
	public void testUnitCannotMoveToLeftOfBoard() {
		// valid row
		// invalid column
		assertFalse("Unit should not move to the left of the board.", game.moveUnit(new Position(2, 0), new Position(2, -1)));
	}
	
	@Test
	public void testUnitCannotMoveToRightOfBoard() {
		// valid row
		// invalid column
		game.addUnitToTile(new Archer(Player.RED), new Position(0, 15));
		assertFalse("Unit should not move to the right of the board.", game.moveUnit(new Position(0, 15), new Position(0, 16)));
	}

	@Test
	public void testUnitCannotMoveAboveBoard() {
		// invalid row
		// valid column
		game.addUnitToTile(new Archer(Player.RED), new Position(0, 15));
		assertFalse("Unit should not move above the board.", game.moveUnit(new Position(0, 15), new Position(-1, 15)));
	}
	
	@Test
	public void testUnitCannotMoveBelowBoard() {
		// invalid row
		// valid column
		game.addUnitToTile(new Archer(Player.RED), new Position(15, 15));
		assertFalse("Unit should not move below the board.", game.moveUnit(new Position(15, 15), new Position(16, 15)));
	}
	
	@Test
	public void testUnitCanMoveToValidPosition() {
		// valid row & column
		// valid distance
		// valid owner
		// valid terrain
		assertTrue("Unit should be allowed to move to a valid position.", game.moveUnit(new Position(2, 0), new Position(3, 0)));
	}
	
	@Test
	public void testUnitCannotMoveMoreThanOneTile() {
		// invalid distance
		assertFalse("Unit should not move more than one tile.", game.moveUnit(new Position(2, 0), new Position(4, 0)));
	}
	
	@Test
	public void testUnitCannotBeMovedByOtherPlayer() {
		// invalid owner
		assertFalse("Unit should not be moved by the other player.", game.moveUnit(new Position(3, 2), new Position(3, 3)));
	}
	
	@Test
	public void testUnitCannotMoveOntoAMountain() {
		// invalid terrain
		game.endOfTurn();
		assertFalse("Unit should not be moved onto a mountain.", game.moveUnit(new Position(3, 2), new Position(2, 2)));
	}
	
	@Test
	public void testUnitCannotMoveMoreThanOnce() {
		game.moveUnit(new Position(2, 0), new Position(3, 0));
		// already moved
		assertFalse("Unit should not be moved twice.", game.moveUnit(new Position(3, 0), new Position(2, 0)));
	}
}