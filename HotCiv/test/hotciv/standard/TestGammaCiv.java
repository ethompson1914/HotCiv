package hotciv.standard;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.standard.Archer;
import hotciv.standard.GameImpl;
import hotciv.standard.Legion;
import hotciv.standard.gamma.GammaCivFactory;

import org.junit.Before;
import org.junit.Test;

public class TestGammaCiv {
	private GameImpl game;
	
	@Before
	public void setUp() {
		
		game = new GameImpl(new GammaCivFactory());
		Position toArcherPosition = new Position(2,1);
		Position blueLegionPosition = new Position(3,0);

		Unit archer2= new Archer(Player.RED);
		Unit legion = new Legion(Player.BLUE);

		game.addUnitToTile(legion, blueLegionPosition);
		game.addUnitToTile(archer2, toArcherPosition);

	}

	@Test
	public void checkRedArcherDefStrength() {
		Position p = new Position(2,1);
		assertEquals("Red Archers strength should start at 3", 3, game.getTileAt(p).getUnit().getDefensiveStrength());
	}
	
	@Test
	public void testFortifyArcher() {
		Position p = new Position(2,1);
		game.performUnitActionAt(p);
		assertEquals("Red Archers new def strength should be 6", 6,  game.getTileAt(p).getUnit().getDefensiveStrength());
	}

	@Test
	public void testFortifiedArcherMoveCount(){
		Position p = new Position(2,1);
		game.performUnitActionAt(p);
		assertEquals("Red Archers move count should be 0", 0,  game.getTileAt(p).getUnit().getMoveCount());
	}
	
	@Test
	public void testDoubleFortifiedArcher(){
		Position p = new Position(2,1);
		game.performUnitActionAt(p);
		game.performUnitActionAt(p);
		assertEquals("Red Archers can't perform action twice", 0,  game.getTileAt(p).getUnit().getMoveCount());
	}
	
	@Test
	public void testDoubleFortifiedArcherTwoTurns(){
		Position p = new Position(2,1);
		Position toP = new Position(3,1);
		game.moveUnit(p, toP);
		game.performUnitActionAt(toP);
		assertEquals("Red Archers new def strength should be 6", 6,  game.getTileAt(toP).getUnit().getDefensiveStrength());
		game.endOfTurn();
		game.endOfTurn();
		game.performUnitActionAt(toP);
		game.moveUnit(toP, p);
		assertEquals("Red Archers move count should be 0", 0,  game.getTileAt(p).getUnit().getMoveCount());
		assertEquals("Red Archers new def strength should be 3", 3,  game.getTileAt(p).getUnit().getDefensiveStrength());
	}
	
	@Test
	public void testSettlerAction(){
		Position p = new Position(4,3);
		game.performUnitActionAt(p);
		assertNull("No red settler at 4,3",game.getTileAt(p).getUnit());
		assertEquals("New Red city at 4,3", Player.RED, game.getTileAt(p).getCity().getOwner());
	}
	
	@Test
	public void testSettlerMoveAndAction(){
		Position p = new Position(4,3);
		Position toP = new Position(3,2);
		game.moveUnit(p, toP);
		game.performUnitActionAt(toP);
		assertNull("No red settler at 4,3",game.getTileAt(toP).getUnit());
		assertEquals("New Red city at 4,3", Player.RED, game.getTileAt(toP).getCity().getOwner());
	}
	
}
