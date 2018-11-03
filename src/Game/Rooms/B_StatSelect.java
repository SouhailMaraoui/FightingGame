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
	private Image S1,S2,S3,S4,S5;
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
		
		if (player.getTag()=="Warrior")
		{
			stat[0]=40; stat[1]=40;stat[2]=0;stat[3]=0;
		}
		if (player.getTag()=="Athlete")
		{
			stat[0]=25; stat[1]=25;stat[2]=25;stat[3]=25;
		}
		if (player.getTag()=="Mage")
		{
			stat[0]=40; stat[1]=40;stat[2]=10;stat[3]=10;
			
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
		if(i==0)
		{
			stat[0]=r.setStat(stat[0], 1400, 100,statCond()[0][0],statCond()[0][1]);
			r.drawImage(S1, 1100, 100);
			if(GameManager.EB) {i=1;}
		}
		else if(i==1)
		{
			r.drawNumber(stat[0], 1500, 105);r.drawImage(S1, 1100, 100);r.drawImage(S2, 1100, 150);
			stat[1]=r.setStat(stat[1], 1400, 150,statCond()[1][0],statCond()[1][1]);
			if(GameManager.EB) {i=2;}
		}
		else if(i==2)
		{
			r.drawNumber(stat[0], 1500, 105);r.drawImage(S1, 1100, 100);
			r.drawNumber(stat[1], 1500, 155);r.drawImage(S2, 1100, 150);r.drawImage(S3, 1100, 200);
			stat[2]=r.setStat(stat[2], 1400, 200,statCond()[2][0],statCond()[2][1]);
			if(GameManager.EB) {i=3;}
		}
		else if(i==3)
		{
			r.drawNumber(stat[0], 1500, 105);r.drawImage(S1, 1100, 100);
			r.drawNumber(stat[1], 1500, 155);r.drawImage(S2, 1100, 150);
			r.drawNumber(stat[2], 1500, 205);r.drawImage(S3, 1100, 200);r.drawImage(S4, 1100, 250);
			stat[3]=r.setStat(stat[3], 1400, 250,statCond()[3][0],statCond()[3][1]);
			if(GameManager.EB) {i=4;}
		}
		else if(i==4)
		{
			r.drawNumber(stat[0], 1500, 105);r.drawImage(S1, 1100, 100);
			r.drawNumber(stat[1], 1500, 155);r.drawImage(S2, 1100, 150);
			r.drawNumber(stat[2], 1500, 205);r.drawImage(S3, 1100, 200);
			r.drawNumber(stat[3], 1500, 255);r.drawImage(S4, 1100, 250);
			player.setStat(stat[0],stat[1],stat[2],stat[3]);
			player.setVitatlity(200+3-stat[0]-stat[1]-stat[2]-stat[3]);
			canNext=true;
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
			statCond[0][0]=20;
			statCond[0][1]=100+exp-60;
			//dexterity		-----------------------------------------------
			statCond[1][0]=20;
			statCond[1][1]=100+exp-force-40;
			//intelligence-------------------------------------------------
			statCond[2][0]=20;
			statCond[2][1]=100+exp-force-dexterity-20;
			//concentration------------------------------------------------
			statCond[3][0]=20;
			statCond[3][1]=100+exp-force-dexterity-intelligence;
		}
		if (player.getTag()=="Mage")
		{
			int intelligence=stat[1];
			int concentration=stat[0];
			
			//concentration-----------------------------------------------
			statCond[0][0]=15;
			statCond[0][1]=100+exp-15;
			//intelligence------------------------------------------------
			statCond[1][0]=15;
			statCond[1][1]=100+exp-concentration;
			//dexterity---------------------------------------------------
			statCond[2][0]=0;
			statCond[2][1]=Scripts.max(concentration,intelligence)-15;
			//force-------------------------------------------------------
			statCond[3][0]=0;
			statCond[3][1]=Scripts.max(concentration,intelligence)-15;
		}
		return statCond;
	}
}