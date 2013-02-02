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
	
	public Rectangle(int x, int y, int width, int height, Color color, double mass) {
		super(x, y, mass);
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
		
		if(e instanceof Rectangle) {
			if((Math.abs(e.getX()+e.getWidth()/2 - (getX()+getWidth()/2))) < e.getWidth()/2 + getWidth()/2
					&& Math.abs(e.getY()+e.getHeight()/2 - (getY()+getHeight()/2)) < e.getHeight()/2 + getHeight()/2) {
				System.out.println("x1: " + getX() + " width " + getWidth()/2);
				System.out.println("y1: " + getY() + " height " + getHeight()/2);
				color = Color.CYAN;
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

}
