package se.chalmers.dropit.spel1;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.TiledMap;

public class GamePlay extends BasicGameState {
	private final int ID = 1;
	private Image world;
	private Image player;
	private Image mario;
	private Polygon marioPoly;
	private SpriteSheet sheet;
	private TiledMap map;

	private float x = 400, y = 300;

	public GamePlay(int state) {

	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		world = new Image("se/chalmers/dropit/spel1/res/land.jpg");
		player = new Image("se/chalmers/dropit/spel1/res/plane.png");
		sheet = new SpriteSheet("se/chalmers/dropit/spel1/res/mario.png", 14,
				22, 3); // width, height, spacing
		mario = sheet.getSprite(1, 0);
		map = new TiledMap("se/chalmers/dropit/spel1/res/mapTest.tmx");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		map.render(0, 0);
		player.draw(x, y);
		mario.draw(x,y,2);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		if (input.isKeyDown(Input.KEY_A)) {
			player.rotate(-0.2f * delta);

		}

		if (input.isKeyDown(Input.KEY_D)) {
			player.rotate(+0.2f * delta);

		}

		if (input.isKeyDown(Input.KEY_W)) {
			float hip = 0.4f * delta;

			float rotation = player.getRotation();

			x += hip * Math.sin(Math.toRadians(rotation));
			y -= hip * Math.cos(Math.toRadians(rotation));

		}

		if (input.isKeyDown(Input.KEY_S)) {
			// y = (float) (y + delta * .1f);
		}

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

}
