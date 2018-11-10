package ca.drgame.entities;

import ca.graphics.Colours;
import ca.graphics.Screen;
import tiles.Tile;

public class Heart {
	
	private HeartStatus  status;
	private int position = 0;
	
	public Heart(HeartStatus status, int position)
	{
		this.position = position;
		this.status = status;
	}

	public HeartStatus getStatus() {
		return status;
	}

	public void setStatus(HeartStatus status) {
		this.status = status;
	}	

	public int getPosition()
	{
		return this.position;
	}
	
	public void setPosition(int position)
	{
		this.position = position;
	}
	
	public void render(Screen screen)
	{
		if(this.status==HeartStatus.FULL)
		{screen.renderoff(screen.width-(screen.getHealthOffset()+position*8),0, 0+6*32, Colours.get(-1, 100, 400, 555), 0, 1);}
		else
		{
			{screen.renderoff(screen.width-(screen.getHealthOffset()+position*8),0, 1+6*32, Colours.get(-1, 100, 400, 555), 0, 1);}
		}
	}

}
