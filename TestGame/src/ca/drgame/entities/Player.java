package ca.drgame.entities;

import java.util.ArrayList;
import java.util.LinkedList;

import Levels.Level;
import ca.drgame.InputHandler;
import ca.graphics.Colours;
import ca.graphics.Screen;

public class Player extends Mob {

	private InputHandler input;
    private int scale = 1;
	private int colour = Colours.get(-1, 0, 8, 543);
	protected boolean isSwimming = false;
	private int tickCount=0;
	protected boolean rangedAttack = false;
	protected boolean meleeAttack = false;
	protected int projectileInterval = 0;
	protected int meleeInterval = 0;
	private int health = 8;
	private int maxhealth = 8;
	private ArrayList<Projectile> activeProjectiles = new ArrayList<Projectile>();
	private ArrayList<MeleeAttack> activeMelee = new ArrayList<MeleeAttack>();
	private ArrayList<Heart> hearts = new ArrayList<Heart>();


	public Player(Level level, int x, int y, InputHandler input) {
		super(level, "Player", x, y, 1);
		this.input = input;
		for(int i=0;i<maxhealth;i++)
		{
			hearts.add(new Heart(HeartStatus.FULL,i));
		}
		
		for(int i=0;i<maxhealth-health;i++)
		{
			hearts.add(new Heart(HeartStatus.DEPLETED,i));
		}
	}

	

	public void tick() {
		
		int xa = 0;
		int ya = 0;
				
		if (input.up.isPressed()) ya--;
		if (input.down.isPressed()) ya++;
		if (input.left.isPressed()) xa--;
		if (input.right.isPressed()) xa++;
		
		if (xa!=0 || ya!=0)
		{
			move(xa,ya);
			isMoving = true;
		}
		else
		{
			isMoving = false;
		}
		
	   if(input.melee.isPressed() && !input.ranged.isPressed())
	   {
		   rangedAttack = false;
		   meleeAttack = true;

		   if(activeMelee.size()==0)
		   {
	           MeleeAttack newmelee = new MeleeAttack(this);
			   this.activeMelee.add(newmelee);
			   activeMelee.get(0).tick();
		   }
		   else
		   {
			   activeMelee.get(0).tick();
		   }
		   
	   }
	   else
	   {
		   meleeAttack = false;
	   }
		
	   if (input.ranged.isPressed() && !input.melee.isPressed()) 
	   {   meleeAttack = false;
		   rangedAttack = true;
		   
		   if(projectileInterval==10)
		   {
		   Projectile newproj = new Projectile(this);
		   this.activeProjectiles.add(newproj);
		   projectileInterval = 0;
		   }
		   projectileInterval++;
	   }
	   else
	   {
		   rangedAttack = false;
	   }
	   
	   if (level.getTile(this.x>>3,this.y>>3).getId()==3)
	   {
		   isSwimming = true;
	   }
	   
	   if (isSwimming && level.getTile(this.x>>3, this.y>>3).getId()!=3) {
		   isSwimming = false;}
	  
	   tickCount++;
	    for(int i=0; i<activeProjectiles.size();i++)
        {
           if(activeProjectiles.get(i).getTravelled() > 100)
        	   {
        		 activeProjectiles.remove(i); 
        	   }
           else
           {
        	   activeProjectiles.get(i).tick();
           }
        }
	    
	   for(int i=0; i<activeMelee.size();i++)
	   {
		   if(activeMelee.get(i).getDuration() > activeMelee.get(i).getMax())
		   {
			   activeMelee.remove(i);
		   }
		   else
		   {
			   activeMelee.get(i).tick();
		   }
	   }
	
	   
	}



