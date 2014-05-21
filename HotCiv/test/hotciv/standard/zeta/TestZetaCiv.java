package hotciv.standard.zeta;

import static org.junit.Assert.*;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.standard.GameImpl;
import hotciv.standard.Legion;
import hotciv.standard.gamma.GammaCivFactory;
import hotciv.standard.zeta.ZetaCivFactory;

import org.junit.Before;
import org.junit.Test;

public class TestZetaCiv {
	
	GameImpl game;
	
	@Before
	public void setUp() {		
		game = new GameImpl(new ZetaCivFactory());
	}
	
	@Test
	public void BlueWinsAfterFirstRound() {
		Position blueLegionPosition = new Position(1,2);
		Unit legion1= new Legion(Player.BLUE);
		game.addUnitToTile(legion1, blueLegionPosition);
		game.endOfTurn();
		Position cityPos = new Position(1,1);
		game.moveUnit(blueLegionPosition, cityPos);
		game.endOfTurn();
		assertEquals("Blue player is the winner", Player.BLUE, game.getWinner());
	}

	@Test
	public void RedWinsAfterFirstRound(){
		Position redLegionPosition = new Position(4,0);
		Unit legion1= new Legion(Player.RED);
		game.addUnitToTile(legion1, redLegionPosition);
		Position cityPos = new Position(4,1);
		game.moveUnit(redLegionPosition, cityPos);
		game.endOfTurn();
		game.endOfTurn();
		assertEquals("Red player is the winner", Player.RED, game.getWinner());
	}
	
	@Test
	public void RedDoesNotWinAfter3Attacks(){

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
		game.attack(red2, blue2);
		game.attack(red3, blue3);
		game.endOfTurn();
		game.endOfTurn();
		assertNull("Red does not win yet", game.getWinner());
	}
	
	@Test
	public void RedDoesWinAfter3AttacksAtRound20(){
		game = new GameImpl(new ZetaCiv20RoundsFactoryTestStub());
		game.endOfTurn();
		game.endOfTurn();
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
		game.attack(red2, blue2);
		game.attack(red3, blue3);
		game.endOfTurn();
		game.endOfTurn();
		assertEquals("Red player should be winner", Player.RED, game.getWinner());
	}
	
	@Test
	public void RedDoesNotWinForCapturedCityAfterRound20(){
		game = new GameImpl(new ZetaCiv20RoundsFactoryTestStub());
		game.endOfTurn();
		game.endOfTurn();
		
		Position redLegionPosition = new Position(4,0);
		Unit legion1= new Legion(Player.RED);
		game.addUnitToTile(legion1, redLegionPosition);
		Position cityPos = new Position(4,1);
		game.moveUnit(redLegionPosition, cityPos);
		game.endOfTurn();
		game.endOfTurn();
		assertNull("Red player does not win for capturing city", game.getWinner());
	}
	
	@Test
	public void RedDoesNotWinFor3AttacksBeforeRound20(){
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
		game.attack(red2, blue2);
		game.attack(red3, blue3);
		game.endOfTurn();
		game.endOfTurn();
		assertNull("Red player does not win for winning 3 attacks", game.getWinner());
	}
}

