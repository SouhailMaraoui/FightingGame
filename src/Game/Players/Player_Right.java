package Game.Players;

import Classes.Classe;
import Classes.Weapon;
import Game.GameManager;
import Game.GameObject;
import Game.Scripts;
import Game.Rooms.A_ClassSelect;
import Game.Rooms.C_Arena_PvP;
import GameEngine.GameEngine;
import GameEngine.Renderer;
import GameEngine.GFX.Image;

public class Player_Right extends GameObject
{
	private static Classe p1;
	private static Classe p2;
	
	private Image player,missed;
	private Image[] image;
	private int[][] imagePos;
	private int px,py;
	
	private boolean parrying=false;
	public static int myDamage=0,myHeal=0;
	
	public static int[] playerAction= {0,0};
	
	public static boolean[][] T;

	private Weapon[][] weapon;
	
	public static int Fcount;
	
	public Player_Right()
	{
		Fcount=0;
				
		p1=A_ClassSelect.getP1();
		p2=A_ClassSelect.getP2();
		
		p1.setMyTurn(true);	
		weapon=p2.getWeapon();
		imagePos=p2.getImagePos();
		image=p2.getImage();
		
		missed=new Image("/UI/Missed.png");
	}
	
	public void update(GameEngine ge, float dt)
	{
		GameManager.End=false;
		
		int k=0;
		
		if(parrying && p2.isMyTurn())
		{
			parrying=false;
			p1.setToBeParried(0);
		}
		
		if(Fcount==0 && !parrying)
		{
			player=image[0];
			px=imagePos[0][0];py=imagePos[0][1];
			p2.setMissed(false);
			p2.setDrawSpell(false);		
		}
		
		T= C_Arena_PvP.P2T2;
		
		for(int i=0;i<T.length;i++)
		{
			for(int j=0;j<T[0].length;j++)
			{
				if(weapon[i][j]!=null)
				{
					k+=1;
					if(C_Arena_PvP.P2T1[i] && T[i][j])
					{
						if(i==0) {playerAction=p2.Attack(weapon[i][j]);}
						if(i==1) {playerAction=p2.Parry(weapon[i][j]);}
						if(i==2) {playerAction=p2.Heal(weapon[i][j]);}
						
						if(GameManager.MB )
						{

							p1.setMyTurn(true);
							p2.setMyTurn(false);
							C_Arena_PvP.weaponList.clear();
							Fcount=60;
							C_Arena_PvP.P2T1[i]=C_Arena_PvP.P2T2[i][j]=false;
							C_Arena_PvP.activePointer=false;
							
							if(i==0) 
							{
								myDamage=Scripts.ifHit(playerAction[0],playerAction[1]);
								
								if(myDamage==0) 						{p2.setMissed(true);}
								else if(weapon[i][j].getTag()=="Staff") {p2.setDrawSpell(true);}
								if(myDamage>p1.getToBeParried()) 	{p1.setVitalityRelative(p1.getToBeParried()-myDamage);}
							}
							if(i==1) {parry();}
							if(i==2) 
							{
								myHeal=Scripts.ifHit(playerAction[0],playerAction[1]);
								
								p2.setVitality(Scripts.min(p2.getVitality()+myHeal,p2.getInitVitality()));
								if(myHeal==0) p2.setMissed(true);
							}
							
							if(Fcount>0)
							{
								player=image[k];
								px=imagePos[k][0];py=imagePos[k][1];
								if(p2.getTag()=="Warrior" && i==0) px+=200;
							}							
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
		p2.setToBeParried(Scripts.ifHit(playerAction[0],playerAction[1]));
		if(p2.getToBeParried()==0) {p2.setMissed(true);parrying=false;}
	}
	
	public void render(GameEngine ge, Renderer r)
	{
		r.drawImageReversed(player, 4800-px, py);
		if(p2.getToBeParried()>0)
		{
			r.drawNumber(p1.getToBeParried(), 2820, 70);
		}
		if(p2.isMissed())
		{
			r.drawImage(missed, 2350,100);
		}
	}
}