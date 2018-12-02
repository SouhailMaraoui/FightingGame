package Game.Rooms;

import java.util.ArrayList;

import Classes.Classe;
import Game.GameManager;
import Game.GameObject;
import Game.Scripts;
import GameEngine.GameEngine;
import GameEngine.Renderer;
import GameEngine.GFX.Image;


public class D_AddExpToStat extends GameObject
{
	public static boolean canNext=false;
	private Image S1,S2,S3,S4,confirm;
	private Image freePointer;
	private boolean Pointer=false;
	private int Py;

	private Classe player;
	private int[] stat;
	private int[][] statCond;
	public D_AddExpToStat(Classe player)
	{
		this.player=player;
		S1=new Image("/UI/Stats/Force.png");
		S2=new Image("/UI/Stats/Dexterity.png");
		S3=new Image("/UI/Stats/Intelligence.png");
		S4=new Image("/UI/Stats/Concentration.png");
		confirm=new Image("/SplashArt/Confirm.png");
		
		freePointer=new Image("/UI/Pointer1.png");

		if(player.getExp()<20)
			{
			player.setExp(player.getExp()+1);
			AddExpToDB(player.getName());
			}
		stat=player.getStat();
	}
	public void update(GameEngine ge, float dt)
	{
	}

	public void render(GameEngine ge, Renderer r)
	{
		player=statUI(player,r);
	}

	private Classe statUI(Classe player,Renderer r)
	{
		stat=player.getStat();;
		r.drawImage(player.getSplashArt(),3000, 0);
		r.drawImage(freePointer, 3210, 100);r.drawImage(S1, 3250, 100);r.drawNumber(stat[0], 3600, 105);
		r.drawImage(freePointer, 3210, 150);r.drawImage(S2, 3250, 150);r.drawNumber(stat[1], 3600, 155);
		r.drawImage(freePointer, 3210, 200);r.drawImage(S3, 3250, 200);r.drawNumber(stat[2], 3600, 205);
		r.drawImage(freePointer, 3210, 250);r.drawImage(S4, 3250, 250);r.drawNumber(stat[3], 3600, 255);
		
		r.drawString("experience", 3000, 0);r.drawNumber(player.getExp(), 3250,0);
		int PointsToSpend=100+player.getExp()-stat[0]-stat[1]-stat[2]-stat[3];
		r.drawString("points to spend ", 3000, 450);r.drawNumber(PointsToSpend, 3380,450);
		
		if(Scripts.isClicked(freePointer, 3210, 100) && PointsToSpend>0 && testStat(stat[0],0))
		{
			//Pointer=true;
			//Py=100;
			player.setStat(stat[0]+1, stat[1], stat[2], stat[3]);
			changeStatInDB(player.getName());
		}
		if(Scripts.isClicked(freePointer, 3210, 150) && PointsToSpend>0 && testStat(stat[1],1))
		{
			//Pointer=true;
			//Py=150;
			player.setStat(stat[0], stat[1]+1, stat[2], stat[3]);
			changeStatInDB(player.getName());
		}
		if(Scripts.isClicked(freePointer, 3210, 200) && PointsToSpend>0 && testStat(stat[2],2))
		{
			//Pointer=true;
			//Py=200;
			player.setStat(stat[0], stat[1], stat[2]+1, stat[3]);
			changeStatInDB(player.getName());
		}
		if(Scripts.isClicked(freePointer, 3210, 250) && PointsToSpend>0 && testStat(stat[3],3))
		{
			//Pointer=true;
			//Py=250;
			player.setStat(stat[0], stat[1], stat[2], stat[3]+1);
			changeStatInDB(player.getName());
		}

		if(GameManager.EB)
		{
			canNext=true;
		}
		r.drawImage(confirm, 3400, 325);
		
		player.setInitVitality(200+player.getExp()-stat[0]-stat[1]-stat[2]-stat[3]);
		return player;
	}
	private boolean testStat(int k,int i)
	{
		statCond=Scripts.statCond(player);
		return statCond[i][0]<k && k<statCond[i][1];
	}
	private void changeStatInDB(String playerName)
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
	
	private void AddExpToDB(String playerName)
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
