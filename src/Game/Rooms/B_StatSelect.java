package Game.Rooms;

import Classes.Classe;
import Game.GameManager;
import Game.GameObject;
import Game.Scripts;
import GameEngine.GameEngine;
import GameEngine.Renderer;
import GameEngine.GFX.Image;

public class B_StatSelect extends GameObject
{		
	private Image S1,S2,S3,S4,S5,splashArt,confirm;
	private Classe player;
	int i=0;
	int exp=1;
	public static int[] stat=new int[4];
	int[][] statCond=new int[4][2];
	boolean test=true;
	
	public static boolean canNext=false;
	
	public B_StatSelect()
	{
		player=A_ClassSelect.getPlayer();
		
		S1=new Image("/UI/Stats/Force.png");
		S2=new Image("/UI/Stats/Dexterity.png");
		S3=new Image("/UI/Stats/Intelligence.png");
		S4=new Image("/UI/Stats/Concentration.png");
		confirm=new Image("/SplashArt/Confirm.png");
		
		if (player.getTag()=="Warrior")
		{
			splashArt=new Image("/SplashArt/Warrior.png");
			stat[0]=40; stat[1]=10;stat[2]=0;stat[3]=0;
		}
		if (player.getTag()=="Athlete")
		{
			splashArt=new Image("/SplashArt/Athlete.png");
			stat[0]=21; stat[1]=21;stat[2]=21;stat[3]=21;
		}
		if (player.getTag()=="Mage")
		{
			splashArt=new Image("/SplashArt/Mage.png");
			stat[0]=40; stat[1]=16;stat[2]=0;stat[3]=0;
			S5=S1;
			S1=S4;
			S4=S5;
			
			S5=S2;
			S2=S3;
			S3=S5;
		}
	}

	public void update(GameEngine ge, float dt)
	{
	}
	
	public void render(GameEngine ge, Renderer r)
	{
		r.drawImage(splashArt,1000, 0);
		
		if(i==0)
		{
			stat[0]=r.setStat(stat[0], 1500, 100,statCond()[0][0],statCond()[0][1]);
			r.drawImage(S1, 1250, 100);
			if(GameManager.EB) {i=1;}
		}
		else if(i==1)
		{
			r.drawNumber(stat[0], 1600, 105);r.drawImage(S1, 1250, 100);r.drawImage(S2, 1250, 150);
			stat[1]=r.setStat(stat[1], 1500, 150,statCond()[1][0],statCond()[1][1]);
			if(GameManager.EB) {i=2;}
		}
		else if(i==2)
		{
			r.drawNumber(stat[0], 1600, 105);r.drawImage(S1, 1250, 100);
			r.drawNumber(stat[1], 1600, 155);r.drawImage(S2, 1250, 150);r.drawImage(S3, 1250, 200);
			stat[2]=r.setStat(stat[2], 1500, 200,statCond()[2][0],statCond()[2][1]);
			if(GameManager.EB) {i=3;}
		}
		else if(i==3)
		{
			r.drawNumber(stat[0], 1600, 105);r.drawImage(S1, 1250, 100);
			r.drawNumber(stat[1], 1600, 155);r.drawImage(S2, 1250, 150);
			r.drawNumber(stat[2], 1600, 205);r.drawImage(S3, 1250, 200);r.drawImage(S4, 1250, 250);
			stat[3]=r.setStat(stat[3], 1500, 250,statCond()[3][0],statCond()[3][1]);
			if(GameManager.EB) {i=4;}
		}
		else if(i==4)
		{
			r.drawNumber(stat[0], 1600, 105);r.drawImage(S1, 1250, 100);
			r.drawNumber(stat[1], 1600, 155);r.drawImage(S2, 1250, 150);
			r.drawNumber(stat[2], 1600, 205);r.drawImage(S3, 1250, 200);
			r.drawNumber(stat[3], 1600, 255);r.drawImage(S4, 1250, 250);
			player.setStat(stat[0],stat[1],stat[2],stat[3]);
			player.setVitality(200+3-stat[0]-stat[1]-stat[2]-stat[3]);
			canNext=true;
			r.drawImage(confirm, 1400, 325);
			if(GameManager.Esc) {GameManager.End=true;};
			if(GameManager.EB) {i=5;}
		}
	}
	
	public int[][] statCond()
	{
		if (player.getTag()=="Warrior")
		{			
			int force=stat[0];
			int dexterity=stat[1];
			int intelligence=stat[2];
			
			//force-------------------------------------------------------
			statCond[0][0]=20;
			statCond[0][1]=100+exp-10;
			//dexterity---------------------------------------------------
			statCond[1][0]=10;
			statCond[1][1]=100+exp-force;
			//intelligence------------------------------------------------
			statCond[2][0]=0;
			statCond[2][1]=100+exp-force-dexterity;
			//concentration-----------------------------------------------
			statCond[3][0]=0;
			statCond[3][1]=100+exp-force-dexterity-intelligence;
		}
		if (player.getTag()=="Athlete")
		{			

			int force=stat[0];
			int dexterity=stat[1];
			int intelligence=stat[2];
			
			//force--------------------------------------------------------
			statCond[0][0]=21;
			statCond[0][1]=100+exp-63;
			//dexterity		-----------------------------------------------
			statCond[1][0]=21;
			statCond[1][1]=100+exp-force-42;
			//intelligence-------------------------------------------------
			statCond[2][0]=21;
			statCond[2][1]=100+exp-force-dexterity-21;
			//concentration------------------------------------------------
			statCond[3][0]=21;
			statCond[3][1]=100+exp-force-dexterity-intelligence;
		}
		if (player.getTag()=="Mage")
		{
			
			int concentration=stat[0];
			int intelligence=stat[1];
			int dexterity=stat[2];
			
			//concentration-----------------------------------------------
			statCond[0][0]=16;
			statCond[0][1]=100+exp-16;
			//intelligence------------------------------------------------
			statCond[1][0]=16;
			statCond[1][1]=100+exp-concentration;
			//dexterity---------------------------------------------------
			statCond[2][0]=0;
			statCond[2][1]=Scripts.min(Scripts.max(concentration,intelligence)-16, 100+exp-concentration-intelligence);
			//force-------------------------------------------------------
			statCond[3][0]=0;
			statCond[3][1]=Scripts.min(Scripts.max(concentration,intelligence)-16, 100+exp-concentration-intelligence-dexterity);
		}
		return statCond;
	}
}