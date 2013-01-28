package se.chalmers.malm.box;

import java.awt.Color;
import java.awt.Graphics;

public class Box extends Rectangle {
	
	private int side;
	private double speedX, speedY = 1;
	
	private Force force = new Force(0, 0);
	
	public Box(int x, int y, int side, Color color) {
		super(x, y, side, side, color);
		System.out.println("LŒda skapad");
	}
	
}
