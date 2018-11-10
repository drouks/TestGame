package ca.drgame.entities;

import Levels.Level;
import ca.graphics.Colours;
import ca.graphics.Screen;


public class LevelGoal extends Entity{

	int XareaS;
	int XareaE;
	int YareaS;
	int YareaE;

	
	public LevelGoal(Level level, int x, int y) {
		super(level);
		this.x = x<<3;
		this.y = y<<3;
		
		this.XareaS = (this.x);
		this.XareaE = (this.x+8);
		this.YareaS = (this.y-3);
		this.YareaE = (this.y+8);
		
	
		System.out.println("goal x" + this.x);
		System.out.println("goal y" + this.y);
		System.out.println(XareaS +"-"+XareaE);
	}

	@Override
	public void tick() {
		Player player;
		if(level.getPlayer()!=null)
		{
		   player = level.getPlayer();
		   if (player.x>=this.XareaS && player.x<=this.XareaE && player.y<=this.YareaE && player.y>=this.YareaS)
		   {
			   this.level.setLevelStatus(true);
		   }
		}
		
	}

	@Override
	public void render(Screen screen) {
		int chestcolour = 420;
		
		screen.render(x, y, 0 + 3 * 32, Colours.get(-1, 100, chestcolour, 555), 0x00, 1);
		screen.render(x+8, y, 1 + 3 * 32, Colours.get(-1, 100, chestcolour, 555), 0x00, 1);
		screen.render(x, y+8, 0 + 4 * 32, Colours.get(-1, 100, chestcolour, 555), 0x00, 1);
		screen.render(x+8, y+8, 1 + 4 * 32, Colours.get(-1, 100, chestcolour, 555), 0x00, 1);
	}

}
