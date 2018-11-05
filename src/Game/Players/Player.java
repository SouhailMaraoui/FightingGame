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

public class Player extends GameObject
{
	private static Classe p1;
	private static Classe p2;
	
	private Image player,missed;
	private Image[] image;
	private int[][] imagePos;
	private int px,py;
	
	private boolean Missed;
	private boolean parrying=false;
	public static int toBeParried=0,myDamage=0,myHeal=0;
	
	public static int[] playerAction= {0,0};
	
	public static boolean[][] T;

	private Weapon[][] weapon=Classe.getWeapon();
	
	public static int Fcount=0;
	
	public Player()
	{
		//hit=new Image("/UI/Info/Hit.png");
		//chance=new Image("/UI/Info/Chance.png");
		
		p1=A_ClassSelect.getPlayer();
		p2=Enemy.getP2();
		
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
		}
		
		T= C_Arena.T2;

		
		
		for(int i=0;i<T.length;i++)
		{
			for(int j=0;j<T[0].length;j++)
			{
				if(weapon[i][j]!=null)
				{
					k+=1;
					if(C_Arena.T1[i]&& T[i][j])
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
								if(myDamage==0) Missed=true;
								if(myDamage>Enemy.getToBeParried())
								{p2.setVitality(p2.getVitality()+Enemy.getToBeParried()-myDamage);}
								
							}
							if(i==1) {parry();}
							if(i==2) 
							{
								myHeal=Scripts.ifHit(playerAction[0],playerAction[1]);
								p1.setVitality(p1.getVitality()+myHeal);
								if(myHeal==0) Missed=true;
							}
							
							if(Fcount>0)
							{
								player=image[k];
								px=imagePos[k][0];py=imagePos[k][1];
							}
							Enemy.AITurn=true;
							
						}
					}
				}
			}
		}
		if(Fcount>0) {Fcount-=1;}
		
		if(p1.getVitality()<=0)
		{
			GameManager.End=true;
		}
		
		
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
		if(toBeParried!=0)
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