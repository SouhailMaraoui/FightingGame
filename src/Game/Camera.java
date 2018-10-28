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
		
		if(ge.getInput().isKey(KeyEvent.VK_D))
		{
			offX+=5;
		}
		if(ge.getInput().isKey(KeyEvent.VK_A))
		{
			offX-=5;
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
}
