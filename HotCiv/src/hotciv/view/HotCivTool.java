package hotciv.view;

import hotciv.framework.City;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.standard.LoggingGameImpl;
import hotciv.standard.PrintLogState;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import minidraw.framework.DrawingEditor;
import minidraw.standard.SelectionTool;

public class HotCivTool extends SelectionTool {
	private LoggingGameImpl game;
	private Position mouseDownPosition;
	
	public HotCivTool(DrawingEditor editor, LoggingGameImpl game) {
		super(editor);
		
		this.game = game;
	}
	
	public void mouseDown(MouseEvent e, int x, int y) {
		Position temp = GfxConstants.getPositionFromXY(x, y);
		mouseDownPosition = temp;
		
		if (!mouseDownPosition.isValid()) {
			mouseDownPosition = null;
			
			if (GfxConstants.TURN_SHIELD_X <= x && x <= GfxConstants.TURN_SHIELD_X + 30 &&
					GfxConstants.TURN_SHIELD_Y <= y && y <= GfxConstants.TURN_SHIELD_Y + 30) {
				game.endOfTurn();
			}
		} else {
			game.selectTile(temp);
			
			Unit unit = game.getUnitAt(temp);
			if (unit == null) {
				mouseDownPosition = null;
			} else {
				super.mouseDown(e, x, y);
				if (e.getButton() == e.BUTTON3) {
					game.performUnitActionAt(temp);
					mouseDownPosition = null;
				}
			}
			
			City city = game.getCityAt(temp);
			if (city != null && e.getButton() == e.BUTTON3) {
				game.changeProductionInCityAt(temp, GameConstants.getNextUnitType(city.getProduction()));
			}
		}
	}
	
	public void mouseUp(MouseEvent e, int x, int y) {
		super.mouseUp(e, x, y);
		
		if (mouseDownPosition != null) {
			Position to = GfxConstants.getPositionFromXY(x, y);
			if (game.getUnitAt(to) != null) {
				game.attack(mouseDownPosition, to);
			} else {
				game.moveUnit(mouseDownPosition, to);
			}
		}
		
		if (game.getLoggingState() instanceof PrintLogState)
			editor.showStatus(game.getLastMessage());
		
		mouseDownPosition = null;
	}
	
	public void keyDown(KeyEvent e, int arg1) {
		if (e.getKeyChar() == 'l') {
			// turn on/off the logging
			game.switchLoggingState();
			editor.showStatus("");
		}
	}
}