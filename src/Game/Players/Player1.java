package Game.Players;

import Classes.Classe;
import Classes.Weapon;
import Game.GameManager;
import Game.GameObject;
import Game.Scripts;
import Game.Rooms.A_ClassSelect;
import Game.Rooms.C_Arena;
import GameEngine.GameEngine;
import GameEngine.Renderer;
import GameEngine.GFX.Image;

public class Player1 extends GameObject
{
	private static Classe p1;
	private static Classe p2;
	
	private Image player,missed,spell;
	private Image[] image;
	private int[][] imagePos;
	private int px,py;
	
	public static boolean Missed;
	private boolean drawSpell=false;
	private boolean parrying=false;
	public static int toBeParried=0,myDamage=0,myHeal=0;
	
	public static int[] playerAction= {0,0};
	
	public static boolean[][] T;

	private Weapon[][] weapon;
	
	public static int Fcount;
	
	public Player1()
	{
		Fcount=0;
		
		spell=new Image("/Sprites/Spell.png");
		
		p1=A_ClassSelect.getP1();
		weapon=p1.getWeapon();
		p2=A_ClassSelect.getP2();
		
		imagePos=p1.getImagePos();
		image=p1.getImage();
		
		missed=new Image("/UI/Missed.png");
	}
	
	public void update(GameEngine ge, float dt)
	{
		GameManager.End=false;
		
		int k=0;
		
		if(parrying && C_Arena.myTurn)
		{
			parrying=false;
			toBeParried=0;
		}
		
		if(Fcount==0 && !parrying)
		{
			player=image[0];
			px=imagePos[0][0];py=imagePos[0][1];
			Missed=false;
			drawSpell=false;
		}
		
		T= C_Arena.T2;
		
		for(int i=0;i<T.length;i++)
		{
			for(int j=0;j<T[0].length;j++)
			{
				if(weapon[i][j]!=null)
				{
					k+=1;
					if(C_Arena.T1[i] && T[i][j])
					{
						if(i==0) {playerAction=p1.Attack(weapon[i][j]);}
						if(i==1) {playerAction=p1.Parry(weapon[i][j]);}
						if(i==2) {playerAction=p1.Heal(weapon[i][j]);}
						
						if(GameManager.MB )
						{
							
							C_Arena.myTurn=false;
							C_Arena.weaponList.clear();
							Fcount=60;
							C_Arena.T1[i]=C_Arena.T2[i][j]=false;
							C_Arena.activePointer=false;
							
							if(i==0) 
							{
								myDamage=Scripts.ifHit(playerAction[0],playerAction[1]);
								
								if(myDamage==0) 						{Missed=true;}
								if(myDamage>Player2.getToBeParried()) 	{p2.setVitalityRelative(Player2.getToBeParried()-myDamage);}
								if(weapon[i][j].getTag()=="Staff")		{drawSpell=true;}
							}
							if(i==1) {parry();}
							if(i==2) 
							{
								myHeal=Scripts.ifHit(playerAction[0],playerAction[1]);
								
								p1.setVitality(Scripts.min(p1.getVitality()+myHeal,p1.getInitVitality()));
								if(myHeal==0) Missed=true;
							}
							
							if(Fcount>0)
							{
								player=image[k];
								px=imagePos[k][0];py=imagePos[k][1];
							}
							Player2.AITurn=true;
							
						}
					}
				}
			}
		}
		if(Fcount>0) {Fcount-=1;}		
	}
	
	public void parry()
	{
		parrying=true;
		toBeParried=Scripts.ifHit(playerAction[0],playerAction[1]);
		if(toBeParried==0) Missed=true;
	}
	
	public void render(GameEngine ge, Renderer r)
	{
		r.drawImage(player, px, py);
		if (drawSpell) r.drawImage(spell, 2700,100);
		if(toBeParried>0)
		{
			r.drawNumber(toBeParried, 2100, 70);
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
}