package  hotciv.standard;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.standard.alpha.AlphaCivFactory;

import org.junit.Before;
import org.junit.Test;


/** Skeleton class for AlphaCiv test cases 

   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Computer Science Department
     Aarhus University

   This source code is provided WITHOUT ANY WARRANTY either 
   expressed or implied. You may study, use, modify, and 
   distribute it for non-commercial purposes. For any 
   commercial use, see http://www.baerbak.com/
 */
public class TestAlphaCiv {
	private GameImpl game;
	/** Fixture for alphaciv testing. */
	@Before
	public void setUp() {
		game = new GameImpl(new AlphaCivFactory());
	}

	@Test
	public void shouldGetTileAt1_1() {
		Position p = new Position(1, 0);
		assertNotNull("There is a tile at location (1,1)", game.getTileAt(p));
	}

	@Test
	public void shouldGetOceanTileAt1_1() {
		Position p = new Position(1, 0);	
		assertEquals("Tile at 1,1 should be ocean",game.getTileAt(p).getTypeString(),GameConstants.OCEANS);
	}

	@Test
	public void shouldGetHillTileAt0_1() {
		Position p = new Position(0, 1);	
		assertEquals("Tile at 0,1 should be hill",game.getTileAt(p).getTypeString(),GameConstants.HILLS);
	}

	@Test
	public void shouldGetRedCityTileAt1_1() {
		Position p = new Position(1,1);	  
		assertNotNull("There is a city at location (1,1)", game.getCityAt(p));
		assertEquals("Tile at 1,1 should be red city",game.getCityAt(p).getOwner(),Player.RED);
	}

	@Test
	public void shouldGetBlueCityTileAt4_1() {
		Position p = new Position(4, 1);	
		assertNotNull("There is a city at location (4,1)", game.getCityAt(p));
		assertEquals("Tile at 4,1 should be blue city",game.getCityAt(p).getOwner(),Player.BLUE);
	}

	@Test
	public void shouldGetMountainTileAt2_2() {
		Position p = new Position(2, 2);	
		assertEquals("Tile at 2,2 should be mountain",game.getTileAt(p).getTypeString(),GameConstants.MOUNTAINS);
	}

	@Test
	public void shouldBeRedTurnAtBeginning() {
		assertEquals("The red player starts the game",game.getPlayerInTurn(),Player.RED);
	}

	@Test
	public void shouldBeBlueTurnAfterRedDone() {
		game.endOfTurn();
		assertEquals("The blue player should go after the red", game.getPlayerInTurn(), Player.BLUE);  
	}

	@Test
	public void shouldBeAbleToMoveUnitAt2_0to2_1() {
		Position p = new Position(2,0);
		Position toP = new Position(2,1);
		game.moveUnit(p, toP);
		assertEquals("There is an archer at 2,1", game.getTileAt(toP).getUnit().getTypeString(), GameConstants.ARCHER);
	}

	@Test
	public void shouldNotBeAbletoMoveUnitAt2_0To1_0(){
		Position p = new Position(2,0);
		Position toP = new Position(1,0);
		assertFalse("Unit should not be able to move from 2,0 to 1,0)",game.moveUnit(p, toP));	  
	}

	@Test
	public void shouldNotBeAbleToMoveUnitAt2_0To4_0(){
		Position p = new Position(2,0);
		Position toP = new Position(4,0);
		assertFalse("Unit should not be able to move from 2,0 to 4,0)", game.moveUnit(p, toP));	
	}

	@Test
	public void shouldBeAbleToMoveUnitAt2_0to3_1(){
		Position p = new Position(2,0);
		Position toP = new Position(3,1);
		assertTrue("Unit should be able to move from 2,0 to 3,1", game.moveUnit(p, toP));
	}

	@Test
	public void shouldBeAbleToWinBattleRedArcherAgainstBlueLegion(){
		Position p = new Position(2,0);
		Position toP = new Position(3,0);
		Position blueLegionPosition = new Position(3,0);
		Unit legion = new Legion(Player.BLUE);
		game.addUnitToTile(legion, blueLegionPosition);
		assertTrue("Red Archer should win against Blue Legion", game.attack(p, toP));
		assertEquals("There is red at 3,0", GameConstants.ARCHER, game.getTileAt(toP).getUnit().getTypeString());
	}

	@Test
	public void shouldNotBeAbleToAttackOwnPlayerUnit(){	  
		Position p = new Position(2,0);
		Position toP = new Position(2,1);
		Position toArcherPosition = new Position(2,1);	
		Unit archer2= new Archer(Player.RED);	
		game.addUnitToTile(archer2, toArcherPosition);
		assertFalse("Red Archer should not be able to attack or move to fellow red unit.", game.moveUnit(p, toP));	  
	}

	//@Test TODO 
	public void shouldBeAbleToHaveArcherPerformAction(){
		//holding off for future testing. 
	}


