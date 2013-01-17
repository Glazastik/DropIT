package se.chalmers.dropit.glaz.towerdefence;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;

public class Enemy extends Image {
	protected float startHealth;
	protected Image texture;
	protected float currentHealth;
	protected boolean alive = true;
	protected float speed = 0.5f;
	protected int bountyGiven;
	protected int posX;
	protected int posY;

	public Enemy(Image texture, int posX, int posY, float health,
			int bountyGiven, float speed) {

		super(texture);
		this.startHealth = health;
		this.currentHealth = startHealth;
		this.bountyGiven = bountyGiven;
		this.speed = speed;
		this.posX = posX;
		this.posY = posY;

	}

	public void tick(int delta) {
		if (currentHealth > 0) {
			currentHealth -= 1;
		} else {
			alive = false;
		}
	}

	@Override
	public void draw() {
		super.draw(posX, posY, new Color(20,
				currentHealth / startHealth * 255, currentHealth / startHealth
						* 255));
	}

}
