package hotciv.standard;

import static org.junit.Assert.*;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.standard.Archer;
import hotciv.standard.GameImpl;
import hotciv.standard.Legion;
import hotciv.standard.delta.DeltaCivFactory;
import hotciv.standard.gamma.GammaCivFactory;

import org.junit.Before;
import org.junit.Test;

public class TestDeltaCiv {

private GameImpl game;
	
	@Before
	public void setUp() {
		
		game = new GameImpl(new DeltaCivFactory());		

	}
	
	
	@Test
	public void shouldHaveBlueCityAt4_5() {
		Position p = new Position(4,5);
		assertEquals("There should be a blue city at 4,5", Player.BLUE, game.getTileAt(p).getCity().getOwner());
	}
	
	@Test
	public void shouldHaveRedCityAt8_12(){
		Position p = new Position(8,12);
		assertEquals("There should be a red city at 8,12", Player.RED, game.getTileAt(p).getCity().getOwner());
	}

	@Test
	public void checkForPlainAt0_4(){
		Position p = new Position(0,4);
		assertEquals("0,4 should be plains", GameConstants.PLAINS, game.getTileAt(p).getTypeString());
	}
	
	@Test
	public void checkForOceanAt2_10(){
		Position p = new Position(2,10);
		assertEquals("2,10 should be ocean", GameConstants.OCEANS, game.getTileAt(p).getTypeString());
	}
	
	@Test
	public void checkForForestAt5_2(){
		Position p = new Position(5,2);
		assertEquals("5,2 should be forest", GameConstants.FOREST,  game.getTileAt(p).getTypeString());
	}
	
	@Test
	public void checkForHill1_3(){
		Position p = new Position(1,3);
		assertEquals("1,3 should be hill", GameConstants.HILLS,  game.getTileAt(p).getTypeString());
	}
	
	@Test
	public void checkForMountain3_3(){
		Position p = new Position(3,3);
		assertEquals("3,3 should be mountain", GameConstants.MOUNTAINS,  game.getTileAt(p).getTypeString());
	}
	
	@Test
	public void checkForBlueLegionAt4_4(){
		Position p = new Position(4,4);
		assertEquals("4,4 should have a blue legion", GameConstants.LEGION, game.getTileAt(p).getUnit().getTypeString());
		assertEquals("4,4 should have a blue legion", Player.BLUE, game.getTileAt(p).getUnit().getOwner());
	}
	
	@Test 
	public void checkForBlueSettler5_5(){
		Position p = new Position(5,5);
		assertEquals("4,4 should have a blue settler", GameConstants.SETTLER, game.getTileAt(p).getUnit().getTypeString());
		assertEquals("4,4 should have a blue settler", Player.RED, game.getTileAt(p).getUnit().getOwner());
	}
	
	@Test
	public void checkForRedArcherAt3_8(){
		Position p = new Position(3,8);
		assertEquals("4,4 should have a red archer", GameConstants.ARCHER, game.getTileAt(p).getUnit().getTypeString());
		assertEquals("4,4 should have a red archer", Player.RED, game.getTileAt(p).getUnit().getOwner());
	}
	
}
