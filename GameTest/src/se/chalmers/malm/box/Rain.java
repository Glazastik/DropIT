package se.chalmers.malm.box;

import java.awt.Color;

public class Rain extends Box{
	
	public Rain(int x, int y) {
		super(x, y, 5, Color.CYAN);
		super.addForce(new Force(0, 0.1));
	}

	public Rain() {
		this(10, 10);
	}
	
}
