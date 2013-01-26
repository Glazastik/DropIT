package se.chalmers.malm.box;

import java.awt.Color;
import java.util.ArrayList;

public class GameModel {
	
	private int FPS_CAP = 100;
	private GameView view;
	private Entity block;
	private ArrayList<Entity> entities;
	
	public GameModel() {
		entities = new ArrayList<Entity>();
		view = new GameView(entities, this);
		block = new Box(200, 50, 100, Color.RED);
	}
	
	public void startGame() {
		
		entities.add(block);
		
		rain();
		
		Updater u = new Updater(FPS_CAP, entities);
		u.run();
	}
	
	public void keyPressed(char c) {
		
		if(c == 'w') {
			block.addForce(new Force(0, -1.8));
		} else if(c == 'a') {
			block.addForce(new Force(-0.5, 0));
		} else if(c == 's') {
			block.addForce(new Force(0, 0.5));
		} else if(c == 'd') {
			block.addForce(new Force(0.5, 0));
		}
		
	}
	
	private void rain() {
		for(int i = 0; i < 200; i++) {
			entities.add(new Rain((int)(Math.random()*700), (int)(Math.random()*-1400)));
		}
	}
	
	private class Updater implements Runnable{
//		private int fps;
		private long nextTick;
		private ArrayList<Entity> entities;
		private int loops;
		
		private final int SKIP_TICKS;
		private final int MAX_FRAMESKIP;
		
		public Updater(int fps, ArrayList<Entity> entities) {
//			this.fps = fps;
			MAX_FRAMESKIP = 10;
			SKIP_TICKS = 1000 / fps;
			this.entities = entities;
			nextTick = System.currentTimeMillis();
		}
		
		private long getTickCount() {
			return System.currentTimeMillis();
		}
		
		private void updateGame() {
			for(Entity e : entities) {
				e.update();
			}
		}

		@Override
		public void run() {
			
			while(true) {
				
				loops = 0;

				while( getTickCount() > nextTick
						&& loops < MAX_FRAMESKIP) {
					updateGame();
					nextTick += SKIP_TICKS;
					loops++;
				}
				
				view.repaint();
			}
		}
	}
}
