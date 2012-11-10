package se.chalmers.dropit.spel1;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public abstract class Unit {
	private final int ANIMATIONTIME = 200;
	private float x;
	private float y;
	private double speed = 1;
	private SpriteSheet spriteSheet;

	private Animation current, normalLeft, normalRight, left, right;
	protected static int aDuration[] = { 100, 100, 100 };

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void moveLeft(int delta) {
		setX((float) (getX() - delta * speed * .1f));
		setCurrent(getLeft());
	}

	public void moveRight(int delta) {
		setX((float) (getX() + delta * speed * .1f));
		setCurrent(getRight());
	}

	public void stopMoving(){
		if(getCurrent().getImage(0).equals(getLeft().getImage(0))){
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
}
