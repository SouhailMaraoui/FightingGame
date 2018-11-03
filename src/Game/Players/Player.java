package Game.Players;

import Classes.Classe;
import Classes.Weapon;
import Classes.Warrior;

import javax.swing.plaf.synth.SynthSeparatorUI;

import Classes.Athlete;
import Classes.Mage;
import Game.GameManager;
import Game.GameObject;
import Game.Rooms.A_ClassSelect;
import Game.Rooms.B_StatSelect;
import Game.Rooms.C_Arena;
import GameEngine.GameEngine;
import GameEngine.Renderer;
import GameEngine.GFX.Image;

public class Player extends GameObject
{
	private static Classe p1=A_ClassSelect.getPlayer();
	
	private Image player;
	private Image[] image;
	private int[][] imagePos;
	private int px,py;
	
	public static int[] playerAction;
	
	public static boolean[][] T;

	private Weapon[][] weapon=Classe.getWeapon();
	
	public static int Fcount=0;
	
	public Player()
	{
		//hit=new Image("/UI/Info/Hit.png");
		//chance=new Image("/UI/Info/Chance.png");
		
		imagePos=p1.getImagePos();
		image=p1.getImage();
		
	}
	
	public void update(GameEngine ge, float dt)
	{
		int k=0;
		if(Fcount==0)
		{
			player=image[0];
			px=imagePos[0][0];py=imagePos[0][1];
		}
		
		T= C_Arena.T2;

		for(int i=0;i<T.length;i++)
		{
			for(int j=0;j<T[0].length;j++)
			{
				if(weapon[i][j]!=null)
				{
					k+=1;
					if(C_Arena.T1[i])
					{
						if(i==0) {playerAction=p1.Attack(weapon[i][j]);}
						if(i==1) {playerAction=p1.Parry(weapon[i][j]);}
						if(i==2) {playerAction=p1.Heal(weapon[i][j]);}
						
						if(GameManager.MB && T[i][j])
						{
							C_Arena.weaponList.clear();
							Fcount=60;
							C_Arena.T1[i]=C_Arena.T2[i][j]=false;
							C_Arena.activePointer=false;
							
							if(Fcount>0)
							{
								player=image[k];
								px=imagePos[k][0];py=imagePos[k][1];
							}
							
						}
					}
				}
			}
		}
		if(Fcount>0) {Fcount-=1;};
	}
	
	public void render(GameEngine ge, Renderer r)
	{
		r.drawImage(player, px, py);
	}
}