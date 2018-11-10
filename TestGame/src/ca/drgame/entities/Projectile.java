package ca.drgame.entities;

import ca.graphics.Colours;
import ca.graphics.Screen;

public class Projectile {
private int speed;
private Player player;
private int x;
private int y;
private int hasTravelled;
private boolean collisionstatus = false;
private boolean active = false;
private int movingDir;




public Projectile(Player player) {
	this.speed = 2;
	this.player = player;
	this.active = true;
	this.hasTravelled = 0;
	this.collisionstatus = false;
	
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
	  y = y-speed;
	  hasTravelled +=speed;
  }
  
  if(movingDir==1)
  {
	  y = y+speed;
	  hasTravelled +=speed;
  }
  
  if(movingDir==2)
  {
	  x = x-speed;
	  hasTravelled +=speed;
  }
  
  if(movingDir==3)
  {
	  x = x+speed;
	  hasTravelled +=speed;
  }
  
  
}

public void render(Screen screen)
{
	if(movingDir==0)
	{
		screen.render(x, y, (3)+(2)*32, Colours.get(-1, 100, 420, 555), 0x00, 1);
	}
	
	if(movingDir==1)
	{
		screen.render(x, y, (1)+(2)*32, Colours.get(-1, 100, 420, 555), 0x00, 1);
	}
	
	if(movingDir==2)
	{
		screen.render(x, y, (2)+(2)*32, Colours.get(-1, 100, 420, 555), 0x00, 1);
	}
	
	if(movingDir==3)
	{
		screen.render(x, y, (0)+(2)*32, Colours.get(-1, 100, 420, 555), 0x00, 1);
	}
	
}



public int getSpeed() {
	return speed;
}
public void setSpeed(int speed) {
	this.speed = speed;
}

public Player getPlayer()
{
	return player;
}

public void setPlayer(Player player)
{
	this.player = player;
}

public int getX() {
	return x;
}
public void setX(int x) {
	this.x = x;
}
public int getY() {
	return y;
}
public void setY(int y) {
	this.y = y;
}


public boolean isActive() {
	return active;
}


public void setActive(boolean active) {
	this.active = active;
}


public boolean isCollisionstatus() {
	return collisionstatus;
}


public void setCollisionstatus(boolean collisionstatus) {
	this.collisionstatus = collisionstatus;
}

public int getTravelled()
{
	return this.hasTravelled;
}

}
