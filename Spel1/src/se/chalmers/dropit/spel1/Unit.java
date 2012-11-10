package se.chalmers.dropit.spel1;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

public abstract class Unit {
	private final static int DURATION = 100;
	private float x;
	private float y;
	private double speed = 1;
	private SpriteSheet spriteSheet;
	private Rectangle rect;

	private Animation current, normalLeft, normalRight, left, right;
	private String direction = "right";
	protected static int aDuration[] = { DURATION, DURATION, DURATION };

	public Unit(String res, int tw, int th, int spacing) {
		try {
			spriteSheet = new SpriteSheet(res, tw, th, spacing);
			Image[] normalLeftImages = {
					spriteSheet.getSprite(0, 0).getScaledCopy(2),
					spriteSheet.getSprite(0, 0).getScaledCopy(2),
					spriteSheet.getSprite(0, 0).getScaledCopy(2) };
			normalLeft = new Animation(normalLeftImages, aDuration, false);
			Image[] normalRightImages = {
					spriteSheet.getSprite(0, 0).getFlippedCopy(true, false)
							.getScaledCopy(2),
					spriteSheet.getSprite(0, 0).getFlippedCopy(true, false)
							.getScaledCopy(2),
					spriteSheet.getSprite(0, 0).getFlippedCopy(true, false)
							.getScaledCopy(2) };
			normalRight = new Animation(normalRightImages, aDuration, false);
			Image[] leftImages = {
					spriteSheet.getSprite(1, 0).getScaledCopy(2),
					spriteSheet.getSprite(2, 0).getScaledCopy(2),
					spriteSheet.getSprite(3, 0).getScaledCopy(2) };
			left = new Animation(leftImages, aDuration, false);
			Image[] rightImages = {
					spriteSheet.getSprite(1, 0).getFlippedCopy(true, false)
							.getScaledCopy(2),
					spriteSheet.getSprite(2, 0).getFlippedCopy(true, false)
							.getScaledCopy(2),
					spriteSheet.getSprite(3, 0).getFlippedCopy(true, false)
							.getScaledCopy(2) };
			right = new Animation(rightImages, aDuration, false);
			setNormalLeft(normalLeft);
			setNormalRight(normalRight);
			setLeft(left);
			setRight(right);
			setCurrent(normalRight);

		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void moveLeft(int delta, boolean swapAnimation) {

		setX((float) (getX() - delta * speed * .1f));
		direction = "left";
		if (swapAnimation) {
			setCurrent(getLeft());
		}
	}

	public void moveRight(int delta, boolean swapAnimation) {
		direction = "right";
		setX((float) (getX() + delta * speed * .1f));
		if (swapAnimation) {
			setCurrent(getRight());
		}
	}
	
	public void moveDown(int delta){
		setY((float) (getY() + delta * speed * .1f));
	}
	
	public void moveUp(int delta){
		setY((float) (getY() - delta * speed * .1f));
	}

	public void stopMoving() {
		if (direction.equals("left")) {
			setCurrent(getNormalLeft());
		} else {
			setCurrent(getNormalRight());
		}
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public Animation getNormalLeft() {
		return normalLeft;
	}

	public void setNormalLeft(Animation normalLeft) {
		this.normalLeft = normalLeft;
	}

	public Animation getNormalRight() {
		return normalRight;
	}

	public void setNormalRight(Animation normalRight) {
		this.normalRight = normalRight;
	}

	public Animation getLeft() {
		return left;
	}

	public void setLeft(Animation left) {
		this.left = left;
	}

	public Animation getRight() {
		return right;
	}

	public void setRight(Animation right) {
		this.right = right;
	}

	public Animation getCurrent() {
		return current;
	}

	public void setCurrent(Animation current) {
		current.setAutoUpdate(true);
		this.current = current;
	}

	public Rectangle getRect() {
		return new Rectangle(x, y, getCurrent().getWidth(), getCurrent()
				.getHeight());
	}

}
