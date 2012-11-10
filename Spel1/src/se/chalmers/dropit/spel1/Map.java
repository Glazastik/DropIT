package se.chalmers.dropit.spel1;

import java.util.ArrayList;
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

			for (int i = 0; i < map.getWidth() - 1; i++) {
				for (int j = 0; j < map.getHeight() - 1; j++) {
					TileType type = null;
					if (map.getTileId(i, j, 0) == 1) {
						type = TileType.WALL;
					}

					if (map.getTileId(i, j, 0) == 2) {
						type = TileType.FLOOR;
					}

					if (type != null) {
						tileList.add(new Tile(type, i * map.getTileWidth(), j
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

}
