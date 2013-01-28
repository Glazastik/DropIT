package se.chalmers.malm.box;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameController implements KeyListener {
	
	private GameModel model;
	
	public GameController(GameModel model, GameView view) {
		this.model = model;
		view.addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		model.keyPressed(e.getKeyChar());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
