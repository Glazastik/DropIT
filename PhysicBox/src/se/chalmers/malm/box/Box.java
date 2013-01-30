package se.chalmers.malm.box;

import java.awt.Color;

public class Box extends Rectangle {
	
	public Box(int x, int y, int side, Color color) {
		super(x, y, side, side, color);
		System.out.println("LŒda skapad");
	}
	
}
