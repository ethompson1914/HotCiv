package hotciv.view;

import hotciv.framework.Game;
import hotciv.standard.GameImpl;
import hotciv.standard.LoggingGameImpl;
import hotciv.standard.delta.FractalDeltaCivFactory;

import javax.swing.JTextField;

import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.DrawingView;
import minidraw.framework.Factory;
import minidraw.standard.MiniDrawApplication;

/** Show a basic world.
 * 
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
public class PlayHotCiv {
	private static LoggingGameImpl game;

	public static void main(String[] args) {
		game = new LoggingGameImpl(new GameImpl(new FractalDeltaCivFactory()));

		DrawingEditor editor = 
				new MiniDrawApplication( "HotCiv",  
						new HotCivFactory(game) );
		editor.open();

		editor.setTool(new HotCivTool(editor, game));
	}
}

class HotCivFactory implements Factory {
	private Game game;
	public HotCivFactory(Game g) { game = g; }

	public DrawingView createDrawingView( DrawingEditor editor ) {
		DrawingView view = 
				new MapView(editor, game);
		return view;
	}

	public Drawing createDrawing( DrawingEditor editor ) {
		return new CivDrawing(editor, game);
	}

	public JTextField createStatusField( DrawingEditor editor) {
		return  new JTextField();
	}
}