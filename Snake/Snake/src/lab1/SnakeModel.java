package lab1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

/**
 * The super awesome game Snake 2! You play as the fast growing and hungry snake.
 * <p>
 * Now includes round apples and walls with a teleportation feature!
 * The snake grows with one tile for every apple it eats.
 */
public class SnakeModel extends GameModel {
	public enum Directions {
		EAST(1, 0), WEST(-1, 0), NORTH(0, -1), SOUTH(0, 1), NONE(0, 0);

		private final int xDelta;
		private final int yDelta;

		Directions(final int xDelta, final int yDelta) {
			this.xDelta = xDelta;
			this.yDelta = yDelta;
		}

		public int getXDelta() {
			return this.xDelta;
		}

		public int getYDelta() {
			return this.yDelta;
		}
	}

	/*
	 * The following GameTile objects are used only to describe how to paint the
	 * specified item.
	 * 
	 * This means that they should only be used in conjunction with the
	 * get/setGameboardState() methods.
	 */

	/** Graphical representation of an apple. */
	private static GameTile APPLE_TILE = new AppleTile(0);

	/** Graphical representation of the snake parts */
	private static final GameTile SNAKE_TILE = new RectangularTile(new Color(30, 120, 30));

	/** Graphical representation of a blank tile. */
	private static final GameTile BLANK_TILE = new RectangularTile(new Color(80, 200, 80));

	/** A list containing the positions of all snake parts. */
	private final LinkedList<Position> snakeParts = new LinkedList<Position>();
	/*
	 * The declaration and object creation above uses the new language feature
	 * 'generic types'. It can be declared in the old way like this: private
	 * java.util.List coins = new ArrayList(); This will however result in a
	 * warning at compilation "Position" in this case is the type of the objects
	 * that are going to be used in the List
	 */

	/** The position of the delicious apple. */
	// private Position collectorPos;
	private Position apple;

	/** The direction of the snake. */
	private Directions direction = Directions.NORTH;

	/** The number of apples eaten. */
	private int score;

	private boolean gameOver = false;

	/**
	 * Create a new model for the snake game.
	 */
	public SnakeModel() {
		score = 0;
		Dimension size = getGameboardSize();

		// Blank out the whole gameboard
		for (int i = 0; i < size.width; i++) {
			for (int j = 0; j < size.height; j++) {
				setGameboardState(i, j, BLANK_TILE);
			}
		}

		// Insert the snake in the middle of the gameboard.
		snakeParts.add(new Position(size.width / 2, size.height / 2));
		setGameboardState(snakeParts.get(0), SNAKE_TILE);

		// Insert coins into the gameboard.
		addApple();
	}

	/**
	 * Insert another apple into the gameboard.
	 */
	private void addApple() {
		Dimension size = getGameboardSize();

		// Loop until a blank position is found and ...
		do {
		apple = new Position((int) (Math.random() * size.width),
				(int) (Math.random() * size.height));
		} while (snakeParts.contains(apple));
		
		APPLE_TILE = new AppleTile(score);

		// ... add a new apple to an empty tile.
		setGameboardState(apple, APPLE_TILE);
	}

//	/**
//	 * Return whether the specified position is empty.
//	 * 
//	 * @param pos
//	 *            The position to test.
//	 * @return true if position is empty.
//	 */
//	private boolean isPositionEmpty(final Position pos) {
//		return (getGameboardState(pos) == BLANK_TILE);
//	}

	/**
	 * Update the direction of the snake according to the user's keypress.
	 */
	private void updateDirection(final int key) {
		switch (key) {
		case KeyEvent.VK_LEFT:
			this.direction = Directions.WEST;
			break;
		case KeyEvent.VK_UP:
			this.direction = Directions.NORTH;
			break;
		case KeyEvent.VK_RIGHT:
			this.direction = Directions.EAST;
			break;
		case KeyEvent.VK_DOWN:
			this.direction = Directions.SOUTH;
			break;
		default:
			// Don't change direction if another key is pressed
			break;
		}
	}

	/**
	 * Get next position of the collector.
	 */
	private Position getNextCollectorPos() {
		Dimension size = getGameboardSize();
		
		int x = (int) ((((snakeParts.get(0).getX() + this.direction.getXDelta()) % (int)size.getWidth()) + size.getWidth()) % size.getWidth());
		int y = (int) ((((snakeParts.get(0).getY() + this.direction.getYDelta()) % (int)size.getHeight()) + size.getHeight()) % size.getHeight());
		
		return new Position(x, y);
	}

	/**
	 * This method is called repeatedly so that the game can update its state.
	 * 
	 * @param lastKey
	 *            The most recent keystroke.
	 */
	@Override
	public void gameUpdate(final int lastKey) throws GameOverException {
		boolean grows = false;

		updateDirection(lastKey);

		// Erase the previous position.
		setGameboardState(snakeParts.get(0), BLANK_TILE);
		// Change collector position.
		// snakeParts.add(getNextCollectorPos());
		
		if(snakeParts.contains(getNextCollectorPos())){
			throw new GameOverException(this.score);
		}
		snakeParts.addFirst(getNextCollectorPos());

		if (isOutOfBounds(snakeParts.get(0))) {
			throw new GameOverException(this.score);
		}
		// Draw collector at new position.
		for (int i = 0; i < snakeParts.size(); i++) {
			setGameboardState(snakeParts.get(i), SNAKE_TILE);
		}

		// Check if all apples are eaten
		if (gameOver) {
			throw new GameOverException(this.score + 5);
		}

		// Check if the snake ate an apple this update
		if (snakeParts.get(0).equals(apple)) {
			score++;
			APPLE_TILE = new AppleTile(score);
			setGameboardState(apple, SNAKE_TILE);
			addApple();
			grows = true;
		}

		if (!grows) {
			setGameboardState(snakeParts.getLast(), BLANK_TILE);
			snakeParts.remove(snakeParts.size() - 1);
			
		}

	}

	/**
	 * 
	 * @param pos
	 *            The position to test.
	 * @return <code>false</code> if the position is outside the playing field,
	 *         <code>true</code> otherwise.
	 */
	private boolean isOutOfBounds(Position pos) {
		return pos.getX() < 0 || pos.getX() >= getGameboardSize().width
				|| pos.getY() < 0 || pos.getY() >= getGameboardSize().height;
	}

}