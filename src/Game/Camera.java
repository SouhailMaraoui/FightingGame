package Game;

import java.awt.event.KeyEvent;

import GameEngine.GameEngine;

public class Camera
{
	private int offX=0,offY=0;
	public Camera()
	{
		
	}
	public void update(GameEngine ge, float dt)
	{
		
		if(ge.getInput().isKey(KeyEvent.VK_1))
		{
			offX=0;
		}
		if(ge.getInput().isKey(KeyEvent.VK_2))
		{
			offX=1000;
			GameManager.objects.add(new Game.Rooms.B_StatSelect());
		}
		if(ge.getInput().isKey(KeyEvent.VK_3))
		{
			offX=2000;
			GameManager.objects.add(new Game.Rooms.C_Arena());
		}
		
		if(GameManager.EB && Game.Rooms.A_ClassSelect.canNext)
		{
			offX=1000;
			GameManager.objects.add(new Game.Rooms.B_StatSelect());
			Game.Rooms.A_ClassSelect.canNext=false;
		}
		if(GameManager.EB && Game.Rooms.B_StatSelect.canNext)
		{
			offX=2000;
			GameManager.objects.add(new Game.Rooms.C_Arena());
			Game.Rooms.B_StatSelect.canNext=false;
		}
		
		ge.getRenderer().setCamX((int)offX);
		ge.getRenderer().setCamY((int)offY);
	}
	public int getOffX()
	{
		return offX;
	}
	public int getOffY()
	{
		return offY;
	}
	public void setOffX(int offX)
	{
		this.offX = offX;
	}
}