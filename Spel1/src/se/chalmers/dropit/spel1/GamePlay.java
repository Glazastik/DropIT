package se.chalmers.dropit.spel1;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.World;
import net.phys2d.raw.shapes.Box;
import net.phys2d.raw.strategies.QuadSpaceStrategy;

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
	private World world;
	private Body[] bodies;

	private float x = 400, y = 300;
	private Body body1;

	public GamePlay(int state) {

	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		mario = new Player();
		map = new Map("se/chalmers/dropit/spel1/res/mapTest.tmx");
		bodies = map.getTileBodies();

		world = new World(new Vector2f(0.0f, 10f), 20, new QuadSpaceStrategy(20,5));

		body1 = new Body("player", new Box(mario.getCurrent().getWidth(), mario
				.getCurrent().getHeight()), 25);
		body1.setRotatable(false);
		body1.setPosition((float) mario.getX(), (float) mario.getY());
//		body1.setDamping(1f);
		body1.setFriction(0f);
		body1.setMaxVelocity(25, 1000);
		body1.setRestitution(0);

		for (int i = 0; i < bodies.length; i++) {
			world.add(bodies[i]);
		}

		world.add(body1);

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		map.render(0, 0);
		mario.setX(body1.getPosition().getX() - mario.getCurrent().getWidth()
				/ 2);
		mario.setY(body1.getPosition().getY() - mario.getCurrent().getHeight()
				/ 2);

		mario.getCurrent().draw((float) mario.getX(), (float) mario.getY());

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		if (input.isKeyDown(Input.KEY_W )) {
			body1.addForce(new Vector2f(0, -2000));
		} else if (input.isKeyDown(Input.KEY_A)) {
			body1.addForce(new Vector2f(-10000000, 0));
			// mario.moveLeft(delta, true);
			// if (map.isColliding(mario.getRect(), TileType.WALL)) {
			// mario.setxForce(mario.getxForce() * -1);
			// mario.moveRight(delta, false);
			// }

		} else if (input.isKeyDown(Input.KEY_D)) {
			body1.addForce(new Vector2f(10000000, 0));
			// mario.moveRight(delta, true);
			// if (map.isColliding(mario.getRect(), TileType.WALL)) {
			// mario.setxForce(mario.getxForce() * -1);
			// mario.moveLeft(delta, false);
			// }
		} else {
			mario.stopMoving();
			// if (Math.abs(mario.getxForce()) < 0.005) {
			// mario.setxForce(0);
			// }
		}

		// Physics tick
		// mario.move(delta);
		// if (!map.isColliding(mario.getRect(), TileType.WALL)) {
		// mario.fall(delta);
		// if (map.isColliding(mario.getRect(), TileType.WALL)) {
		// mario.moveUp(delta);
		// mario.setyForce(0);
		// if (map.isColliding(mario.getRect(), TileType.WALL)) {
		// mario.setxForce(mario.getxForce() * -1);
		// mario.moveLeft(delta, false);
		// if (map.isColliding(mario.getRect(), TileType.WALL)) {
		// mario.setxForce(mario.getxForce() * -1);
		// mario.moveRight(delta, false);
		// }
		// }
		// }
		// }
		world.step();
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

}
