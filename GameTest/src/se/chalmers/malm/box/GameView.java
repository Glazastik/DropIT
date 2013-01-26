package se.chalmers.malm.box;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.*;

public class GameView extends JFrame {
	
//	private int fps = 0;
	private JFrame frame;
	private GameController controller;
	private ArrayList<Entity> entities;
	
	public GameView(ArrayList<Entity> entities, GameModel model) {
		super("Box");
		
		this.controller = new GameController(model, this);
		this.entities = entities;
		
		this.setVisible(true);
		this.setDefaultCloseOperation(3);
		this.setSize(700, 500);
		this.setLocationRelativeTo(null);
		this.setBackground(Color.WHITE);
	}
	
//	public void setFps(int fps) {
//		this.fps = fps;
//	}
	
	public void paint(Graphics g) {
		super.paintComponents(g);
//		g.setColor(Color.BLACK);
//		g.drawString(String.valueOf(fps), 50, 50);
		for(Entity e : entities) {
			e.paint(g);
		}
	}
}