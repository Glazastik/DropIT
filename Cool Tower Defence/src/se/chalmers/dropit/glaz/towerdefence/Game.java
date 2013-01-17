package se.chalmers.dropit.glaz.towerdefence;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends BasicGameState {

	private final int ID = 1;
	private final float spriteScale = 3.5f;
	private SpriteSheet masterSheet;
	private Image grass;
	private Image path;
	private Image enemy;
	
	private Enemy test;

	int[][] mapData = { 
			{ 0, 0, 1, 0, 0, 0, 0, 0, },
			{ 0, 0, 1, 1, 0, 0, 0, 0, },
			{ 0, 0, 0, 1, 1, 0, 0, 0, },
			{ 0, 0, 0, 0, 1, 0, 0, 0, },
			{ 0, 0, 0, 1, 1, 0, 0, 0, }, 
			{ 0, 0, 1, 1, 0, 0, 0, 0, },
			{ 0, 0, 1, 0, 0, 0, 0, 0, }, 
			{ 0, 0, 1, 1, 1, 1, 1, 1, }, };
	
	Image[][] map;

	public Game(int state) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		masterSheet = new SpriteSheet(
				"se/chalmers/dropit/glaz/towerdefence/res/ground.png", 20, 20,
				0);
		grass = masterSheet.getSprite(0, 0).getScaledCopy(spriteScale);
		path = masterSheet.getSprite(1, 0).getScaledCopy(spriteScale);
		enemy = masterSheet.getSprite(0, 1).getScaledCopy(spriteScale);
		
		map = new Image[mapData.length][mapData[0].length];
		for(int i = 0; i < mapData.length; i++){
			for(int j = 0; j < mapData[i].length; j++){
				if(mapData[i][j] == 0){
					map[i][j] = grass;
				} else {
					map[i][j] = path;
				}
			}
		}
		
		test = new Enemy(enemy, 100, 100, 1000, 10, 1);

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[i].length; j++){
				map[i][j].draw(j*grass.getWidth(), i*grass.getHeight());
			}
		}
		
		test.draw();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		test.tick(delta);

	}

	@Override
	public int getID() {
		return ID;
	}

}
