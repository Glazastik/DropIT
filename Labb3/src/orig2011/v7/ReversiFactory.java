package orig2011.v7;


/**
 * Factory class for available games.
 */
public class ReversiFactory implements IGameFactory {

	/**
	 * Returns an array with names of games this factory can create. Used by GUI
	 * list availible games.
	 */
	@Override
	public String[] getGameNames() {
		return new String[] {"Reversi", "Gold"};
	}

	/**
	 * Returns a new model object for the game corresponding to its Name.
	 * 
	 * @param gameName
	 *            The name of the game as given by getGameNames()
	 * @throws IllegalArgumentException
	 *             if no such game
	 */
	@Override
	public GameModel createGame(final String gameName) {
		GameModel game;
		
		if (gameName.equals("Reversi")) {
			game = new ReversiModel();
			game.addObserver(new ReversiScoreView());
		} else if (gameName.equals("Gold")) {
			game = new GoldModel();
		} else {
			throw new IllegalArgumentException("No such game: " + gameName);
		}
		
		return game;
		
	}
}
