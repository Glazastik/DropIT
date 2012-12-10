package se.chalmers.dropit.spel1;

import java.util.ArrayList;

import net.phys2d.raw.Body;
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.shapes.Box;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.*;

import se.chalmers.dropit.spel1.Tile.TileType;

public class Map {
	private TiledMap map;
	private ArrayList<Tile> tileList;

	public Map(String res) {
		tileList = new ArrayList<Tile>();
		try {
			map = new TiledMap(res);

			for (int i = 0; i < map.getHeight() - 1; i++) {
				for (int j = 0; j < map.getWidth() - 1; j++) {
					TileType type = null;
					if (map.getTileId(j, i, 0) == 1) {
						type = TileType.WALL;
					}

					if (map.getTileId(j, i, 0) == 2) {
						type = TileType.FLOOR;
					}
					
					if (type != null) {
						tileList.add(new Tile(type, j * map.getTileWidth(), i
								* map.getTileWidth(), map.getTileWidth(), map
								.getTileHeight()));
					}
				}
			}

		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isColliding(Rectangle rect, TileType type) {
		for (int i = 0; i < tileList.size(); i++) {
			if (type == tileList.get(i).getType()) {
				if (rect.intersects(tileList.get(i).getRect())) {
					return true;
				}
			}
		}
		return false;
	}

	public void render(int x, int y) {
		map.render(x, y);
	}

	public Body[] getTileBodies() {
		Body[] bodies = new Body[tileList.size()];
		
		for (int i = 0; i < tileList.size(); i++) {

			Body tempBody = new StaticBody(new Box(map.getTileWidth(),
					map.getTileHeight()));
			tempBody.setPosition(tileList.get(i).getX() + map.getTileWidth()
					/ 2, tileList.get(i).getY() + map.getTileHeight() / 2);

			tempBody.setFriction(1f);

			bodies[i] = tempBody;
			
		}
		optBodies(bodies);
		return bodies;
	}

	public void optBodies(Body[] bodies) {
		
		
	}
}
