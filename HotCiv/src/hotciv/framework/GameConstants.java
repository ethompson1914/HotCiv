package hotciv.framework;


/** Collection of constants used in HotCiv Game. Note that strings are
 * used instead of enumeration types to keep the set of valid
 * constants open to extensions by future HotCiv variants.  Enums can
 * only be changed by compile time modification.

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
public class GameConstants {
	// The size of the world is set permanently to a 16x16 grid 
	public static final int WORLDSIZE = 16;
	// Valid unit types
	public static final String ARCHER    = "archer";
	public static final String LEGION    = "legion";
	public static final String SETTLER   = "settler";
	// Valid terrain types
	public static final String PLAINS    = "plains";
	public static final String OCEANS    = "ocean";
	public static final String FOREST    = "forest";
	public static final String HILLS     = "hills";
	public static final String MOUNTAINS = "mountain";
	public static final String CITY = "city";
	//Strategy interface names
	public static final String WINNING_STRATEGY = "WinningStrategy";
	public static final String AGING_STRATEGY = "AgingStrategy";
	public static final String ACTION_STRATEGY = "ActionStrategy";
	// Valid production balance types
	public static final String productionFocus = "hammer";
	public static final String foodFocus = "apple";
	//Unit costs
	public static int ARCHER_COST = 10;
	public static int LEGION_COST = 15;
	public static int SETTLER_COST = 30;
	//Unit attack strengths
	public static int ARCHER_ATTACK = 2;
	public static int LEGION_ATTACK = 4;
	public static int SETTLER_ATTACK = 0;
	//Unit defense strengths
	public static int ARCHER_DEFENSE = 3;
	public static int LEGION_DEFENSE = 2;
	public static int SETTLER_DEFENSE = 3;
	
	public static String getNextUnitType(String s) {
		if (s.equals(ARCHER)) return LEGION;
		else if (s.equals(LEGION)) return SETTLER;
		else if (s.equals(SETTLER)) return ARCHER;
		return "";
	}
	
	private GameConstants(){}
	
}
