package Game.Rooms;

import Classes.Classe;
import Classes.Warrior;
import Classes.Athlete;
import Classes.Mage;
import Game.GameManager;
import Game.GameObject;
import Game.Scripts;
import GameEngine.GameEngine;
import GameEngine.Renderer;
import GameEngine.GFX.Image;

public class A_ClassSelect extends GameObject
{	
	private static Classe p1,p2;
	private Image player,warrior, athlete, mage;
	private Image PvP,PvA,AvA;
	private Image image,wallpaper,background,Pointer1Img;
	public static Classe classe;
	
	private static String Mode="";
	
	private int wx=100,wy=50;
	private int ax=400,ay=50;
	private int mx=700,my=50;
	private int Px=0, Py=275;
	private int phase=0;
	
	private boolean Pointer1=false,Pointer2=false;
	public static boolean canNext=false;

	public A_ClassSelect()
	{
		GameManager.End=false;

		p1=null;
		Mode="";
		
		wallpaper=new Image("/Wallpaper.png");
		background=new Image("/Background.png");

		Pointer1Img=new Image("/UI/Select.png");
			
		player=new Image("/UI/Player.png");
		warrior=new Image("/Sprites/Warrior/Warrior.png");
		athlete=new Image("/Sprites/Athlete/Athlete.png");
		mage=new Image("/Sprites/Mage/Mage.png");
		
		PvP=new Image("/UI/PvP.png");
		PvA=new Image("/UI/PvA.png");
		AvA=new Image("/UI/AvA.png");
	}
	
	public void update(GameEngine ge, float dt)
	{	
		if (phase==2)
		{
			p2=setClasse(p2);
			
			if (GameManager.EB && Pointer2==true)
			{
				canNext=true;
				phase=-1;
			}
		}
		
		if (phase==1)
		{
			p1=setClasse(p1);
			if (GameManager.EB && Pointer2==true) 
				{
					Pointer2=false;
					phase=2;
				}
		}
		if(phase==0)
		{
			Mode=setMode();
			if (GameManager.EB && Pointer1==true) 
			{
				Pointer1=false;
				phase=1;
			}
		}
	}

	
	
	public void render(GameEngine ge, Renderer r)
	{
		
		
		r.drawImage(background, 2000, 0);
		r.drawImage(wallpaper,0,0);
		if(Pointer1)
		{
			r.drawImage(Pointer1Img, Px, 100);
		}
		if(Pointer2)
		{
			r.drawImage(image, Px, Py);
		}
		if(phase==0)
		{
			r.drawImage(PvP,wx,100);
			r.drawImage(PvA,ax,100);
			r.drawImage(AvA,mx,100);
		}
		else
		{
			r.drawImage(player, 0, 0);
			r.drawNumber(phase, 100, 0);
			r.drawImage(warrior,wx,wy);
			r.drawImage(athlete,ax,ay);
			r.drawImage(mage,mx,my);
		}
	}

	private Classe setClasse(Classe player)
	{
		if(Scripts.isClicked(warrior,wx,wy))
		{
			player=null;
			player=new Warrior();
			player.setTag("Warrior");
			
			image= new Image("/UI/Warrior.png");
			Pointer2=true;
			Px=wx;
		}
		
		if(Scripts.isClicked(athlete,ax,ay))
		{
			player=null;
			player=new Athlete();
			player.setTag("Athlete");
			
			image= new Image("/UI/Athlete.png");
			Pointer2=true;
			Px=ax;	
		}

		if(Scripts.isClicked(mage,mx,my))
		{
			player=null;
			player=new Mage();
			player.setTag("Mage");
			
			image= new Image("/UI/Mage.png");
			Pointer2=true;
			Px=mx;
		}
		return player;
	}
	
	private String setMode()
	{
		if(Scripts.isClicked(PvP, wx, 100))
		{
			Mode="PvP";
			Pointer1=true;
			Px=wx;	
		}
		if(Scripts.isClicked(PvA, ax, 100))
		{
			Mode="PvA";
			Pointer1=true;
			Px=ax;	
		}
		if(Scripts.isClicked(AvA, mx, 100))
		{
			Mode="AvA";
			Pointer1=true;
			Px=mx;
		}
		return Mode;
	}
	
	public static Classe getP1()
	{
		return p1;
	}

	public static Classe getP2()
	{
		return p2;
	}
	
	public static String getMode()
	{
		return Mode;
	}
}