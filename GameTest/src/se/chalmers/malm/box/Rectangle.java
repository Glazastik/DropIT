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

	@Override
	public void update() {
		super.update();
		addForce(Global.GRAVITY);
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

}
