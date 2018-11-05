package Game;

import GameEngine.GameEngine;

public class Camera
{
	private int offX=0,offY=0;
	public Camera()
	{
		
	}
	public void update(GameEngine ge, float dt)
	{
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
		
		if(offX>0 && GameManager.End)
		{
			Game.Rooms.A_ClassSelect.canNext= Game.Rooms.B_StatSelect.canNext=false;
			offX=0;
			Scripts.restart();
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