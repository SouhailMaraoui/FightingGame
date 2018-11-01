package Game.Players;

import Game.GameObject;
import Game.Rooms.A_ClassSelect;
import Game.Rooms.C_Arena;
import GameEngine.GameEngine;
import GameEngine.Renderer;
import GameEngine.GFX.Image;

public class Player extends GameObject
{
	private Image player,I,A,P,H;
	private int px,py;
	private int Ix,Iy;
	private int Ax,Ay;
	private int Px,Py;
	private int Hx,Hy;
	
	
	private int Fcount=0;
	private boolean Aing,Hing,Ping;
	
	public Player()
	{
		if (A_ClassSelect.classTag=="Warrior")
		{
			I=new Image("/Sprites/Warrior/Arena/Idle.png");			Ix=2100;Iy=100;
			A=new Image("/Sprites/Warrior/Arena/Attack.png");		Ax=2500;Ay=100;
			P=new Image("/Sprites/Warrior/Arena/Parry.png");		Px=2100;Py=100;
			H=new Image("/Sprites/Warrior/Arena/Heal.png");			Hx=2090;Hy=3;
		}
		if (A_ClassSelect.classTag=="Athlete")
		{
			I=new Image("/Sprites/Athlete/Arena/Idle.png");			Ix=2100;Iy=100;
			A=new Image("/Sprites/Athlete/Arena/Attack_Staff.png");	Ax=2100;Ay=100;
			P=new Image("/Sprites/Athlete/Arena/Parry.png");		Px=2100;Py=100;
			H=new Image("/Sprites/Athlete/Arena/Heal.png");			Hx=2100;Hy=100;
		}
		if (A_ClassSelect.classTag=="Mage")
		{
			I=new Image("/Sprites/Mage/Arena/Idle.png");			Ix=2100;Iy=100;
			A=new Image("/Sprites/Mage/Arena/Attack_Staff.png");	Ax=2100;Ay=50;
			P=new Image("/Sprites/Mage/Arena/Parry.png");			Px=2100;Py=100;
			H=new Image("/Sprites/Mage/Arena/Heal.png");			Hx=2100;Hy=100;
		}
	}
	
	public void update(GameEngine ge, float dt)
	{
		if(C_Arena.Aing)
		{
			Fcount=60;
			C_Arena.Aing=false;
			Aing=true;
		}
		
		if(C_Arena.Ping)
		{
			Fcount=60;
			C_Arena.Ping=false;
			Ping=true;
		}
		
		if(C_Arena.Hing)
		{
			Fcount=60;
			C_Arena.Hing=false;
			Hing=true;
		}
		if(Fcount>0) Fcount-=1;
		else {Aing=Ping=Hing=false;}
	}
	
	public void render(GameEngine ge, Renderer r)
	{
		player=I;
		px=Ix;py=Iy;
		if(Fcount>0)
		{
			if(Aing){player=A;px=Ax;py=Ay;}
			else if(Ping) {player=P;px=Px;py=Py;}
			else if(Hing) {player=H;px=Hx;py=Hy;}
		}
		r.drawImage(player, px, py);
	}
}