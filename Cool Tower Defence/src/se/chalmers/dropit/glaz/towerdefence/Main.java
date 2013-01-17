package se.chalmers.dropit.glaz.towerdefence;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame{
	private static final String GAMENAME = "Tower Defence!";
	private static final int GAMEWINDOW_X = 800;
	private static final int GAMEWINDOW_Y = 600;
	private static final int MENU = 0;
	private static final int GAME = 1;
	private static final boolean FULLSCREENMODE = false;

	public Main(String name) {
		super(name);
		// TODO Auto-generated constructor stub
		this.addState(new Game(GAME));
//		this.addState(new GamePlay(GAMEPLAY));
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
			appGameContainer.setTargetFrameRate(200);
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
		this.getState(GAME).init(gameContainer, this);
//		
		//Enters the first state
		this.enterState(GAME);
	}
}	
