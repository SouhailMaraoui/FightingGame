package Game.Rooms;

import Game.GameObject;
import Game.Scripts;
import GameEngine.GameEngine;
import GameEngine.Renderer;
import GameEngine.GFX.Image;
import classes.Classe;

public class A_ClassSelect extends GameObject
{
	public static String classTag="Warrior";
	
	private Image warrior, athlete, mage;
	private Image player;
	public static Classe classe;
	
	private int wx=100,wy=20;
	private int ax=400,ay=20;
	private int mx=700,my=20;
	private int Px=0, Py=245;

	
	private boolean activePointer=false;
	public static boolean canNext=false;

	
	public A_ClassSelect()
	{
		warrior=new Image("/Sprites/Warrior/Warrior.png");
		athlete=new Image("/Sprites/Athlete/Athlete.png");
		mage=new Image("/Sprites/Mage/Mage.png");
	}
	
	public void update(GameEngine ge, float dt)
	{
		if(Scripts.isClicked(warrior,wx,wy))
		{
			classe=new Classe(25,25,25,25);
			player= new Image("/UI/Warrior.png");
			classTag="Warrior";
			activePointer=true;
			canNext=true;
			Px=wx;
		}
		
		if(Scripts.isClicked(athlete,ax,ay))
		{
			classe=new Classe(25,25,25,25);
			player= new Image("/UI/Athlete.png");
			classTag="Athlete";
			activePointer=true;
			canNext=true;
			Px=ax;	
		}

		if(Scripts.isClicked(mage,mx,my))
		{
			classe=new Classe(25,25,25,25);
			player= new Image("/UI/Mage.png");
			classTag="Mage";
			activePointer=true;
			canNext=true;
			Px=mx;
		}
	}

	public void render(GameEngine ge, Renderer r)
	{
		if(activePointer)
		{
			r.drawImage(player, Px, Py);
		}
		r.drawImage(warrior,wx,wy);
		r.drawImage(athlete,ax,ay);
		r.drawImage(mage,mx,my);
		
	}

	public String getClassTag()
	{
		return classTag;
	}
}