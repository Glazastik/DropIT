package se.chalmers.malm.box;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Entity {
	
	private int width, height;
	private Color color;
	
	public Rectangle(int x, int y, int width, int height, Color color) {
		super(x, y);
		this.width = width;
		this.height = height;
		this.color = color;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillRect((int)super.getX(), (int)super.getY(), width, height);
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	@Override
	public boolean contains(Entity e) {
		// TODO Check if the Rectangle collides with the object
		
		if(this == e) {
			return false;
		}
		
		double eWidth = e.getSilhouette()[2] - e.getSilhouette()[0];
		double eHeight = e.getSilhouette()[3] - e.getSilhouette()[1];
		
		if(e.getSilhouette()[0] + eWidth/2 - getX()-width/2 < Math.abs(eWidth/2 + width/2)
				&& e.getSilhouette()[1] + eHeight/2 - getY()-height/2 < Math.abs(eHeight/2 + height/2)) {
			System.out.println("Collision detected");
			color = Color.CYAN;
			return true;
		}
		
		return false;
	}
	
	public double[] getSilhouette() {
		double[] i = {getX(), getY(), getX()+width, getY()+height};
		return i;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

}
