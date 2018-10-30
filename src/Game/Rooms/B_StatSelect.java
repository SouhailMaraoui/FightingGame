package Game.Rooms;

import Game.GameObject;
import GameEngine.GameEngine;
import GameEngine.Renderer;
import GameEngine.GFX.Image;
import classes.Class;
//import classes.warrior;

public class B_StatSelect extends GameObject
{		
	private Class player;

	int i=0;
	int[] stat= {20,20,20,20,20,20};
	
	public B_StatSelect()
	{

	}

	public void update(GameEngine ge, float dt)
	{
	}
	
	/*public void end(A_ClassSelect CS)
	{

		player=new Class(stat[0],stat[1],stat[2],stat[3],stat[4],stat[5]);
	}*/
	
	public void render(GameEngine ge, Renderer r)
	{
		stat[0]=r.setStat(stat[0], 1400, 100);
		stat[1]=r.setStat(stat[1], 1400, 150);
		stat[2]=r.setStat(stat[2], 1400, 200);
		stat[3]=r.setStat(stat[3], 1400, 250);
	}

	public Class getPlayer()
	{
		return player;
	}
}
