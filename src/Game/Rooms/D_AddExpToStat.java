package Game.Rooms;

import java.util.ArrayList;

import Classes.Fighter;
import Game.GameManager;
import Game.GameObject;
import Game.Scripts;
import GameEngine.GameEngine;
import GameEngine.Image;
import GameEngine.Renderer;


public class D_AddExpToStat extends GameObject
{
	public static boolean canNext=false;
	private Image S1,S2,S3,S4,confirm;
	private Image freePointer;

	private Fighter winner,losser;
	
	private int phase=1;
	
	public D_AddExpToStat(Fighter winner,Fighter losser)
	{
		this.winner=winner;
		this.losser=losser;
		
		S1=new Image("/UI/Stats/Force.png");
		S2=new Image("/UI/Stats/Dexterity.png");
		S3=new Image("/UI/Stats/Intelligence.png");
		S4=new Image("/UI/Stats/Concentration.png");
		confirm=new Image("/SplashArt/Confirm.png");
		
		freePointer=new Image("/UI/Pointer1.png");

		if(winner.getExp()<20)
		{
			winner.setExp(winner.getExp()+1);
			ChangeExpInDB(winner);
		}
		if(losser.getExp()>1)
		{
			losser.setExp(losser.getExp()-1);
			ChangeExpInDB(losser);
		}
	}
	public void update(GameEngine ge, float dt)
	{
		if(GameManager.EB || Scripts.isClicked(confirm, 3400, 325))
		{
			if(phase==1)
			{
				phase=2;
			}
			if(phase==3)
			{
				canNext=true;
			}
		}
	}

	public void render(GameEngine ge, Renderer r)
	{
		if(phase>=2)
		{
			losser=statUI(losser,-1,r);
		}
		
		if(phase==1)
		{
			winner=statUI(winner,1,r);
		}
		r.drawImage(confirm, 3400, 325);
	}

	private Fighter statUI(Fighter player,int toAdd,Renderer r)
	{
		int[] stat=player.getStat();;
		r.drawImage(player.getSplashArt(),3000, 0);
		r.drawImage(freePointer, 3210, 100);r.drawImage(S1, 3250, 100);r.drawNumber(stat[0], 3600, 105);
		r.drawImage(freePointer, 3210, 150);r.drawImage(S2, 3250, 150);r.drawNumber(stat[1], 3600, 155);
		r.drawImage(freePointer, 3210, 200);r.drawImage(S3, 3250, 200);r.drawNumber(stat[2], 3600, 205);
		r.drawImage(freePointer, 3210, 250);r.drawImage(S4, 3250, 250);r.drawNumber(stat[3], 3600, 255);
		
		r.drawString("experience", 3000, 0);r.drawNumber(player.getExp(), 3250,0);
		int PointsToSpend=100+player.getExp()-stat[0]-stat[1]-stat[2]-stat[3];
		if(phase==1)
		{
			if(PointsToSpend>0)
			{
				r.drawString("Unused points ", 3000, 450);r.drawNumber(PointsToSpend, 3380,450);
				IncreaseStat(player,stat);
			}
		}
		if(phase==2)
		{
			
			if(PointsToSpend==0) {phase=3;}
			
			if(PointsToSpend<0)
			{
				r.drawString("Points to reduce ", 3000, 450);r.drawNumber(-PointsToSpend, 3390,450);

				if(Scripts.isClicked(freePointer, 3210, 100)  &&  testStatMin(player,stat[0],0))
				{
					player.setStat(stat[0]-1, stat[1], stat[2], stat[3]);
					changeStatInDB(player);
				}
				if(Scripts.isClicked(freePointer, 3210, 150)&& testStatMin(player,stat[1],1))
				{
					player.setStat(stat[0], stat[1]-1, stat[2], stat[3]);
					changeStatInDB(player);
				}
				if(Scripts.isClicked(freePointer, 3210, 200)&& testStatMin(player,stat[2],2))
				{
					player.setStat(stat[0], stat[1], stat[2]-1, stat[3]);
					changeStatInDB(player);
				}
				if(Scripts.isClicked(freePointer, 3210, 250)&& testStatMin(player,stat[3],3))
				{
					player.setStat(stat[0], stat[1], stat[2], stat[3]-1);
					changeStatInDB(player);
				}
			}
			if(PointsToSpend>0)
			{
				r.drawString("Unused Points ", 3000, 450);r.drawNumber(PointsToSpend, 3380,450);
				IncreaseStat(player,stat);
			}
		}		
		
		player.setInitVitality(200+3*player.getExp()-stat[0]-stat[1]-stat[2]-stat[3]);
		return player;
	}
	
