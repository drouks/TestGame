package ca.drgame.entities;

import ca.graphics.Colours;
import ca.graphics.Screen;

public class MeleeAttack {
private int x;
private int y;
private Player player;
private int movingDir;
private int duration;
private int maxduration = 10;

public MeleeAttack(Player player)
{
	this.player = player;
	duration = 0;
	
    if(player.movingDir == 0)
    {
    	x = player.x;
    	y = player.y-8;
    	movingDir = 0;
    }
    
    if(player.movingDir == 1)
    {
    	x = player.x;
    	y = player.y+8;
    	movingDir = 1;
    }
    
    if(player.movingDir == 2)
    {
    	x = player.x-8;
    	y = player.y-3;
    	movingDir = 2;
    }
    
    if(player.movingDir == 3)
    {
    	x = player.x+8;
    	y = player.y-3;
    	movingDir = 3;
    }
}

public void tick()
{
	  if(movingDir==0)
	  {
		  y = player.y-8;
	  }
	  
	  if(movingDir==1)
	  {
		  y = player.y+8;
	  }
	  
	  if(movingDir==2)
	  {
		  x = player.x-8;
	  }
	  
	  if(movingDir==3)
	  {
		  x = player.x+8;
	  }
	  
	  duration++;
	
}
	
public void render(Screen screen)
{
	if(movingDir==0)
	{
		screen.render(x-4, y-4, (1)+(22)*32, Colours.get(-1, 100, 420, 555), 0x00, 1);
		screen.render(x+4, y-4, (1)+(22)*32, Colours.get(-1, 100, 420, 555), 0x01, 1);		
	}
	
	if(movingDir==1)
	{

		screen.render(x+4, y+2, (3)+(22)*32, Colours.get(-1, 100, 420, 555), 0x00, 1);
		screen.render(x-4, y+2, (3)+(22)*32, Colours.get(-1, 100, 420, 555), 0x01, 1);	
		
	}
	
	if(movingDir==2)
	{
		screen.render(x-2, y+4, (2)+(22)*32, Colours.get(-1, 100, 420, 555), 0x00, 1);
		screen.render(x-2, y-4, (0)+(22)*32, Colours.get(-1, 100, 420, 555), 0x01, 1);	
	}
	
	if(movingDir==3)
	{
		screen.render(x+2, y+4, (2)+(22)*32, Colours.get(-1, 100, 420, 555), 0x01, 1);
		screen.render(x+2, y-4, (0)+(22)*32, Colours.get(-1, 100, 420, 555), 0x00, 1);	
		
	}
}
	
public int getDuration()
{
	return this.duration;
}

public int getMax()
{
	return this.maxduration;
}


}
