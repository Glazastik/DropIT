package se.chalmers.dropit.spel1;

import java.util.ArrayList;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.World;
import net.phys2d.raw.shapes.Box;
import net.phys2d.raw.shapes.Circle;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class GamePlay extends BasicGameState {
	private final int ID = 1;
	private Player mario;
	private Map map;
	private World world;
	private Body[] bodies;

	private float x = 400, y = 300;
	private Body body1;
	private boolean debug = false;

	private ArrayList<Body> bulletList;

	public GamePlay(int state) {

	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {

		bulletList = new ArrayList<Body>();

		mario = new Player();
		map = new Map("se/chalmers/dropit/spel1/res/mapTest.tmx");
		bodies = map.getTileBodies();

		world = new World(new Vector2f(0.0f, 10f), 50);

		body1 = new Body("player", new Box(mario.getCurrent().getWidth(), mario
				.getCurrent().getHeight()), 25);
		body1.setRotatable(true);
		body1.setPosition((float) mario.getX(), (float) mario.getY());
		// body1.setDamping(1f);
		body1.setFriction(1f);
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

		mario.setRotation(body1.getRotation());
		mario.getCurrent().draw((float) mario.getX(), (float) mario.getY());

		if (debug) {
			drawBody(bodies, g);
			drawBody(body1, g);
			drawBody(bulletList, g);
		}

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();

		if (input.isKeyPressed(Input.KEY_1)) {
			debug = !debug;
		}

		if (input.isKeyDown(Input.KEY_W)
				&& Math.abs(body1.getVelocity().getY()) < 1) {
			body1.addForce(new Vector2f(0, -80000));
		} else if (input.isKeyDown(Input.KEY_A)) {
			body1.addForce(new Vector2f(-10000, 0));
			mario.moveLeft(delta, true);

		} else if (input.isKeyDown(Input.KEY_D)) {
			body1.addForce(new Vector2f(10000, 0));
			mario.moveRight(delta, true);

		} else if (input.isKeyPressed(Input.KEY_SPACE)) {
			spawnBullet(bulletList, gc);

		} else {
			mario.stopMoving();
		}

		world.step();
	}

	private void drawBody(Body[] bodies, Graphics g) {
		for (int i = 0; i < bodies.length; i++) {
			Body body = bodies[i];
			Box box = (Box) body.getShape();
			Vector2f[] pts = box.getPoints(body.getPosition(),
					body.getRotation());

			Vector2f v1 = pts[0];
			Vector2f v2 = pts[1];
			Vector2f v3 = pts[2];
			Vector2f v4 = pts[3];

			g.setColor(Color.red);
			g.drawLine((int) v1.x, (int) v1.y, (int) v2.x, (int) v2.y);
			g.drawLine((int) v2.x, (int) v2.y, (int) v3.x, (int) v3.y);
			g.drawLine((int) v3.x, (int) v3.y, (int) v4.x, (int) v4.y);
			g.drawLine((int) v4.x, (int) v4.y, (int) v1.x, (int) v1.y);
		}
	}

	public void drawBody(Body body, Graphics g) {
		Box box = (Box) body.getShape();
		Vector2f[] pts = box.getPoints(body.getPosition(), body.getRotation());

		Vector2f v1 = pts[0];
		Vector2f v2 = pts[1];
		Vector2f v3 = pts[2];
		Vector2f v4 = pts[3];

		g.setColor(Color.cyan);
		g.drawLine((int) v1.x, (int) v1.y, (int) v2.x, (int) v2.y);
		g.drawLine((int) v2.x, (int) v2.y, (int) v3.x, (int) v3.y);
		g.drawLine((int) v3.x, (int) v3.y, (int) v4.x, (int) v4.y);
		g.drawLine((int) v4.x, (int) v4.y, (int) v1.x, (int) v1.y);

	}

	private void drawBody(ArrayList<Body> bodies, Graphics g) {
		for (int i = 0; i < bodies.size(); i++) {
			Body body = bodies.get(i);
			Box box = (Box) body.getShape();
			Vector2f[] pts = box.getPoints(body.getPosition(),
					body.getRotation());

			Vector2f v1 = pts[0];
			Vector2f v2 = pts[1];
			Vector2f v3 = pts[2];
			Vector2f v4 = pts[3];

			g.setColor(Color.red);
			g.drawLine((int) v1.x, (int) v1.y, (int) v2.x, (int) v2.y);
			g.drawLine((int) v2.x, (int) v2.y, (int) v3.x, (int) v3.y);
			g.drawLine((int) v3.x, (int) v3.y, (int) v4.x, (int) v4.y);
			g.drawLine((int) v4.x, (int) v4.y, (int) v1.x, (int) v1.y);
		}
	}

	private void spawnBullet(ArrayList<Body> bulletList, GameContainer gc) {
		Body bullet = new Body(new Box(5, 5), 2f);

		int xMouse = Mouse.getX();
		int yMouse = gc.getHeight() - Mouse.getY();
		body1.setRestitution(0);
		bullet.setPosition(body1.getPosition().getX(), body1.getPosition().getY());
		
		float xForce = 100*Math.abs(xMouse - body1.getPosition().getX());
		float yForce = 100*Math.abs(yMouse - body1.getPosition().getY());
		bullet.setForce(xForce, yForce);
		world.add(bullet);

		if (body1.getForce().getX() > 0) {
			bullet.addForce(new Vector2f(10000, 0));
		} else {
			bullet.addForce(new Vector2f(-10000, 0));
		}
		bulletList.add(bullet);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

}
