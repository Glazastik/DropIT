package se.chalmers.dropit.spel1;

import org.newdawn.slick.geom.Rectangle;

public class Tile {
	public enum TileType{
		WALL, FLOOR;
	}
	
	private TileType type;
	private float x;
	private float y;
	private float width;
	private float height;
	
	public Tile(TileType type, float x, float y, float height, float width){
		this.type = type;
		this.setX(x);
		this.setY(y);
		this.setHeight(height);
		this.setWidth(width);
	}
	
	public Rectangle getRect(){
		return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}
	
	
	public TileType getType(){
		return type;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}
	
}
