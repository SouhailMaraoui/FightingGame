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
	private Image warrior, athlete, mage;
	private Image image,wallpaper,background;
	public static Classe classe;
	
	private int wx=100,wy=20;
	private int ax=400,ay=20;
	private int mx=700,my=20;
	private int Px=0, Py=245;
	private int phase=0;
	
	private boolean activePointer=false;
	public static boolean canNext=false;

	public A_ClassSelect()
	{
		GameManager.End=false;

		p1=null;
		
		wallpaper=new Image("/Wallpaper.png");
		background=new Image("/Background.png");


		warrior=new Image("/Sprites/Warrior/Warrior.png");
		athlete=new Image("/Sprites/Athlete/Athlete.png");
		mage=new Image("/Sprites/Mage/Mage.png");
		
	}
	
	public void update(GameEngine ge, float dt)
	{	
		if (phase==1)
		{
			p2=setClasse(p2);
			
			if (GameManager.EB && activePointer==true)
			{
				canNext=true;
				phase=-1;
			}
		}
		
		if (phase==0)
		{
			p1=setClasse(p1);
			if (GameManager.EB && activePointer==true) 
				{
					activePointer=false;
					phase=1;
				}
		}
	}

	
	
	public void render(GameEngine ge, Renderer r)
	{
		
		
		r.drawImage(background, 2000, 0);
		r.drawImage(wallpaper,0,0);
		if(activePointer)
		{
			r.drawImage(image, Px, Py);
		}
		r.drawImage(warrior,wx,wy);
		r.drawImage(athlete,ax,ay);
		r.drawImage(mage,mx,my);	
		r.drawNumber(phase, 0, 0);
	}

	private Classe setClasse(Classe player)
	{
		if(Scripts.isClicked(warrior,wx,wy))
		{
			player=null;
			player=new Warrior();
			player.setTag("Warrior");
			
			image= new Image("/UI/Warrior.png");
			activePointer=true;
			Px=wx;
		}
		
		if(Scripts.isClicked(athlete,ax,ay))
		{
			player=null;
			player=new Athlete();
			player.setTag("Athlete");
			
			image= new Image("/UI/Athlete.png");
			activePointer=true;
			Px=ax;	
		}

		if(Scripts.isClicked(mage,mx,my))
		{
			player=null;
			player=new Mage();
			player.setTag("Mage");
			
			image= new Image("/UI/Mage.png");
			activePointer=true;
			Px=mx;
		}
		return player;
	}
	
	public static Classe getP1()
	{
		return p1;
	}

	public static Classe getP2()
	{
		return p2;
	}
}