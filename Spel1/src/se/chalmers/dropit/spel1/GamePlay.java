package se.chalmers.dropit.spel1;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.TiledMap;

import se.chalmers.dropit.spel1.Tile.TileType;

public class GamePlay extends BasicGameState {
	private final int ID = 1;
	private Player mario;
	private Polygon marioPoly;
	private SpriteSheet sheet;
	private Map map;

	private float x = 400, y = 300;

	public GamePlay(int state) {

	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		mario = new Player();
		map = new Map("se/chalmers/dropit/spel1/res/mapTest.tmx");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		map.render(0, 0);
		mario.getCurrent().draw(mario.getX(), mario.getY());

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		if (input.isKeyDown(Input.KEY_A)) {
			mario.moveLeft(delta, true);
			if (map.isColliding(mario.getRect(), TileType.WALL)) {
				mario.moveRight(delta, false);
			}

		} else if (input.isKeyDown(Input.KEY_D)) {
			mario.moveRight(delta, true);
			if (map.isColliding(mario.getRect(), TileType.WALL)) {
				mario.moveLeft(delta, false);
			}
		} else if (input.isKeyDown(Input.KEY_W)) {
			mario.jump();
		} else {
			mario.stopMoving();
		}
		
		mario.moveDown(delta*2);
		if (map.isColliding(mario.getRect(), TileType.WALL)) {
			mario.moveUp(delta*2);
		}

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

}
