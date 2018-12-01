package Game.Players;

import java.util.Random;

import Classes.Classe;
import Classes.Weapon;
import Game.GameObject;
import Game.Scripts;
import Game.Rooms.A_ClassSelect;
import GameEngine.GameEngine;
import GameEngine.Renderer;
import GameEngine.GFX.Image;

public class AI_Left extends GameObject
{
	public static int myDamage=0,myHeal=0;
	public static int Fcount;

	private static Classe p1,p2;

	private Weapon[][] weapon;
	private Image[] image;
	private Image player,missed;
	private Random r=new Random();
	
	private int[][] imagePos;
	private int px,py;
	private int k,i=0;
	private int p1vit,p2vit;
	
	private boolean parrying=false;

	public AI_Left()
	{
		Fcount=0;
		
		missed=new Image("/UI/Missed.png");
		
		p1=A_ClassSelect.getP1();p1.setMyTurn(true);
		p2=A_ClassSelect.getP2();
				
		imagePos=p1.getImagePos();
		image=p1.getImage();
		weapon=p1.getWeapon();
	}
	
	public void update(GameEngine ge, float dt)
	{		
		if(parrying && p1.isMyTurn())
		{
			parrying=false;
			p1.setToBeParried(0);
		}
		if(Fcount==0)	
		{
			if (!parrying){player=image[0];px=imagePos[0][0];py=imagePos[0][1];}
			p1.setMissed(false);
			p1.setDrawSpell(false);
		}
		if(p1.isMyTurn())
		{
			if(Fcount==0)
			{
				Fcount=200;
				i=r.nextInt(100);
				p2vit=p2.getVitality();
				p1vit=p1.getVitality();
			}
			int j=i;
			if(21<Fcount && Fcount<80)
			{
				Ai(j,p1vit,p2vit);
			}
			if(Fcount<2)
			{
				p1.setMyTurn(false);
				p2.setMyTurn(true);
			}
		}

		if(Fcount>0)	{Fcount-=1;}	
	}
	
	public void Ai(int i, int p1vit, int p2vit)
	{
		if(Fcount==70)
		{
			if(p2vit<40)
			{
				attack();
			}
			else if(80<p1vit)
			{
				attack();
			}
			
			else if(30<p1vit)
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

	public void attack()
	{
		int i=0;
		if(weapon[0][1]!=null)	{i=r.nextInt(1);}
		
		int [] playerAction=p1.Attack(weapon[0][i]);
		
		myDamage=Scripts.ifHit(playerAction[0], playerAction[1]);
		
		if (myDamage==0)	{p1.setMissed(true);}
		else if(weapon[0][i].getTag()=="Staff") p1.setDrawSpell(true);
		if(myDamage>p2.getToBeParried()) 	{p2.setVitalityRelative(p2.getToBeParried()-myDamage);}

		k=Scripts.imgIndex(p1,0,i);
		player=image[k];px=imagePos[k][0];py=imagePos[k][1];
	}
	
	public void parry()
	{
		p1.setMyTurn(false);
		p2.setMyTurn(true);
		parrying=true;

		int i=0;
		if(weapon[1][1]!=null)	{i=r.nextInt(1);}
		
		int [] playerAction=p1.Parry(weapon[1][i]);
		
		p1.setToBeParried(Scripts.ifHit(playerAction[0],playerAction[1]));
		if(p1.getToBeParried()==0) {p1.setMissed(true);parrying=false;}
		
		k=Scripts.imgIndex(p1,1,i);
		player=image[k];px=imagePos[k][0];py=imagePos[k][1];
	}
	
	public void heal()
	{
		myHeal=Scripts.ifHit(p1.Heal(weapon[2][0])[0],p1.Heal(weapon[2][0])[1]);
		if(myHeal==0)	{p1.setMissed(true);}
		p1.setVitality(Scripts.min(p1.getVitality()+myHeal,p1.getInitVitality()));

		k=Scripts.imgIndex(p1,2,0);
		player=image[k];px=imagePos[k][0];py=imagePos[k][1];
	}
	
	public void render(GameEngine ge, Renderer r)
	{
		r.drawImage(player, px, py);
		if(p1.getToBeParried()>0)
		{
			r.drawNumber(p1.getToBeParried(), 2100, 70);
		}		
		if(p1.isMissed())
		{
			r.drawImage(missed, 2350,100);
		}
	}

	public static int getToBeParried()
	{
		return p1.getToBeParried();
	}
}