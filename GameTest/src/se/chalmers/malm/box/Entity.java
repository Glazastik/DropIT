package se.chalmers.malm.box;

import java.awt.Graphics;

/**
 * An Entity is a class that can be affected by forces and collisions with other entities.  
 * @author Malm
 */
public abstract class Entity {
	
	private double x, y;
	private Force force;
	
	/**
	 * Set the position of the object.
	 * @param x the horizontal position
	 * @param y the vertical position
	 */
	public Entity(double x, double y) {
		this.x = x;
		this.y = y;
		this.force = new Force(0, 0);
	}

	public void update() {
		if(getY()+getHeight() >= 500 && force.getYPower() < 8) {
			force.addVerticalForce(-force.getYPower()*1.7);
		}
		x += force.getXPower();
		y += force.getYPower();
	}
	
	public final int getMass() {
		return 1;
	}
	
	public final double getX() {
		return x;
	}
	
	public final double getY() {
		return y;
	}
	
	public Force getForce() {
		return force;
	}
	
	public abstract int getHeight();
	public abstract boolean contains(Object o);
	public abstract boolean equals(Object o);
	public abstract void paint(Graphics g);

	public final void addForce(Force f) {
		force.addForce(f);
	}

}
