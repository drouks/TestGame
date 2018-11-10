package Levels;

import tiles.Tile;

public class FirstLevel extends Level {

	public FirstLevel(String imagePath, int levelcount) {
		super(imagePath, 0);
		// TODO Auto-generated constructor stub
	
	
		for (int x = 0; x<this.width;x++)
		this.tiles[x + 0*32] = Tile.GRASS.getId();
		
		for (int x = 0; x<this.width;x++)
			this.tiles[x + this.height*32] = Tile.STONE.getId();
		
		
	
	
	
	
	
	}

}
