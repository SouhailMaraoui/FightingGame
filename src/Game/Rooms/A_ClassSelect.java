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
	private static Classe player;
	private Image warrior, athlete, mage;
	private Image image,wallpaper,background;
	public static Classe classe;
	
	private int wx=100,wy=20;
	private int ax=400,ay=20;
	private int mx=700,my=20;
	private int Px=0, Py=245;
	
	private boolean activePointer=false;
	public static boolean canNext=false;

	public A_ClassSelect()
	{
		GameManager.End=false;

		player=null;
		
		wallpaper=new Image("/Wallpaper.png");
		background=new Image("/Background.png");


		warrior=new Image("/Sprites/Warrior/Warrior.png");
		athlete=new Image("/Sprites/Athlete/Athlete.png");
		mage=new Image("/Sprites/Mage/Mage.png");
		
	}
	
	public void update(GameEngine ge, float dt)
	{		
		if(Scripts.isClicked(warrior,wx,wy))
		{
			player=null;

			player=new Warrior();
			player.setTag("Warrior");
			
			image= new Image("/UI/Warrior.png");
			activePointer=true;
			canNext=true;
			Px=wx;
		}
		
		if(Scripts.isClicked(athlete,ax,ay))
		{
			player=null;

			player=new Athlete();
			player.setTag("Athlete");
			
			image= new Image("/UI/Athlete.png");
			activePointer=true;
			canNext=true;
			Px=ax;	
		}

		if(Scripts.isClicked(mage,mx,my))
		{
			player=null;

			player=new Mage();
			player.setTag("Mage");
			
			image= new Image("/UI/Mage.png");
			activePointer=true;
			canNext=true;
			Px=mx;
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
	}

	public static Classe getPlayer()
	{
		return player;
	}
}