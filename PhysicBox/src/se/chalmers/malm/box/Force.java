package se.chalmers.malm.box;

/**
 * A class that stores force as vectors with length and direction
 * @author Malm
 */
public class Force {
	
	/**
	 * 
	 */
	private double xPower, yPower;
	
	public Force(double x, double y) {
		this.xPower = x;
		this.yPower = y;
	}

	public double getHorizontalForce() {
		return xPower;
	}

	public double getVerticalForce() {
		return yPower;
	}
	
	/**
	 * Changes the force by percent
	 * @param x change horizontally
	 * @param y change vertically
	 */
	public void changeForce(double x, double y) {
		xPower *= x;
		yPower *= y;
	}
	
//	public void decreaseHorizontalForce(double force) {
//		if(xPower > 0) {
//			
//		}
//	}
	
	public void addVerticalForce(double force) {
		yPower += force;
	}
	
	public void addForce(Force f) {
		xPower += f.getHorizontalForce();
		yPower += f.getVerticalForce();
	}
	
	public void decreaseForce(Force f) {
		xPower -= f.getHorizontalForce();
		yPower -= f.getVerticalForce();
	}
}