	@Test
	public void shouldNotBeAbleToMoveBlueUnitWhenRedCurrentPlayer(){
		Position from = new Position(3,0);
		Position to = new Position(4,0);
		Position blueLegionPosition = new Position(3,0);
		Unit legion = new Legion(Player.BLUE);
		game.addUnitToTile(legion, blueLegionPosition);
		assertFalse("Red player should not be able to move blue units.", game.moveUnit(from, to));
	}

	@Test
	public void shouldBeAbleToIncreaseProductionFrom0t6AtEndOfRound(){
		Position RedCity = new Position(1,1);
		game.endOfTurn();
		game.endOfTurn();
		assertEquals("Red City's current total production after one round is 6",6,game.getCityAt(RedCity).getTotalProduction());
	}  

	@Test
	public void shouldBeAbleToProduceArcherOnCityTileAt1_1(){
		Position RedCity = new Position(1,1);
		game.endOfTurn();
		game.endOfTurn();
		game.endOfTurn();
		game.endOfTurn();
		assertEquals("An red archer should be created at 1,1 after 2 rounds.", Player.RED, game.getUnitAt(RedCity).getOwner());
		assertEquals("An red archer should be created at 1,1 after 2 rounds.", GameConstants.ARCHER , game.getUnitAt(RedCity).getTypeString());
	}

	@Test
	public void shouldBeProductionFocusOfWorkForceForCityAt1_1(){
		Position RedCity = new Position(1,1);
		assertEquals("Red city production focus should be production.", GameConstants.productionFocus, game.getTileAt(RedCity).getCity().getWorkforceFocus());	  
	}

	@Test 
	public void shouldBeYear4000BCAtBeginning(){
		assertEquals("Beginning year is 4000BC", -4000, game.getAge());
	}

	@Test
	public void shouldBeRedVictoryAt3000BC(){
		for(int x = 0; x<20;x++){
			game.endOfTurn();
		}
		assertEquals("Red should be winner at the 3000BC", Player.RED, game.getWinner());
	}

	@Test
	public void shouldChangeProductionFocusToSettlerAtCity1_1(){
		Position RedCity = new Position(1,1);
		assertEquals("Default production is archer",GameConstants.ARCHER, game.getTileAt(RedCity).getCity().getProduction() );
		game.changeProductionInCityAt(RedCity, GameConstants.SETTLER);
		assertEquals("The new production should be settler",GameConstants.SETTLER, game.getTileAt(RedCity).getCity().getProduction() );
	}

	@Test
	public void  shouldChangeProductionFocusFromToLegionAtCity1_1(){
		Position RedCity = new Position(1,1);
		assertEquals("Default production is archer",GameConstants.ARCHER, game.getTileAt(RedCity).getCity().getProduction() );
		game.changeProductionInCityAt(RedCity, GameConstants.LEGION);
		assertEquals("The new production should be Legion",GameConstants.LEGION, game.getTileAt(RedCity).getCity().getProduction() );
	}

	@Test
	public void shouldChangeProductionFocusToSettlerAtCity4_1(){
		Position RedCity = new Position(4,1);
		game.endOfTurn();
		assertEquals("Default production is archer",GameConstants.ARCHER, game.getTileAt(RedCity).getCity().getProduction() );
		game.changeProductionInCityAt(RedCity, GameConstants.SETTLER);
		assertEquals("The new production should be Legion",GameConstants.SETTLER, game.getTileAt(RedCity).getCity().getProduction() ); 
	}


	@Test
	public void shouldChangeProductionFocusToLegionAtCity4_1(){
		Position RedCity = new Position(4,1);
		game.endOfTurn();
		assertEquals("Default production is archer",GameConstants.ARCHER, game.getTileAt(RedCity).getCity().getProduction() );
		game.changeProductionInCityAt(RedCity, GameConstants.LEGION);
		assertEquals("The new production should be Legion",GameConstants.LEGION, game.getTileAt(RedCity).getCity().getProduction() );
	}

	@Test 
	public void shouldBeAbleToChangeWorkFocusAtRedCity(){
		Position RedCity = new Position(1,1);

		assertEquals("Default work focus is hammer",GameConstants.productionFocus, game.getTileAt(RedCity).getCity().getWorkforceFocus());
		game.changeWorkForceFocusInCityAt(RedCity, GameConstants.foodFocus);
		game.endOfTurn();
		assertEquals("The new focus should be apple",GameConstants.foodFocus, game.getTileAt(RedCity).getCity().getWorkforceFocus() );
	}

	@Test
	public void shouldNotBeAbleToMoveUnitOffBoardFrom20to2negative1(){
		Position redUnit =  new Position(2,0);
		Position to = new Position(2,-1);
		assertFalse("Should not be able to move unit off the board", game.moveUnit(redUnit, to));
	}

