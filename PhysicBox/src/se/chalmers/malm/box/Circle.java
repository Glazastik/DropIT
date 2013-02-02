package se.chalmers.malm.box;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends Entity {

	private int radius;
	private Color color;
	
	public Circle(double x, double y, int radius, Color color, double mass) {
		super(x, y);
		this.radius = radius;
		this.color = color;
	}
	
	public Circle(double x, double y, int radius, Color color) {
		this(x, y, radius, color, 10);
	}

	@Override
	public int getHeight() {
		return radius*2;
	}

	@Override
	public int getWidth() {
		return radius*2;
	}

	@Override
	public boolean contains(Entity e) {
		
		if(this == e) {
			return false;
		}
		
		if(e instanceof Circle) {
			if(Math.sqrt(Math.pow(e.getX()+e.getWidth()/2 - (getX()+radius), 2) + Math.pow(e.getY()+e.getHeight()/2 - (getY()+radius), 2)) < this.radius + e.getWidth()/2)
//					&& Math.abs(e.getY()+e.getHeight()/2 - (getY()+radius)) < this.radius + e.getHeight()/2)
			{
				color.brighter();
				System.out.println("Collision");
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

	@Override
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillOval((int)getX(), (int)getY(), radius*2, radius*2);
	}

}
