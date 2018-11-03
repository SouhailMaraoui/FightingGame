package Game.Players;

import Classes.Classe;
import Game.GameObject;
import GameEngine.GameEngine;
import GameEngine.Renderer;
import GameEngine.GFX.Image;

public class Enemy extends GameObject
{
	private static Classe p2;
	private Image enemy,I,A,P,H;
	private int px,py;
	private int Ix,Iy;
	private int Ax,Ay;
	private int Px,Py;
	private int Hx,Hy;
	
	
	private int Fcount=0;
	private boolean Aing,Hing,Ping;
	
	public Enemy()
	{
		p2=new Classe();
		p2.setStat(60,60, 60, 60);
		p2.setVitatlity(200);
		Ix=2100;Iy=100;
		Ax=2100;Ay=100;
		Px=2100;Py=100;
		Hx=2100;Hy=100;
		I=new Image("/Sprites/Athlete/Arena/Idle.png");
		A=new Image("/Sprites/Athlete/Arena/Attack_Staff.png");
		P=new Image("/Sprites/Athlete/Arena/Parry.png");
		H=new Image("/Sprites/Athlete/Arena/Heal.png");
	}
	
	public void update(GameEngine ge, float dt)
	{
	}
	
	public void render(GameEngine ge, Renderer r)
	{
		enemy=I;
		px=Ix;py=Iy;
		if(Fcount>0)
		{
			if(Aing){enemy=A;px=Ax;py=Ay;}
			else if(Ping) {enemy=P;px=Px;py=Py;}
			else if(Hing) {enemy=H;px=Hx;py=Hy;}
		}
		r.drawImageReversed(enemy, px+600, py);
		
		r.drawNumber(p2.getVitatlity(), 2900, 0);
	}

	public static Classe getP2()
	{
		return p2;
	}
}