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
	private SpriteSheet groundSheet;
	private Image grass;
	private Image path;

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
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		groundSheet = new SpriteSheet(
				"se/chalmers/dropit/glaz/towerdefence/res/ground.png", 20, 20,
				0);
		grass = groundSheet.getSprite(0, 0).getScaledCopy(spriteScale);
		path = groundSheet.getSprite(1, 0).getScaledCopy(spriteScale);
		
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

	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[i].length; j++){
				map[i][j].draw(j*grass.getWidth(), i*grass.getHeight());
			}
		}
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getID() {
		return ID;
	}

}
