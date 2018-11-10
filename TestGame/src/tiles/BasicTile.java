package tiles;

import Levels.Level;
import ca.graphics.Screen;

public class BasicTile extends Tile {

	protected int tileId;
	protected int tileColour;
	
	
	public BasicTile(int id, int x, int y,int colour,int levelColour) {
		super(id, false, false,levelColour);
        this.tileId = x+y*32;
        this.tileColour = colour;
        
	}


	public void render(Screen screen, Level level, int x, int y) {
     screen.render(x,y,tileId,tileColour,0x00,1);
		
	}
	
	public void tick()
	{
		
	}

	
	
}
