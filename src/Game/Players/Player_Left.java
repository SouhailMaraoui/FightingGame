package Game.Players;

import java.util.ArrayList;

import Classes.Fighter;
import Classes.Weapon;
import Game.GameManager;
import Game.GameObject;
import Game.Scripts;
import Game.Rooms.A_ClassSelect;
import Game.Rooms.C_Arena_PvP;
import Game.Rooms.C_Arena_PvA;
import GameEngine.GameEngine;
import GameEngine.Image;
import GameEngine.Renderer;

public class Player_Left extends GameObject
{
	private static Fighter p1;
	private static Fighter p2;
		
	private Image player,missed;
	private Image[] image;
	private int[][] imagePos;
	private int px,py;
	
	private boolean parrying=false;
	public static int myDamage=0,myHeal=0;
	public static ArrayList<Image> weaponList;
	public static int[] playerAction= {0,0};
	public static boolean activePointer=false;
	
	public static boolean[] T1;
	public static boolean[][] T2;
	
	private Weapon[][] weapon;	
	public static int Fcount;
	
	public Player_Left()
	{
		Fcount=0;
		
		
		p1=A_ClassSelect.getP1();
		p2=A_ClassSelect.getP2();
				
		p1.setMyTurn(true);	
		weapon=p1.getWeapon();
		imagePos=p1.getImagePos();
		image=p1.getImage();
		
		missed=new Image("/UI/Missed.png");
		
	}
	
	public void update(GameEngine ge, float dt)
	{
		GameManager.End=false;
		
		int k=0;
		
		if(parrying && p1.isMyTurn())
		{
			parrying=false;
			p1.setToBeParried(0);
		}
		
		if(Fcount==0 && !parrying)
		{
			player=image[0];
			px=imagePos[0][0];py=imagePos[0][1];
			p1.setMissed(false);
			p1.setDrawSpell(false);
		}

		if(A_ClassSelect.getMode()=="PvP")
		{
			T1= C_Arena_PvP.P1T1;
			T2=C_Arena_PvP.P1T2;
			weaponList=C_Arena_PvP.weaponList;
			activePointer=C_Arena_PvP.activePointer;
		}
		if(A_ClassSelect.getMode()=="PvA")
		{
			T1=C_Arena_PvA.P1T1;
			T2=C_Arena_PvA.P1T2;
			weaponList=C_Arena_PvA.weaponList;
			activePointer=C_Arena_PvA.activePointer;
		}
		
		for(int i=0;i<T2.length;i++)
		{
			for(int j=0;j<T2[0].length;j++)
			{
				if(weapon[i][j]!=null)
				{
					k+=1;
					if(T1[i] && T2[i][j])
					{
						if(i==0) {playerAction=p1.Attack(weapon[i][j]);}
						if(i==1) {playerAction=p1.Parry(weapon[i][j]);}
						if(i==2) {playerAction=p1.Heal(weapon[i][j]);}
						
						if(GameManager.MB )
						{
							weaponList.clear();
							Fcount=60;
							T1[i]=T2[i][j]=false;
							activePointer=false;
							
							if(i==0) 
							{
								myDamage=Scripts.ifHit(playerAction[0],playerAction[1]);
								
								
								if(myDamage==0) 						{p1.setMissed(true);}
								else if(weapon[i][j].getTag()=="Staff")		{p1.setDrawSpell(true);}
								if(myDamage>p2.getToBeParried()) 	{p2.setVitalityRelative(p2.getToBeParried()-myDamage);}
								
							}
							if(i==1) {parry();}
							if(i==2) 
							{
								myHeal=Scripts.ifHit(playerAction[0],playerAction[1]);
								
								p1.setVitality(Scripts.min(p1.getVitality()+myHeal,p1.getInitVitality()));
								if(myHeal==0) p1.setMissed(true);
							}
							
							if(Fcount>0)
							{
								player=image[k];
								px=imagePos[k][0];py=imagePos[k][1];
							}							
							p1.setMyTurn(false);
							p2.setMyTurn(true);
						}
					}
				}
			}
		}
		
		if(A_ClassSelect.getMode()=="PvP")
		{
			C_Arena_PvP.P1T1=T1;
			C_Arena_PvP.P1T2=T2;
			C_Arena_PvP.weaponList=weaponList;
			C_Arena_PvP.activePointer=activePointer;
		}
		if(A_ClassSelect.getMode()=="PvA")
		{
			C_Arena_PvA.P1T1=T1;
			C_Arena_PvA.P1T2=T2;
			C_Arena_PvA.weaponList=weaponList;
			C_Arena_PvA.activePointer=activePointer;
		}
		if(Fcount>0) {Fcount-=1;}		
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
	
	public void parry()
	{
		parrying=true;
		p1.setToBeParried(Scripts.ifHit(playerAction[0],playerAction[1]));
		if(p1.getToBeParried()==0) {p1.setMissed(true);parrying=false;}
	}
}