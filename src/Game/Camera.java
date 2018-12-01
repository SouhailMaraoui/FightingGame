package Game;

import Game.Rooms.*;
import GameEngine.GameEngine;

public class Camera
{
	private int offX=0,offY=0;
	
	public Camera()	{}
	
	public void update(GameEngine ge, float dt)
	{
		if(GameManager.EB && Game.Rooms.A_ClassSelect.canNext)
		{
			offX=1000;
			GameManager.objects.add(new Game.Rooms.B_StatSelect());
			Game.Rooms.A_ClassSelect.canNext=false;
		}
		if((GameManager.EB || Scripts.isClicked(B_StatSelect.confirm, 1400, 325)) && Game.Rooms.B_StatSelect.canNext)
		{
			offX=2000;
			if		(A_ClassSelect.getMode()=="PvP") {GameManager.objects.add(new C_Arena_PvP());}
			else if	(A_ClassSelect.getMode()=="PvA") {GameManager.objects.add(new C_Arena_PvA());}
			else if	(A_ClassSelect.getMode()=="AvA") {GameManager.objects.add(new C_Arena_AvA());}

			Game.Rooms.B_StatSelect.canNext=false;
		}
		if(Game.Rooms.C_Arena_PvP.canNext || Game.Rooms.C_Arena_PvA.canNext || Game.Rooms.C_Arena_AvA.canNext)
		{		
			try{Thread.sleep(3000);}
			catch (InterruptedException e){e.printStackTrace();}
			
			GameManager.End=true;
		}
		
		if(offX>0 && GameManager.End)
		{
			Game.Rooms.A_ClassSelect.canNext= Game.Rooms.B_StatSelect.canNext=C_Arena_PvP.canNext=C_Arena_PvA.canNext=C_Arena_AvA.canNext=false;
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