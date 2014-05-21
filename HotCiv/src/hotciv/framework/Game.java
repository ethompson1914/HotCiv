package hotciv.framework;

/** Game is the central interface allowing a client to access and
 * modify the state of a HotCiv game.  

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

public interface Game extends GameState {
	

	// === Mutator methods ======================================

	/** move a unit from one position to another. If that other position
	 * is occupied by an opponent unit, a battle is conducted leading to
	 * either victory or defeat. If victorious then the opponent unit is
	 * removed from the game and the move conducted. If defeated then
	 * the attacking unit is removed from the game. If a successful move
	 * results in the unit entering the position of a city then this
	 * city becomes owned by the owner of the moving unit.
	 * Precondition: both from and to are within the limits of the
	 * world.  Precondition: there is a unit located at position from.
	 * @param from the position that the unit has now
	 * @param to the position the unit should move to
	 * @return true if the move is valid (no mountain, move is valid
	 * under the rules of the game variant etc.), and false
	 * otherwise. If false is returned, the unit stays in the same
	 * position and its "move" is intact (it can be moved to another
	 * position.)
	 */
	public boolean moveUnit( Position from, Position to );

	/** Tell the game that the current player has
	 * finished his/her turn. The next player is then 
	 * in turn. If all players have had their turns
	 * then do end-of-round processing:
	 * A) restore all units' move counts
	 * B) produce food and production in all cities
	 * C) produce units in all cities (if enough production)
	 * D) increase population size in all cities (if enough food)
	 * E) increment the world age.
	 */
	public void endOfTurn();

	/** change the work force's focus in a city, i.e. what
	 * kind of production there will be emphasis on in the city.
	 * Precondition: there is a city at location 'p'.
	 * @param p the position of the city whose focus 
	 * should be changed. 
	 * @param balance a string defining the focus of the work
	 * force in a city. Valid values are at least 
	 * GameConstants.productionFocus and 
	 * GameConstants.foodFocus.
	 */
	public void changeWorkForceFocusInCityAt( Position p, String balance );

	/** change the type of unit a city will produce next. 
	 * Precondition: there is a city at location 'p'.
	 * @param p the position of the city whose production
	 * should be changed. 
	 * @param unitType a string defining the type of unit that the
	 * city should produce next.
	 */
	public void changeProductionInCityAt( Position p, String unitType );

	/** perform the action associated with the unit at position p.
	 * Example: a settler unit may create a new city at its location.
	 * Precondition: there is a unit at location 'p'.
	 * @param p the position of a unit that must perform its action.
	 * Nothing happens in case the unit has no associated action.
	 */
	public void performUnitActionAt( Position p );  
	
	/** calculate and perform an attack onto a defender from an attacker. 
	 * 
	 * @param attacker the position of the attacking unit
	 * @param defender the position of the defending unit
	 * @return if the attack was successful
	 */
	public boolean attack(Position attacker, Position defender);
	
	/** puts the unit onto the game board
	 * 
	 * @param unit the unit being put onto the board
	 * @param p the position of the tile
	 */
	public void addUnitToTile(Unit unit, Position p);
	
	/**
	 * add an observer to the game
	 */
	public void addObserver(GameObserver observer);
	
	/**
	 * select a tile at the given position
	 */
	public Tile selectTile(Position p);
}
