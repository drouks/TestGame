package Levels;

import java.util.ArrayList;
import java.util.List;

import ca.drgame.entities.LevelGoal;
import ca.drgame.entities.Player;

public class LevelProgression {
	
public List<Level> levels = new ArrayList<Level>();
public int levelIndex = 0;
public Player player;
public int maxlevels;
public Level lastlevel;

public LevelProgression()
{
	Level firstlevel = new Level("/levels/small_test_level.png",0);
	firstlevel.addEntity(new LevelGoal(firstlevel,6,6));
	Level secondlevel = new Level("/levels/water_test_level.png",1);
	secondlevel.addEntity(new LevelGoal(secondlevel, 3,3));
	Level thirdlevel = new Level("/levels/water_test_level2.png",2);
	thirdlevel.addEntity(new LevelGoal(thirdlevel, 30,30));
	Level fourthlevel = new Level("/levels/final_test_level.png",3);
	fourthlevel.addEntity(new LevelGoal(fourthlevel,58,51));
	Level fifthlevel = new Level("/levels/level_road_test.png",4);
	fifthlevel.addEntity(new LevelGoal(fifthlevel,25,25));
	this.levels.add(firstlevel);
	this.levels.add(secondlevel);
	this.levels.add(thirdlevel);
	this.levels.add(fourthlevel);
	this.levels.add(fifthlevel);
	this.maxlevels = this.levels.size();
	this.lastlevel = fifthlevel;

}

public Level getCurrentLevel()
{
	return levels.get(this.levelIndex);
}

public void goToNext()
{
	levelIndex++;
}

public void setPlayer(Player player)
{
	this.player = player;
}

public Player getPlayer()
{
	return this.player;
}



}
