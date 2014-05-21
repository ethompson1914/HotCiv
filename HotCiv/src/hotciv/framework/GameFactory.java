package hotciv.framework;


public interface GameFactory {
	
	/**
	 * A factory method for creating a new ActionStrategy
	 * @return a new ActionStrategy for the calling factory
	 */
	public ActionStrategy createActionStrategy();
	

	/**
	 * A factory method for creating a new WinningStrategy
	 * @return a new WinningStrategy for the calling factory
	 */
	public WinningStrategy createWinStrategy();
	

	/**
	 * A factory method for creating a new AgingStrategy
	 * @return a new AgingStrategy for the calling factory
	 */
	public AgingStrategy createAgeStrategy();
	
	/**
	 * A factory method for retrieving the variation's initial terrain layout
	 * @return the variation's terrain layout 
	 */
	public String getTerrainLayout();
	
	/**
	 * A factory method for retrieving the variation's initial city layout
	 * @return the variation's terrain layout 
	 */
	public String getCityLayout();
	
	/**
	 * A factory method for retrieving the variation's initial unit layout
	 * @return the variation's terrain layout 
	 */
	public String getUnitLayout();
	
	/**
	 * A factory method for creating a new AttackingStrategy
	 * @return a new AttackingStrategy for the calling factory
	 */
	public AttackingStrategy createAttackingStrategy();
}
