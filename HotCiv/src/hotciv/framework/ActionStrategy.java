package hotciv.framework;

public interface ActionStrategy {

	 /**
	  * Peform the proper unit action for a specified unit on a given tile
	  * @param gs The current game
	  * @param p  The position to perform the action
	  */
	 void performAction(GameState gs, Position p);

}
