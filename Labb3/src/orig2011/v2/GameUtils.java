package orig2011.v2;

/**
 * A class to be used in a static way for handling the games.
 * 
 * @author Anton Myrholm
 * @author Pontus Malm
 * 
 */

public class GameUtils {

	/**
	 * Set the tile on a specified position in the gameboard.
	 * 
	 * @param pos
	 *            The position in the gameboard matrix.
	 * @param tile
	 *            The type of tile to paint in specified position
	 */
	public static void setGameboardState(final Position pos,
			final GameTile tile, GameTile[][] board) {
		board[pos.getX()][pos.getY()] = tile;
	}

}