	@Test
	public void  shouldNotBeAbleToMoveUnitOffBoardFrom1515to1616(){
		Position redUnit = new Position(15,15);
		Position to = new Position(16,16);	
		Unit archer2= new Archer(Player.RED);		 	 
		game.addUnitToTile(archer2, redUnit);
		assertFalse("Should not be able to move unit off the board", game.moveUnit(redUnit, to));

	}

	@Test
	public void shouldDecreaseMoveCountFrom1to0AfterRedArcherMoves(){
		Position p = new Position(2,0);
		Position toP = new Position(3,0);
		game.moveUnit(p, toP);
		assertEquals("Red archer at 2,1 should have moveCount of 0",  0,game.getTileAt(toP).getUnit().getMoveCount());
	}

	@Test
	public void shouldDecreaseMoveCountFrom1to0AfterBlueLegionMoves(){
		Position p = new Position(3,2);
		Position toP = new Position(3,3);
		game.endOfTurn();
		game.moveUnit(p, toP);
		assertEquals("Blue Legion at 3,2 should have moveCount of 0",  0,game.getTileAt(toP).getUnit().getMoveCount());
	}

	@Test
	public void shouldHaveResetMoveCountForRedArcherTo1AfterRound(){
		Position p = new Position(2,0);
		Position toP = new Position(3,0);
		game.moveUnit(p, toP);
		game.endOfTurn();
		game.endOfTurn();
		assertEquals("Red archer at 3,0 should have moveCount of 1", 1, game.getTileAt(toP).getUnit().getMoveCount());
	}

	@Test
	public void shouldHaveResetMoveCountForBlueLegionTo1AfterRound(){
		Position p = new Position(3,2);
		Position toP = new Position(3,3);
		game.endOfTurn();
		game.moveUnit(p, toP);
		game.endOfTurn();
		assertEquals("Red Player turn", Player.RED,game.getPlayerInTurn());
		assertEquals("Blue Legion at 3,3 should have moveCount of 1",  1, game.getTileAt(toP).getUnit().getMoveCount());
	}

	@Test
	public void shouldNotBeAbleToMoveRedArcherAfterMoveCountIsZero(){
		Position p = new Position(2,0);
		Position toP = new Position(3,0);
		game.moveUnit(p, toP);
		assertFalse("Red archer at 2,1 should have moveCount of 0", game.moveUnit(toP,p));
	}

	@Test
	public void shouldCreateUnitAtNorthTileIfRedCityOccupied(){
		Position redCity = new Position(1,1);
		Position newArcher = new Position(0,1);
		for(int x=0; x<8;x++){
			game.endOfTurn();
		}
		assertEquals("An red archer should be created at 1,1 after 2 rounds.", GameConstants.ARCHER , game.getUnitAt(redCity).getTypeString());
		assertEquals("An red archer should be created at 1,1 after 2 rounds.", GameConstants.ARCHER , game.getUnitAt(newArcher).getTypeString());

	}

	@Test
	public void shouldCreateUnitAtNorthWestTileIfAllOtherTilesAreOccupied(){
		Position redCity = new Position(1,1);
		Position newArcher = new Position(0,0);
		Position toArcherPosition = new Position(2,1);	
		Unit archer2= new Archer(Player.RED);	
		game.addUnitToTile(archer2, toArcherPosition);
		for(int x=0; x<18;x++){
			game.endOfTurn();
		}
		assertEquals("An red archer should be created at 1,1 after 2 rounds.", GameConstants.ARCHER , game.getUnitAt(redCity).getTypeString());
		assertEquals("An red archer should be created at 0,0 after 9 rounds.", GameConstants.ARCHER , game.getUnitAt(newArcher).getTypeString());
	}

	@Test 
	public void shouldCreateUnitAtNorthEastTileIfCityAndNorthOccupied(){
		Position redCity = new Position(1,1);
		Position newArcher = new Position(0,2);
		for(int x=0; x<12;x++){
			game.endOfTurn();
		}
		assertEquals("An red archer should be created at 1,1 after 2 rounds.", GameConstants.ARCHER , game.getUnitAt(redCity).getTypeString());
		assertEquals("An red archer should be created at 0,2 after 6 rounds.", GameConstants.ARCHER , game.getUnitAt(newArcher).getTypeString());
	}

	@Test
	public void shouldNotCreateUnitOnOceanAt10(){
		Position redCity = new Position(1,1);
		Position ocean = new Position(1,0);
		for(int x=0; x<18;x++){
			game.endOfTurn();
		}
		assertEquals("An red archer should be created at 1,1 after 2 rounds.", GameConstants.ARCHER , game.getUnitAt(redCity).getTypeString());
		assertNull("An red archer should not of be created at 1,0 after 9 rounds.",  game.getUnitAt(ocean));

	}
}  