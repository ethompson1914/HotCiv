package hotciv.framework;

public interface AttackingStrategy {


	/**
	 *  This method carries out and determines the winning unit after an attack
	 * @param gs The current game
	 * @param a attacking unit
	 * @param d defending unit
	 * @param attackingSupport an integer representing the friendly unit contribution around the attacking unit
	 * @param attackingTerrainFactor an integer representing the contribution to the attacking unit from the terrain where the unit currently resides
	 * @param defendingSupport an integer representing the friendly unit contribution around the defending unit
	 * @param defendingTerrainFactor
	 * @return True if the attack is successful false otherwise
	 */
	public boolean attackSuccessful(GameState gs, Unit a, Unit d, int attackingSupport, int attackingTerrainFactor, int defendingSupport, int defendingTerrainFactor);
}
