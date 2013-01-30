package se.chalmers.malm.box;

import java.awt.Graphics;

/**
 * An Entity is a class that can be affected by forces and collisions with other entities.  
 * @author Malm
 */
public abstract class Entity {
	
	private boolean gravityApplied = true;
	private double x, y, mass;
	private Force force;
	
	/**
	 * Set the position of the object.
	 * The weight defaults to 1kg.
	 * @param x the horizontal position
	 * @param y the vertical position
	 */
	public Entity(double x, double y) {
		this(x, y, 100);
	}
	
	/**
	 * Set the position and weight of the object.
	 * @param x the horizontal position
	 * @param y the vertical position
	 * @param weight the weight
	 */
	public Entity(double x, double y, double mass) {
		this.x = x;
		this.y = y;
		this.mass = mass;
		this.force = new Force(0, 0);
	}

	/**
	 * Updates the state of the entity.
	 */
	public void update() {
		if(gravityApplied) {
			addForce(Global.GRAVITY);
		}
		
		// Check for collision
		if(getY()+getHeight() + force.getVerticalForce() > 500 + 1) {
			if(force.getVerticalForce() > 1) {
				force.addVerticalForce(-force.getVerticalForce()*1.7);
			} else {
				force.changeForce(1, 0);
			}
		}
		
//		if(getY()+getHeight() >= 500) {
//			if(force.getVerticalForce() > 0.1) {
//				force.addVerticalForce(-force.getVerticalForce()*1.2);
//			} else {
//				force.changeForce(1, 0);
//			}
//		}
		x += force.getHorizontalForce();
		y += force.getVerticalForce();
	}
	
	/**
	 * Returns the mass in kg
	 * @return mass in kg
	 */
	public final double getMass() {
		return mass;
	}
	
	public final double getX() {
		return x;
	}
	
	public final double getY() {
		return y;
	}
	
	public double getHorizontalForce() {
		return force.getHorizontalForce();
	}
	
	public double getVerticalForce() {
		return force.getVerticalForce();
	}
	
	public abstract double[] getSilhouette();
	public abstract int getHeight();
	public abstract int getWidth();
	public abstract boolean contains(Entity e);
	public abstract boolean equals(Object o);
	public abstract void paint(Graphics g);

	/**
	 * Add a force
	 * @param f the force vector being added
	 */
	public final void addForce(Force f) {
		force.addForce(f);
	}

}