	private void IncreaseStat(Fighter player,int[] stat)
	{
	
		if(Scripts.isClicked(freePointer, 3210, 100)&& testStatMax(player,stat[0],0))
		{
			player.setStat(stat[0]+1, stat[1], stat[2], stat[3]);
			changeStatInDB(player);
		}
		if(Scripts.isClicked(freePointer, 3210, 150)&& testStatMax(player,stat[1],1))
		{
			player.setStat(stat[0], stat[1]+1, stat[2], stat[3]);
			changeStatInDB(player);
		}
		if(Scripts.isClicked(freePointer, 3210, 200)&& testStatMax(player,stat[2],2))
		{
			player.setStat(stat[0], stat[1], stat[2]+1, stat[3]);
			changeStatInDB(player);
		}
		if(Scripts.isClicked(freePointer, 3210, 250)&& testStatMax(player,stat[3],3))
		{
			player.setStat(stat[0], stat[1], stat[2], stat[3]+1);
			changeStatInDB(player);
		}
	}
	private boolean testStatMax(Fighter player,int k,int i)
	{
		int exp=player.getExp();
		int[] stat=player.getStat();
		int[] statCond=new int[4];
		int force=stat[0];
		int dexterity=stat[1];
		int intelligence=stat[2];
		if (player.getTag()=="Warrior")
		{			
			//force-------------------------------------------------------
			statCond[0]=100+exp;
			//dexterity---------------------------------------------------
			statCond[1]=Scripts.min(force-10,100+exp-force);
			//intelligence------------------------------------------------
			statCond[2]=Scripts.min(dexterity,100+exp-force-dexterity);
			//concentration-----------------------------------------------
			statCond[3]=Scripts.min(intelligence+10,100+exp-force-dexterity-intelligence);
		}
		if (player.getTag()=="Athlete")
		{			
			//force--------------------------------------------------------
			statCond[0]=100+exp-63;
			//dexterity----------------------------------------------------
			statCond[1]=100+exp-force-42;
			//intelligence-------------------------------------------------
			statCond[2]=100+exp-force-dexterity-21;
			//concentration------------------------------------------------
			statCond[3]=100+exp-force-dexterity-intelligence;
		}
		if (player.getTag()=="Mage")
		{
			//force--------------------------------------------------------
			statCond[0]=(int)((70+exp)/4.0-0.01f);
			//dexterity----------------------------------------------------
			statCond[1]=Scripts.min(((int)((70+exp)/4.0-0.01f)),(int)((70+exp)/2-force-0.01f));
			//intelligence-------------------------------------------------
			statCond[2]=100+exp-force-dexterity-(16+Scripts.max(force,dexterity));
			//concentration------------------------------------------------
			statCond[3]=100+exp-force-dexterity-intelligence;
		}
		return k<statCond[i];
	}
	
	private boolean testStatMin(Fighter player,int k,int i)
	{
		int[] stat=player.getStat();
		int[] statCond=new int[4];
		int force=stat[0];
		int dexterity=stat[1];
		if (player.getTag()=="Warrior")
		{			
			//force-------------------------------------------------------
			statCond[0]=10;
			//dexterity---------------------------------------------------
			statCond[1]=0;
			//intelligence------------------------------------------------
			statCond[2]=0;
			//concentration-----------------------------------------------
			statCond[3]=0;
		}
		if (player.getTag()=="Athlete")
		{			
			//force--------------------------------------------------------
			statCond[0]=21;
			//dexterity----------------------------------------------------
			statCond[1]=21;
			//intelligence-------------------------------------------------
			statCond[2]=21;
			//concentration------------------------------------------------
			statCond[3]=21;
		}
		if (player.getTag()=="Mage")
		{
			//force--------------------------------------------------------
			statCond[0]=0;
			//dexterity----------------------------------------------------
			statCond[1]=0;
			//intelligence-------------------------------------------------
			statCond[2]=Scripts.max(force,dexterity)+16;
			//concentration------------------------------------------------
			statCond[3]=Scripts.max(force,dexterity)+16;
		}
		return statCond[i]<k;
	}
	
	private void changeStatInDB(Fighter player)
	{
		int[] stat=player.getStat();
		ArrayList<String> lines=Scripts.FileRead("Players.txt");
		int index=0;
		for(int i=0;i<lines.size();i++)
		{
			if(player.getName().equals(Scripts.getName(i)))
			{
				index=i;
			}
		}
		Scripts.FileClear("PLayers.txt");
		for(int i=0;i<lines.size();i++)
		{
			if(i!=index) Scripts.FileWrite("PLayers.txt", lines.get(i), true);
			else Scripts.FileWrite("PLayers.txt",player.getName()+":"+player.getTag().charAt(0)+":"+stat[0]+":"+stat[1]+":"+stat[2]+":"+stat[3]+":"+player.getExp()+";", true);
		}
	}
	
	private void ChangeExpInDB(Fighter player)
	{
		int[] stat=player.getStat();
		ArrayList<String> lines=Scripts.FileRead("Players.txt");
		int index=0;
		for(int i=0;i<lines.size();i++)
		{
			if(player.getName().equals(Scripts.getName(i)))
			{
				index=i;
			}
		}
		Scripts.FileClear("PLayers.txt");
		for(int i=0;i<lines.size();i++)
		{
			if(i!=index) Scripts.FileWrite("PLayers.txt", lines.get(i), true);
			else Scripts.FileWrite("PLayers.txt",player.getName()+":"+player.getTag().charAt(0)+":"+stat[0]+":"+stat[1]+":"+stat[2]+":"+stat[3]+":"+player.getExp()+";", true);
		}
	}
}
