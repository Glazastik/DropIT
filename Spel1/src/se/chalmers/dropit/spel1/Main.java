/**
 * @author Anton Myrholm
 * @author Pontus Mlam
 */
package se.chalmers.dropit.spel1;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Main extends StateBasedGame {

	private static final String GAMENAME = "Spel 1 - test";
	private static final int GAMEWINDOW_X = 800;
	private static final int GAMEWINDOW_Y = 600;
	private static final int MENU = 0;
	private static final int GAMEPLAY = 1;
	private static final boolean FULLSCREENMODE = false;

	public Main(String name) {
		super(name);
		// TODO Auto-generated constructor stub
//		this.addState(new Menu(MENU));
		this.addState(new GamePlay(GAMEPLAY));
	}

	/**
	 * The main method, it generates the game container window and starts the game in in.
	 * @param args
	 */
	public static void main(String[] args) {
		AppGameContainer appGameContainer;

		try {
			appGameContainer = new AppGameContainer(new Main(GAMENAME));
			appGameContainer.setDisplayMode(GAMEWINDOW_X, GAMEWINDOW_Y, FULLSCREENMODE);
			appGameContainer.start();

		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initiates the States needed for the game.
	 * @param gameContainer
	 */
	@Override
	public void initStatesList(GameContainer gameContainer) throws SlickException {
//		this.getState(MENU).init(gameContainer, this);
		this.getState(GAMEPLAY).init(gameContainer, this);
		
		this.enterState(GAMEPLAY);
	}

}
