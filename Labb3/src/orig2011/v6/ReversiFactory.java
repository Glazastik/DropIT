package orig2011.v6;


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
		if (gameName.equals("Reversi")) {
			return new ReversiModel();
		}
		if (gameName.equals("Gold")) {
			return new GoldModel();
		}

		throw new IllegalArgumentException("No such game: " + gameName);
	}
}
