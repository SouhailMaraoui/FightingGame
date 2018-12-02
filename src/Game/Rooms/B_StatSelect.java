package Game.Rooms;

import Classes.Fighter;
import Game.GameManager;
import Game.GameObject;
import Game.Scripts;
import GameEngine.GameEngine;
import GameEngine.Image;
import GameEngine.Renderer;

public class B_StatSelect extends GameObject
{		
	private Image S1,S2,S3,S4;
	private int phase=1;
	public static Image confirm,playerText;
	private Fighter p1,p2;
	int i=0;
	int exp;
	public static int[] stat=new int[4];
	int[][] statCond=new int[4][2];
	boolean test=true;
	
	public static boolean canNext=false;
	
	public B_StatSelect()
	{
		p1=A_ClassSelect.getP1();
		p2=A_ClassSelect.getP2();
		playerText=new Image("/UI/Player.png");
		S1=new Image("/UI/Stats/Force.png");
		S2=new Image("/UI/Stats/Dexterity.png");
		S3=new Image("/UI/Stats/Intelligence.png");
		S4=new Image("/UI/Stats/Concentration.png");
		confirm=new Image("/SplashArt/Confirm.png");
		if(p1.getStat()==null){stat[0]=statCond(p1)[0][0];}
		else {stat=p1.getStat();i=4;}
	}

	public void update(GameEngine ge, float dt)
	{
	}
	
	public void render(GameEngine ge, Renderer r)
	{
		if(phase==2)
			p2=statUI(p2,r);
		if(phase==1)
			p1=statUI(p1,r);
	}
	
	private Fighter statUI(Fighter player,Renderer r)
	{
		exp=player.getExp();
		r.drawImage(player.getSplashArt(),1000, 0);
		
		if(i==0)
		{
			stat[0]=r.setStat(stat[0], 1500, 100,statCond(player)[0][0],statCond(player)[0][1]);
			r.drawImage(S1, 1250, 100);
			if(GameManager.EB) {i=1;if(player.getStat()==null)stat[1]=statCond(player)[1][0];else stat[1]=player.getStat()[1];}
			
		}
		else if(i==1)
		{
			r.drawNumber(stat[0], 1600, 105);r.drawImage(S1, 1250, 100);r.drawImage(S2, 1250, 150);
			stat[1]=r.setStat(stat[1], 1500, 150,statCond(player)[1][0],statCond(player)[1][1]);
			if(GameManager.EB) {i=2;if(player.getStat()==null)stat[2]=statCond(player)[2][0];else stat[2]=player.getStat()[2];}
			
		}
		else if(i==2)
		{
			r.drawNumber(stat[0], 1600, 105);r.drawImage(S1, 1250, 100);
			r.drawNumber(stat[1], 1600, 155);r.drawImage(S2, 1250, 150);r.drawImage(S3, 1250, 200);
			stat[2]=r.setStat(stat[2], 1500, 200,statCond(player)[2][0],statCond(player)[2][1]);
			if(GameManager.EB) {i=3;if(player.getStat()==null)stat[3]=statCond(player)[3][0];else stat[3]=player.getStat()[3];}
		}
		else if(i==3)
		{
			r.drawNumber(stat[0], 1600, 105);r.drawImage(S1, 1250, 100);
			r.drawNumber(stat[1], 1600, 155);r.drawImage(S2, 1250, 150);
			r.drawNumber(stat[2], 1600, 205);r.drawImage(S3, 1250, 200);r.drawImage(S4, 1250, 250);
			stat[3]=r.setStat(stat[3], 1500, 250,statCond(player)[3][0],statCond(player)[3][1]);
			if(GameManager.EB) {i=4;}
		}
		else if(i==4)
		{
			r.drawNumber(stat[0], 1600, 105);r.drawImage(S1, 1250, 100);
			r.drawNumber(stat[1], 1600, 155);r.drawImage(S2, 1250, 150);
			r.drawNumber(stat[2], 1600, 205);r.drawImage(S3, 1250, 200);
			r.drawNumber(stat[3], 1600, 255);r.drawImage(S4, 1250, 250);
			
			player.setInitVitality(200+3*player.getExp()-stat[0]-stat[1]-stat[2]-stat[3]);
			r.drawImage(confirm, 1400, 325);
			if(GameManager.Esc && player.isNew()) {i=0;}
			if(GameManager.EB || Scripts.isClicked(confirm, 1400, 325)) 
			{
				player.setStat(stat[0], stat[1], stat[2], stat[3]);
				if(player.isNew()) {Scripts.FileWrite("Players.txt",player.getName()+":"+player.getTag().charAt(0)+":"+stat[0]+":"+stat[1]+":"+stat[2]+":"+stat[3]+":"+player.getExp()+";",true);player.setNew(false);player.setStatCond(statCond);}
				if(phase==2)
				{
					phase=-1;
					canNext=true;
				}
				if(phase==1)
				{
					stat=new int[4];
					if(p2.getStat()==null){stat[0]=statCond(p2)[0][0];i=0;}
					else {stat=p2.getStat();i=4;}

					phase=2;
				}
			}
		}
		r.drawImage(playerText, 1000, 0);
		r.drawNumber(phase, 1100, 0);
		return player;	
	}
	
	private int[][] statCond(Fighter player)
	{
		if (player.getTag()=="Warrior")
		{			
			int force=stat[0];
			int dexterity=stat[1];
			int intelligence=stat[2];
			
			//force-------------------------------------------------------
			statCond[0][0]=10;
			statCond[0][1]=100+exp;
			//dexterity---------------------------------------------------
			statCond[1][0]=0;
			statCond[1][1]=Scripts.min(force-10,100+exp-force);
			//intelligence------------------------------------------------
			statCond[2][0]=0;
			statCond[2][1]=Scripts.min(dexterity,100+exp-force-dexterity);
			//concentration-----------------------------------------------
			statCond[3][0]=0;
			statCond[3][1]=Scripts.min(intelligence+10,100+exp-force-dexterity-intelligence);
		}
		if (player.getTag()=="Athlete")
		{			
			int force=stat[0];
			int dexterity=stat[1];
			int intelligence=stat[2];
			
			//force--------------------------------------------------------
			statCond[0][0]=21;
			statCond[0][1]=100+exp-63;
			//dexterity----------------------------------------------------
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
			
			int force=stat[0];
			int dexterity=stat[1];
			int intelligence=stat[2];
			
			//force--------------------------------------------------------
			statCond[0][0]=0;
			statCond[0][1]=(int)((70+exp)/4.0-0.01f);
			//dexterity----------------------------------------------------
			statCond[1][0]=0;
			statCond[1][1]=Scripts.min(((int)((70+exp)/4.0-0.01f)),(int)((70+exp)/2-force-0.01f));
			//intelligence-------------------------------------------------
			statCond[2][0]=Scripts.max(force,dexterity)+16;
			statCond[2][1]=100+exp-force-dexterity-(16+Scripts.max(force,dexterity));
			//concentration------------------------------------------------
			statCond[3][0]=Scripts.max(force,dexterity)+16;
			statCond[3][1]=100+exp-force-dexterity-intelligence;
		}
		return statCond;
	}
}