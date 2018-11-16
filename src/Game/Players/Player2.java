package Game.Players;

import java.util.Random;

import Classes.Classe;
import Classes.Weapon;
import Game.GameObject;
import Game.Scripts;
import Game.Rooms.A_ClassSelect;
import Game.Rooms.C_Arena;
import GameEngine.GameEngine;
import GameEngine.Renderer;
import GameEngine.GFX.Image;

public class Player2 extends GameObject
{
	private static Classe p1;
	private static Classe p2;
	
	private Random r=new Random();
	
	private Image enemy,missed;
	private Image[] image;
	private int[][] imagePos;
	private int px,py;
	private int k;
	
	private int i=0,p1vit,p2vit;
	
	public static boolean Missed;
	public static boolean AITurn=false;
	public static boolean drawSpell=false;
	private boolean parrying=false;
	public static int toBeParried=0,myDamage=0,myHeal=0;
	
	public static int[] playerAction= {0,0};
	
	public static boolean[][] T;

	private Weapon[][] weapon;
	
	public static int Fcount;
	
	public Player2()
	{
		Fcount=0;
				
		p2=A_ClassSelect.getP2();
		weapon=p2.getWeapon();
		p1=A_ClassSelect.getP1();
		
		imagePos=p2.getImagePos();
		image=p2.getImage();
		
		missed=new Image("/UI/Missed.png");
	}
	
	public void update(GameEngine ge, float dt)
	{
		int vitality=p2.getVitality();
		
		if(parrying && AITurn)
		{
			parrying=false;
			toBeParried=0;
		}
		
		if(Fcount==0)	
		{
			if (!parrying)
				{enemy=image[0];px=imagePos[0][0];py=imagePos[0][1];}
			Missed=false;
			drawSpell=false;
		}
		
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
		if(Fcount==70)
		{
			if(p1vit<40)
			{
				attack();
			}
			else if(80<p2vit)
			{
				attack();
			}
			
			else if(30<p2vit)
			{
				if(i<60)
				{
					attack();
				}
				else if(i<75)
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
	}
	
	public void heal()
	{
		
		System.out.println("H");
		myHeal=Scripts.ifHit(p2.Heal(weapon[2][0])[0],p2.Heal(weapon[2][0])[1]);
		if(myHeal==0)
		{
			Missed=true;
		}

		k=imgIndex(2,0);
		enemy=image[k];px=imagePos[k][0];py=imagePos[k][1];
		p2.setVitality(Scripts.min(p2.getVitality()+myHeal,p2.getInitVitality()));
		
	}
	public void attack()
	{

		System.out.println("A");
		int i=0;
		if(weapon[0][1]!=null)
		{
			i=r.nextInt(1);
		}
		int [] playerAction=p2.Attack(weapon[0][i]);
		if(weapon[0][i].getTag()=="Staff") drawSpell=true;
		myDamage=Scripts.ifHit(playerAction[0], playerAction[1]);
		if (myDamage==0)
		{
			Missed=true;
		}
		k=imgIndex(0,i);
		enemy=image[k];px=imagePos[k][0];py=imagePos[k][1];
		if(p2.getTag()=="Warrior") px+=200;
		if(myDamage>Player1.getToBeParried()) 	{p1.setVitalityRelative(Player1.getToBeParried()-myDamage);}
	}	
	public void parry()
	{
		AITurn=false;
		System.out.println("P");
		int i=0;
		if(weapon[1][1]!=null)
		{
			i=r.nextInt(1);
		}
		parrying=true;
		int [] playerAction=p2.Parry(weapon[1][i]);
		toBeParried=Scripts.ifHit(playerAction[0],playerAction[1]);
		if(toBeParried==0) Missed=true;
		
		k=imgIndex(1,i);
		enemy=image[k];px=imagePos[k][0];py=imagePos[k][1];

	}
	
	public void render(GameEngine ge, Renderer r)
	{
		r.drawImageReversed(enemy, 4800-px, py);
		if(toBeParried>0)
		{
			r.drawNumber(toBeParried, 2820, 70);
		}
		if(Missed)
		{
			r.drawImage(missed, 2350,100);
		}
	}

	public static int getToBeParried()
	{
		return toBeParried;
	}

	private int imgIndex(int i,int j)
	{
		if(p2.getTag()=="Warrior")
		{
			switch(i)
			{
			case 0:k=1; break;
			case 1:k=2+j; break;
			case 2:k=4; break;
			}
		}
		if(p2.getTag()=="Athlete")
		{
			switch(i)
			{
			case 0:k=1+j; break;
			case 1:k=3; break;
			case 2:k=4; break;
			}
		}if(p2.getTag()=="Mage")
		{
			switch(i)
			{
			case 0:k=1; break;
			case 1:k=2; break;
			case 2:k=3; break;
			}
		}
		return k;
	}
}