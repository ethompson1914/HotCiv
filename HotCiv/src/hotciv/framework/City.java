package hotciv.framework;
/** Represents a city in the game.

    Responsibilities:
    1) Knows its owner.
    2) Knows its population size.

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

public interface City {	
	/** return the owner of this city.
	 * @return the player that controls this city.
	 */
	public Player getOwner();

	/** return the size of the population.
	 * @return population size.
	 */
	public int getSize();

	/** return the type of unit this city is currently producing.
	 * @return a string type defining the unit under production,
	 * see GameConstants for valid values.
	 */
	public String getProduction();

	/** return the work force's focus in this city.
	 * @return a string type defining the focus, see GameConstants
	 * for valid return values.
	 */
	public String getWorkforceFocus();

	/**
	 * Getter for the current total production of the calling city
	 * @return int representing the current production of the city
	 */
	public int getTotalProduction();

	/**
	 * Increase total production for a city
	 * this increase is maintained by the city variant
	 */
	void increaseTotalProduction();

	/**
	 * Condition check for the calling city's ability to produce a unit
	 * @return True if the calling city can produce a unit
	 */
	boolean canProduceUnit();

	/**
	 * Changes the calling city's current unit production focus
	 * @param production a unit to change focus of unit to be produced
	 */
	void changeProduction(String production);

	/**
	 * Changes the focus of a city to the specified value
	 * @param focus a string representing the new focus of the city
	 */
	void changeFocus(String focus);

	/**
	 * Sets the owner of the calling city
	 * @param player the new owner of the calling city
	 */
	void setOwner(Player player);
}
