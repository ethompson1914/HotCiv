package hotciv.framework;

public interface AgingStrategy {
	
	/**
	 * Calculates the age of the current game state
	 * @param age the current age of the game
	 * @return int that indicates the new age
	 */
	int calculateAge(int age);

}