	public void render(Screen screen) {
	        int xTile = 0;
	        int yTile = 28;
	        int walkingSpeed = 4;
	        int flipTop = (numSteps >> walkingSpeed) & 1;
	        int flipBottom = (numSteps >> walkingSpeed) & 1;

	        if (movingDir == 1) {
	            xTile += 2;
	      
	           
	        } else if (movingDir > 1) {
	            xTile += 4 + ((numSteps >> walkingSpeed) & 1) * 2;
	            flipTop = (movingDir - 1) % 2;
	            flipBottom = (movingDir - 1) % 2;

	        }

	        int modifier = 8 * scale;
	        int xOffset = x - modifier / 2;
	        int yOffset = y - modifier / 2 - 4;
	        
	        if (rangedAttack)
	        {
	        	yTile = 24;
	        }

	        if (isSwimming) {
	            int waterColour = 0;
	            yOffset += 4;
	            if (tickCount % 60 < 15) {
	                waterColour = Colours.get(-1, -1, 225, -1);
	            } else if (15 <= tickCount % 60 && tickCount % 60 < 30) {
	                yOffset -= 1;
	                waterColour = Colours.get(-1, 225, 115, -1);
	            } else if (30 <= tickCount % 60 && tickCount % 60 < 45) {
	                waterColour = Colours.get(-1, 115, -1, 225);
	            } else {
	                yOffset -= 1;
	                waterColour = Colours.get(-1, 225, 115, -1);
	            }
	            screen.render(xOffset, yOffset + 3, 0 + 27 * 32, waterColour, 0x00, 1);
	            screen.render(xOffset + 8, yOffset + 3, 0 + 27 * 32, waterColour, 0x01, 1);
	        }
	        screen.render(xOffset + (modifier * flipTop), yOffset, xTile + yTile * 32, colour, flipTop, scale);
	        screen.render(xOffset + modifier - (modifier * flipTop), yOffset, (xTile + 1) + yTile * 32, colour, flipTop,
	                scale);

	        if (!isSwimming) {
	        	screen.render(xOffset + (modifier * flipBottom), yOffset + modifier, xTile + (yTile + 1) * 32, colour,
	                    flipBottom, scale);
	            screen.render(xOffset + modifier - (modifier * flipBottom), yOffset + modifier, (xTile + 1) + (yTile + 1)
	                    * 32, colour, flipBottom, scale);
	        }
	        
	        
	        for(int i=0; i<activeProjectiles.size();i++)
	        {
	        
	        	   activeProjectiles.get(i).render(screen);
	        }
	        
	        for(int i=0; i<activeMelee.size();i++)
	        {
	             activeMelee.get(i).render(screen);
	        }
	        	        
	    }
	
	
	public boolean hasCollided(int xa, int ya) {
        int xMin = 0;
        int xMax = 7;
        int yMin = 3;
        int yMax = 7;
        for (int x = xMin; x < xMax; x++) {
            if (isSolidTile(xa, ya, x, yMin)) {
                return true;
            }
        }
        for (int x = xMin; x < xMax; x++) {
            if (isSolidTile(xa, ya, x, yMax)) {
                return true;
            }
        }
        for (int y = yMin; y < yMax; y++) {
            if (isSolidTile(xa, ya, xMin, y)) {
                return true;
            }
        }
        for (int y = yMin; y < yMax; y++) {
            if (isSolidTile(xa, ya, xMax, y)) {
                return true;
            }
        }
        return false;
    }


    public void Heal(int amount)
    {
    	if(this.health==this.maxhealth)
    	{
    		return;
    	}
    	else
    	{
    		for(int i=0; i<amount;i++)
    		{
    		 if(this.health==this.maxhealth)
    		 {
    			 break;
    		 }
    		 else
    		 {
    			 this.health++;
    			 this.adaptHearts();
    		 }
    		}
    		return;
    	}
    }
    
    public void Damage(int amount)
    {
    	
      for(int i=0;i<amount;i++)
      {
    	  this.health-=i;
    	  this.adaptHearts();
    	  if (this.health==0)
    	  {
    		  this.Death();
    	  }
      }
   
    }
    
    void adaptHearts()
    {
        hearts.clear();
        int firstdepleted = 5;
        if(health==maxhealth)
        	return;
        
        for(int i=0;i<health;i++)
        {
          	hearts.add(new Heart(HeartStatus.FULL,i));
          	firstdepleted = i;
        }
        
        firstdepleted++;
        for(int i=firstdepleted;i<maxhealth;i++)
        {
        	hearts.add(new Heart(HeartStatus.DEPLETED,i));
        }
        
        return;
    }
   
	
    void Death() //TO-DO
    {
    	return;
    }



	public int getHealth() {
		return health;
	}



	public void setHealth(int health) {
		this.health = health;
	}



	public int getMaxhealth() {
		return maxhealth;
	}



	public void setMaxhealth(int maxhealth) {
		this.maxhealth = maxhealth;
	}
	
	
	public ArrayList<Heart> getHearts() {
		return hearts;
	}



	public void setHearts(ArrayList<Heart> hearts) {
		this.hearts = hearts;
	}




}
