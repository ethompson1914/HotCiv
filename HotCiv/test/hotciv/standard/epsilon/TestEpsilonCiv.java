package hotciv.standard.epsilon;

import static org.junit.Assert.*;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.standard.Archer;
import hotciv.standard.GameImpl;
import hotciv.standard.Legion;
import hotciv.standard.epsilon.EpsilonCivFactory;

import org.junit.Before;
import org.junit.Test;

public class TestEpsilonCiv {
	
private GameImpl game;
	
	@Before
	public void setUp() {
		
		game = new GameImpl(new EpsilonFactoryTestStub());
	}
	
	
	
	@Test
	public void shouldBeRedWinAfterThreeSuccessfulAttack() {		
	
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
	
	@Test
	public void shouldBeRedLegionWin(){
		
		Position red1 = new Position(7,5);
		Position blue1 = new Position(6,5);
		
		Unit legionRed1= new Legion(Player.RED);
		Unit legionBlue1 = new Legion(Player.BLUE);
		game.addUnitToTile(legionRed1, red1);
		game.addUnitToTile(legionBlue1, blue1);
		
		game.attack(red1, blue1);
		assertEquals("should be red legion at 6,5", Player.RED, game.getUnitAt(blue1).getOwner());
		
	}
	
	@Test 
	public void shouldBeDefenderWin(){
		
		Position red1 = new Position(7,5);
		Position blue1 = new Position(6,5);
		
		Unit archerRed1= new Archer(Player.RED);
		Unit legionBlue1 = new Archer(Player.BLUE);
		game.addUnitToTile(archerRed1, red1);
		game.addUnitToTile(legionBlue1, blue1);
		
		game.endOfTurn();
		game.attack(blue1, red1);
		assertNull("should not have blue legion at 6,5", game.getUnitAt(blue1));
		assertEquals("should be red archer at 7,5", Player.RED, game.getUnitAt(red1).getOwner());
	}
	
	@Test
	public void shouldBeRedWinAfterThreeSuccessfulAttackWithSupportingUnit() {
		
	
		Position red1 = new Position(7,5);
		Position red2 = new Position(7,6);
		Position red3 = new Position(7,7);
		Position blue1 = new Position(6,5);
		Position blue2 = new Position(6,6);
		Position blue3 = new Position(6,7);
		
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
		//assertEquals("red should have one win", 1, )
		game.attack(red2, blue2);
		game.attack(red3, blue3);
		game.endOfTurn();
		game.endOfTurn();
		assertEquals("should be red winner", Player.RED, game.getWinner());
	}
	
	@Test 
	public void shouldBeLegionWinFromHillAgainstFortifiedArcher(){
		
		Position red1 = new Position(0,1);
		Position blue1 = new Position(0,0);
		Unit archerRed1 = new Archer(Player.RED);
		Unit archerBlue1 = new Archer(Player.BLUE);
		game.addUnitToTile(archerRed1, red1);
		game.addUnitToTile(archerBlue1, blue1);
		game.attack(red1, blue1);
		assertEquals("should be red archer at 0,0", Player.RED, game.getUnitAt(blue1).getOwner());
	}
	
	@Test 
	public void shouldBeLegionWinWithSupportAgainstFortifiedArcher(){
		
		Position blue1 = new Position(7,5);
		Position blue2 = new Position(7,6);
		Position blue3 = new Position(7,7);
		Position blue4 = new Position(6,5);
		Position blue5 = new Position(6,6);
		Position blue6 = new Position(6,7);
		Position red1 = new Position(5,6);
		Unit legionBlue1= new Legion(Player.BLUE);
		Unit legionBlue2 = new Legion(Player.BLUE);
		Unit legionBlue3 = new Legion(Player.BLUE);
		Unit legionBlue4 = new Legion(Player.BLUE);
		Unit legionBlue5 = new Legion(Player.BLUE);
		Unit legionBlue6 = new Legion(Player.BLUE);
		Unit archerRed1 = new Archer(Player.RED);
		game.addUnitToTile(legionBlue1, blue1);
		game.addUnitToTile(legionBlue2, blue2);
		game.addUnitToTile(legionBlue3, blue3);
		game.addUnitToTile(legionBlue4, blue4);
		game.addUnitToTile(legionBlue5, blue5);
		game.addUnitToTile(legionBlue6, blue6);
		game.addUnitToTile(archerRed1, red1);

		game.endOfTurn();
		game.attack(blue5, red1);
		assertNull("should not have blue legion at 6,5", game.getUnitAt(blue5));
		assertEquals("should be blue legion at 5,6", Player.BLUE, game.getUnitAt(red1).getOwner());
	}
}
