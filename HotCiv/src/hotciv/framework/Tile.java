package hotciv.framework;

import hotciv.standard.CityImpl;

/** Tile represents a single territory tile of a given type.

    Responsibilities:
    1) Know its type and position on the board.

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

public interface Tile {

	/** return position of this tile on the board. 
	 * @return position of tile.
	 */
	public Position getPosition();

	/** return the tile type as a string. The set of
	 * valid strings are defined by the graphics
	 * engine, as they correspond to named image files.
	 * @return the type type as string
	 */
	public String getTypeString();

	/**
	 * Set the city on the calling tile
	 * @param newCity the new city to place on the tile
	 * @return True if successful
	 */
	boolean setCity(City newCity);

	/**
	 * A getter for retrieving the current city on the calling tile
	 * @return the current city on the tile
	 */
	City getCity();

	/**
	 * A getter for retrieving the current unit on the calling tile
	 * @return the current unit on the tile
	 */
	Unit getUnit();

	/**
	 * Setter to change the current unit on the calling tile
	 * @param newUnit the new unit to place on the tile
	 */
	void setUnit(Unit newUnit);

	/**
	 * Remove the current unit from the calling tile
	 */
	void clearUnit(); 

}
