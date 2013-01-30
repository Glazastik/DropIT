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
		
		if((e.getX() + e.getWidth()/2)-(getX()+getWidth()/2) < Math.abs(e.getWidth()/2 + getWidth()/2)
				&& (e.getY()+e.getWidth()/2) - (getY()+getWidth()/2) < Math.abs(e.getWidth()/2 + getWidth()/2)) {
			System.out.println("Collision detected");
			color = Color.CYAN;
			return true;
		}
		
		return false;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

}
