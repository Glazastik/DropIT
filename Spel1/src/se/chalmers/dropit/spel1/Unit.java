package se.chalmers.dropit.spel1;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

public abstract class Unit {
	private final static int DURATION = 100;
	private double x;
	private double y;
	private SpriteSheet spriteSheet;

	private Animation current, normalLeft, normalRight, left, right;
	private String direction = "right";
	private Animation rightAir;
	private Animation leftAir;
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
			Image[] leftAirImages = {
					spriteSheet.getSprite(5, 0).getScaledCopy(2),
					spriteSheet.getSprite(5, 0).getScaledCopy(2),
					spriteSheet.getSprite(5, 0).getScaledCopy(2) };
			leftAir = new Animation(leftAirImages, aDuration, false);
			Image[] rightAirImages = {
					spriteSheet.getSprite(5, 0).getFlippedCopy(true, false)
							.getScaledCopy(2),
					spriteSheet.getSprite(5, 0).getFlippedCopy(true, false)
							.getScaledCopy(2),
					spriteSheet.getSprite(5, 0).getFlippedCopy(true, false)
							.getScaledCopy(2) };
			rightAir = new Animation(rightAirImages, aDuration, false);
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
		direction = "left";
		if (swapAnimation) {
			setCurrent(getLeft());
		} else {
			setCurrent(getLeftAir());
		}
	}

	public void moveRight(int delta, boolean swapAnimation) {
		direction = "right";

		if (swapAnimation) {
			setCurrent(getRight());
		} else {
			setCurrent(getRightAir());
		}
	}

	public void moveDown(int delta) {
		setY((double) (getY() + delta * 2.5 * .1f));
	}

	public void moveUp(int delta) {
		setY((double) (getY() - delta * 4 * .1f));
	}

	public void jump() {

	}

	public void stopMoving() {
		
		//TODO: Fixa
		if (direction.equals("left")) {
			setCurrent(getNormalLeft());
		} else if (direction.equals("right")) {
			setCurrent(getNormalRight());
		} else if (direction.equals("left")) {
			setCurrent(getLeftAir());
		} else if (direction.equals("left")) {
			setCurrent(getRightAir());
		}
	}

	public double getX() {
		return x;
	}

	public void setX(double d) {
		this.x = d;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
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

	public Animation getRightAir() {
		return rightAir;
	}

	public void setRightAir(Animation rightAir) {
		this.rightAir = rightAir;
	}

	public Animation getLeftAir() {
		return leftAir;
	}

	public void setLeftAir(Animation leftAir) {
		this.leftAir = leftAir;
	}

	public Animation getCurrent() {
		return current;
	}

	public void setCurrent(Animation current) {
		current.setAutoUpdate(true);
		this.current = current;
	}
	
	public void setRotation(float rotation) {
		this.getCurrent().getCurrentFrame().setRotation((float) Math.toDegrees(rotation));
		
	}

	public Rectangle getRect() {
		return new Rectangle((int) x, (int) y, getCurrent().getWidth(),
				getCurrent().getHeight());
	}

}
