package se.chalmers.dropit.spel1;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends BasicGameState {

	private final int ID = 0;
	private Image titleImage;
	private Image titleSelector;
	private final float SCALE = 3f;
	private float selectorOffset = 0;

	public Menu(int state) {

	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		titleImage = new Image("se/chalmers/dropit/spel1/res/title.png");
		titleSelector = new Image(
				"se/chalmers/dropit/spel1/res/titleselect.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		titleImage.draw(gc.getWidth() / 2 - titleImage.getWidth() / 2 * SCALE,
				gc.getHeight() / 2 - titleImage.getHeight() / 2 * SCALE, SCALE);
		titleSelector.draw((gc.getWidth() / 2 - titleImage.getWidth() / 2
				* SCALE)
				+ titleImage.getWidth() * 0.18f * SCALE,
				(gc.getHeight() / 2 - titleImage.getHeight() / 2 * SCALE)
						+ titleImage.getHeight() * 0.78f * SCALE
						+ selectorOffset * SCALE, SCALE);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delte)
			throws SlickException {
		Input input = gc.getInput();
		if (input.isKeyDown(Input.KEY_DOWN)) {
			selectorOffset = 15f;
		} else if (input.isKeyDown(Input.KEY_UP)) {
			selectorOffset = 0;
		} else if (input.isKeyDown(Input.KEY_SPACE)
				|| input.isKeyDown(Input.KEY_ENTER)) {
			if (selectorOffset != 15) {
				sbg.enterState(1);
			} else {
				System.exit(0);
			}
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

}
