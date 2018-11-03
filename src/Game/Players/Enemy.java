package Game.Players;

import Classes.Classe;
import Game.GameObject;
import Game.Rooms.A_ClassSelect;
import Game.Rooms.C_Arena;
import GameEngine.GameEngine;
import GameEngine.Renderer;
import GameEngine.GFX.Image;

public class Enemy extends GameObject
{
	private Classe p1;
	private static Classe p2;
	private Image enemy,I,A,P,H;
	private int px,py;
	private int Ix,Iy;
	private int Ax,Ay;
	private int Px,Py;
	private int Hx,Hy;

	public static boolean AITurn=false;
	private boolean parrying=false;
	private static int toBeParried=0;
	
	
	private int vitality=200;
	
	private int Fcount=0;
	private boolean Aing,Hing,Ping;
	
	public Enemy()
	{
		p1=A_ClassSelect.getPlayer();
		p2=new Classe();
		p2.setStat(60,60, 60, 60);
		p2.setVitatlity(vitality);
		Ix=2100;Iy=100;
		Ax=2100;Ay=100;
		Px=2100;Py=100;
		Hx=2100;Hy=100;
		enemy=I=new Image("/Sprites/Athlete/Arena/Idle.png");
		A=new Image("/Sprites/Athlete/Arena/Attack_Staff.png");
		P=new Image("/Sprites/Athlete/Arena/Parry.png");
		H=new Image("/Sprites/Athlete/Arena/Heal.png");
	}
	
	public void update(GameEngine ge, float dt)
	{
		vitality=p2.getVitatlity();
		
		if(parrying && AITurn)
		{
			parrying=false;
			toBeParried=0;
		}
		
		if(Fcount==0 && !parrying)	{enemy=I;}

		if(!C_Arena.myTurn)
		{
			if(Fcount==0){Fcount=150;}
			if(21<Fcount && Fcount<80)
			{
				if(150<vitality)
				{
					enemy=A;
					if(Player.getToBeParried()<50)
					{
						if(Fcount==50)p1.setVitatlity(p1.getVitatlity()+Player.getToBeParried()-50);
					}
				}
				if(50<vitality && vitality<=150)
				{
					enemy=P;
					AITurn=false;
					parry();
				}
				if(vitality<=50)
				{
					enemy=H;
					if(Fcount==50)p2.setVitatlity(p2.getVitatlity()+50);
				}
			}
			if(Fcount<2)
			{
				C_Arena.myTurn=true;
				AITurn=false;
			}
		}

		if(Fcount>0)	{Fcount-=1;}
	}
	
	public void parry()
	{
		parrying=true;
		toBeParried=30;
	}
	
	public void render(GameEngine ge, Renderer r)
	{
		px=Ix;py=Iy;
		if(Fcount>0)
		{
			if(Aing){enemy=A;px=Ax;py=Ay;}
			else if(Ping) {enemy=P;px=Px;py=Py;}
			else if(Hing) {enemy=H;px=Hx;py=Hy;}
		}
		r.drawImageReversed(enemy, px+600, py);
		
		r.drawNumber(p2.getVitatlity(), 2900, 0);
		
		if(toBeParried!=0)
		{
			r.drawNumber(toBeParried, 2550, 0);
		}
	}

	public static Classe getP2()
	{
		return p2;
	}

	public static int getToBeParried()
	{
		return toBeParried;
	}
}