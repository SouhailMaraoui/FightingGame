package Game.Players;

import Classes.Classe;
import Game.GameObject;
import Game.Rooms.A_ClassSelect;
import Game.Rooms.C_Arena;
import GameEngine.GameEngine;
import GameEngine.Renderer;
import GameEngine.GFX.Image;
import java.util.Random;

public class Enemy extends GameObject
{
	private Classe p1;
	private Random r=new Random();
	private static Classe p2;
	public static Image enemy;
	private Image I;
	private Image A1;
	public static Image A2;
	private Image P;
	private Image H;
	private Image logo;
	private int px,py;
	private int Ix,Iy;
	private int A1x,A1y;
	private int A2x,A2y;
	private int Px,Py;
	private int Hx,Hy;
	
	private int i=0;
	private int p1vit;
	private int p2vit;
	public static boolean AITurn=false;
	private boolean parrying=false;
	private static int toBeParried=0;
	
	private int vitality=147;
	private int damage1=45,damage2=40;
	private int heal=30;
	
	private int Fcount=0;
	
	public Enemy()
	{
		p1=A_ClassSelect.getP1();
		p2=new Classe();
		p2.setVitality(vitality);
		Ix=2700;Iy=100;
		A1x=2200;A1y=100;
		A2x=2700;A2y=100;
		Px=2700;Py=100;
		Hx=2700;Hy=100;
		enemy=I=new Image("/Sprites/Enemy/Idle.png");
		A2=new Image("/Sprites/Enemy/Attack_Staff.png");
		A1=new Image("/Sprites/Enemy/Attack_Dagger.png");
		P=new Image("/Sprites/Enemy/Parry.png");
		H=new Image("/Sprites/Enemy/Heal.png");
		logo=new Image("/Sprites/Athlete/Logo.png");
	}
	
	public void update(GameEngine ge, float dt)
	{
		vitality=p2.getVitality();
		
		if(parrying && AITurn)
		{
			parrying=false;
			toBeParried=0;
		}
		
		if(Fcount==0 && !parrying)	{enemy=I;px=Ix;py=Iy;}
		
		if(!C_Arena.myTurn)
		{
			if(Fcount==0){Fcount=200;i=r.nextInt(100);p1vit=p1.getVitality();p2vit=vitality;}
			int j=i;
			if(21<Fcount && Fcount<80)
			{
				AI(j,p1vit,p2vit);
			}
			if(Fcount<2)
			{
				C_Arena.myTurn=true;
				AITurn=false;
			}
		}

		if(Fcount>0)	{Fcount-=1;}
	}
	

	
	public void AI(int i, int p1vit, int p2vit)
	{
		if(p1vit<damage1)
		{
			attack1();
		}
		else if(100<p2vit)
		{
			if(i<50)
			{
				attack1();
			}
			else if(i<90)
			{
				attack2();
			}
			else 
			{
				parry();
			}
		}
		
		else if(30<p2vit)
		{
			if(i<25)
			{
				attack1();
			}
			else if(i<50)
			{
				attack2();
			}
			else if(i<70)
			{
				parry();
			}
			else 
			{
				heal();
			}
		}
		else 
		{
			heal();
		}
	}
	
	public void render(GameEngine ge, Renderer r)
	{
		r.drawImageReversed(enemy, px, py);
		
		if(toBeParried!=0)
		{
			r.drawNumber(toBeParried, 2850, 70);
		}
		
		
		//Draw-Health---------------------------------------------------------------------------
		r.drawImage(logo, 2940,10);
		r.drawRectangle(2940, 23, p2.getVitality()+toBeParried, 24, 0xff1f1f1f,-1);
		r.drawRectangle(2940, 25, p2.getVitality(), 20, 0xff88313C,-1);
		r.drawRectangle(2940-p2.getVitality(), 25, toBeParried, 20, 0xff23679F,-1);
		r.drawRectangle(2940, 42, p2.getVitality()+toBeParried, 3, 0xff8C202F,-1);

		if(p2.getVitality()>=0)r.drawNumber(p2.getVitality(), 2920, 70);
	}

	public static Classe getP2()
	{
		return p2;
	}

	public static int getToBeParried()
	{
		return toBeParried;
	}

	public void attack1()
	{
		enemy=A1; px=A1x ;py=A1y;
		if(Player1.getToBeParried()<damage1)
		{
			if(Fcount==50)p1.setVitality(p1.getVitality()+Player1.getToBeParried()-damage1);
		}
	}
	public void attack2()
	{
		enemy=A2; px=A2x ;py=A2y;
		if(Player1.getToBeParried()<damage2)
		{
			if(Fcount==50)p1.setVitality(p1.getVitality()+Player1.getToBeParried()-damage2);
		}
	}

	public void parry()
	{
		enemy=P; px=Px ;py=Py;
		AITurn=false;
		parrying=true;
		toBeParried=40;
	}
	
	public void heal()
	{
		enemy=H; px=Hx ;py=Hy;
		if(Fcount==50)p2.setVitality(p2.getVitality()+heal);
	}
}