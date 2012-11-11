package se.chalmers.dropit.spel1;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

public abstract class Unit {
	private final static int DURATION = 100;
	private double x;
	private double xForce;
	private double y;
	private double yForce;
	private double speed = 1;
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

	public void move(int delta) {
		if (getxForce() < 0) {
			setX(getX() - 0.45 * delta * 0.4);
			setxForce(getxForce() * 0.1 + 0.001);
		} else if (getxForce() > 0) {
			setX(getX() + 0.45 * delta * 0.4);
			setxForce(getxForce() * 0.1 - 0.001);
		}

		if (getyForce() > 0) {
			moveUp(delta);
			System.out.println(getyForce());
			setyForce(getyForce() - delta * 0.1);
		}

	}

	public void moveLeft(int delta, boolean swapAnimation) {

		// setX((double) (getX() - delta * speed * .1f));
		if (getxForce() > -100 && getxForce() <= 0) {
			double nextForce = (getxForce() - delta * .1f) * 10;

			setxForce(nextForce);
		}
		direction = "left";
		if (swapAnimation && Math.abs(getyForce()) < 0.1) {
			setCurrent(getLeft());
		} else {
			setCurrent(getLeftAir());
		}
	}

	public void moveRight(int delta, boolean swapAnimation) {
		direction = "right";
		// setX((double) (getX() + delta * speed * .1f));

		if (getxForce() < 100 && getxForce() >= 0) {
			double nextForce = (getxForce() + delta * .1f) * 10;
			setxForce(nextForce);
		}

		if (swapAnimation && Math.abs(getyForce()) < 0.1) {
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
		if (getyForce() == 0) {
			setyForce(50);
		}

	}

	public void fall(int delta) {

		if (!(Math.abs(getyForce()) > 0.5 && Math.abs(getyForce()) < 1) ) {
			moveDown(delta);
			setyForce(getyForce() - 2);
		}
	}

	public void stopMoving() {
		if (direction.equals("left") && Math.abs(getyForce()) < 0.1) {
			setCurrent(getNormalLeft());
		} else if (direction.equals("right") && Math.abs(getyForce()) < 0.1) {
			setCurrent(getNormalRight());
		} else if (direction.equals("left") && Math.abs(getyForce()) < 0.1) {
			setCurrent(getLeftAir());
		} else if (direction.equals("left") && Math.abs(getyForce()) < 0.1) {
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

	public double getxForce() {
		return xForce;
	}

	public void setxForce(double xForce) {
		this.xForce = xForce;
	}

	public double getyForce() {
		return yForce;
	}

	public void setyForce(double yForce) {
		this.yForce = yForce;
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

	public Rectangle getRect() {
		return new Rectangle((int) x, (int) y, getCurrent().getWidth(),
				getCurrent().getHeight());
	}

}
