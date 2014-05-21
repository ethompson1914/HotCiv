package hotciv.standard.semi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.standard.GameImpl;
import hotciv.standard.Legion;
import hotciv.standard.epsilon.EpsilonFactoryTestStub;

import org.junit.Before;
import org.junit.Test;

public class TestSemiCiv {
	private GameImpl game;

	@Before
	public void setup(){
		game = new GameImpl(new SemiCivFactory());
	}

	@Test
	public void shouldBe3000BCAfter10Rounds() {
		for(int i=0; i<20; i++)
		{
			game.endOfTurn();
		}
		assertEquals("Should be 3000BC after 10 Rounds", -3000, game.getAge());
	}

	@Test
	public void shouldBe100BCAfter39Rounds() {
		for(int i=0; i<78; i++)
		{
			game.endOfTurn();
		}
		assertEquals("Should be 3000BC after 10 Rounds", -100, game.getAge());

	}
	
	@Test
	public void testSettlerAction(){
		Position p = new Position(5,5);
		game.performUnitActionAt(p);
		assertNull("No red settler at 4,3",game.getTileAt(p).getUnit());
		assertEquals("New Red city at 4,3", Player.RED, game.getTileAt(p).getCity().getOwner());
	}
	
	@Test
	public void testArcherCannotFortify(){
		Position p = new Position(3,8);
		game.performUnitActionAt(p);
		assertEquals("Archer's defense should be the same", 3, game.getUnitAt(p).getDefensiveStrength());
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
	
	@Test
	public void shouldBeRedWinAfterThreeSuccessfulAttack() {		
		game = new GameImpl(new EpsilonFactoryTestStub());
		
		Position red1 = new Position(7,7);
		Position red2 = new Position(7,1);
		Position red3 = new Position(7,3);
		Position blue1 = new Position(6,7);
		Position blue2 = new Position(6,1);
		Position blue3 = new Position(6,3);
		
		Unit legionRed1= new Legion(Player.RED);
		Unit legionRed2 = new Legion(Player.RED);
		Unit legionRed3 = new Legion(Player.RED);
		
		Unit legionBlue1 = new Legion(Player.BLUE);
		Unit legionBlue2 = new Legion(Player.BLUE);
		Unit legionBlue3 = new Legion(Player.BLUE);
		
		game.addUnitToTile(legionRed1, red1);
		game.addUnitToTile(legionRed2, red2);
		game.addUnitToTile(legionRed3, red3);
		game.addUnitToTile(legionBlue1, blue1);
		game.addUnitToTile(legionBlue2, blue2);
		game.addUnitToTile(legionBlue3, blue3);
		
		game.attack(red1, blue1);
		assertEquals("red should be on 6,7", Player.RED, game.getUnitAt(blue1).getOwner());
		assertEquals("red should have one win", 1, game.getPlayerWinCount(Player.RED) );
		game.attack(red2, blue2);
		game.attack(red3, blue3);
		game.endOfTurn();
		game.endOfTurn();
		assertEquals("should be red winner", Player.RED, game.getWinner());
	}
	
	
}