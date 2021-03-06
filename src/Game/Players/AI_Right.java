package Game.Players;

import java.util.Random;

import Classes.Fighter;
import Classes.Weapon;
import Game.GameObject;
import Game.Scripts;
import Game.Rooms.A_ClassSelect;
import GameEngine.GameEngine;
import GameEngine.Image;
import GameEngine.Renderer;

public class AI_Right extends GameObject
{
	public static int myDamage=0,myHeal=0;
	public static int Fcount;

	private static Fighter p1,p2;

	private Weapon[][] weapon;
	private Image[] image;
	private Image player,missed;
	private Random r=new Random();
	
	private int[][] imagePos;
	private int px,py;
	private int k,i=0;
	private int p1vit,p2vit;
	
	private boolean parrying=false;
	
	public AI_Right()
	{
		Fcount=0;
		
		missed=new Image("/UI/Missed.png");
		
		p1=A_ClassSelect.getP1();
		p2=A_ClassSelect.getP2();
				
		imagePos=p2.getImagePos();
		image=p2.getImage();
		weapon=p2.getWeapon();	
	}
	
	public void update(GameEngine ge, float dt)
	{		
		if(parrying && p2.isMyTurn())
		{
			parrying=false;
			p2.setToBeParried(0);
		}
		if(Fcount==0)	
		{
			if (!parrying){player=image[0];px=imagePos[0][0];py=imagePos[0][1];}
			p2.setMissed(false);
			p2.setDrawSpell(false);
		}
		if(p2.isMyTurn())
		{
			if(Fcount==0)
			{
				Fcount=200;
				i=r.nextInt(100);
				p1vit=p1.getVitality();
				p2vit=p2.getVitality();;
			}
			int j=i;
			if(21<Fcount && Fcount<80)
			{
				Ai(j,p2vit,p1vit);
			}
		}

		if(Fcount>0)	{Fcount-=1;}	
	}
	
	public void render(GameEngine ge, Renderer r)
	{
		r.drawImageReversed(player, 4800-px, py);
		if(p2.getToBeParried()>0)
		{
			r.drawNumber(p2.getToBeParried(), 2820, 70);
		}		
		if(p2.isMissed())
		{
			r.drawImage(missed, 2350,100);
		}
	}
	
	public void Ai(int i, int p2vit, int p1vit)
	{
		if(Fcount==70)
		{
			p2.setMyTurn(false);
			p1.setMyTurn(true);
			
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
	
	public void attack()
	{
		
		int i=0;
		if(weapon[0][1]!=null)	{i=r.nextInt(2);}
		
		int [] playerAction=p2.Attack(weapon[0][i]);
		
		myDamage=Scripts.ifHit(playerAction[0], playerAction[1]);
		
		if (myDamage==0)	{p2.setMissed(true);}
		else if(weapon[0][i].getTag()=="Staff") p2.setDrawSpell(true);
		if(myDamage>p1.getToBeParried()) 	{p1.setVitalityRelative(p1.getToBeParried()-myDamage);}

		k=Scripts.imgIndex(p2,0,i);
		player=image[k];px=imagePos[k][0];py=imagePos[k][1];
		if(p2.getTag()=="Warrior") px+=200;
	}	
	
	public void parry()
	{
		parrying=true;
		
		int i=0;
		if(weapon[1][1]!=null)	{i=r.nextInt(2);}

		int [] playerAction=p2.Parry(weapon[1][i]);
		
		p2.setToBeParried(Scripts.ifHit(playerAction[0],playerAction[1]));
		if(p2.getToBeParried()==0) {p2.setMissed(true);parrying=false;}
		
		k=Scripts.imgIndex(p2,1,i);
		player=image[k];px=imagePos[k][0];py=imagePos[k][1];
	}
	
	public void heal()
	{
		int [] playerAction=p2.Heal(weapon[2][0]);
		myHeal=Scripts.ifHit(playerAction[0],playerAction[1]);
		
		if(myHeal==0)	{p2.setMissed(true);}
		p2.setVitality(Scripts.min(p2.getVitality()+myHeal,p2.getInitVitality()));

		k=Scripts.imgIndex(p2,2,0);
		player=image[k];px=imagePos[k][0];py=imagePos[k][1];
	}

	public static int getToBeParried()
	{
		return p2.getToBeParried();
	}
}