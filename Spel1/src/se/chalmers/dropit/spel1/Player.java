package se.chalmers.dropit.spel1;

import org.newdawn.slick.*;

public class Player extends Unit {

	private static String spriteResource = "se/chalmers/dropit/spel1/res/mario.png";
	private static int tableWidth = 14;
	private static int tableHeight = 22;
	private static int spacing = 3;

	public Player() {
		super(spriteResource, tableWidth, tableHeight, spacing);
		setX(400);
		setY(400);
		// Test
	}

}
