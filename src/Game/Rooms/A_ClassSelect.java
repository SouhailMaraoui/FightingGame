package Game.Rooms;

import Game.GameObject;
import Game.Scripts;
import GameEngine.GameEngine;
import GameEngine.Renderer;
import GameEngine.GFX.Image;

public class A_ClassSelect extends GameObject
{
	private Image warrior, athlete, mage;
	private Image next, player;
	
	private int wx=100,wy=20;
	private int ax=400,ay=20;
	private int mx=700,my=20;
	private int Px=0, Py=245;
	
	private boolean activePointer=false;
	
	public A_ClassSelect()
	{
		warrior=new Image("/Sprites/Warrior/Warrior.png");
		athlete=new Image("/Sprites/Athlete/Athlete.png");
		mage=new Image("/Sprites/Mage/Mage.png");
		
		next= new Image("/UI/NextRoom.png");
	}
	
	public void update(GameEngine ge, float dt)
	{
		if(Scripts.isClicked(warrior,wx,wy))
		{
			player= new Image("/UI/Warrior.png");
			System.out.println("Warrior");
			activePointer=true;
			Px=wx;
		}
		
		if(Scripts.isClicked(athlete,ax,ay))
		{
			player= new Image("/UI/Athlete.png");
			System.out.println("Athlete");
			activePointer=true;
			Px=ax;	
		}

		if(Scripts.isClicked(mage,mx,my))
		{
			player= new Image("/UI/Mage.png");
			System.out.println("Mage");
			activePointer=true;
			Px=mx;
		}
	}

	public void render(GameEngine ge, Renderer r)
	{
		if(activePointer)
		{
			r.drawImage(player, Px, Py);
		}
		
		r.drawImage(next,925,250);
		
		r.drawImage(warrior,wx,wy);
		r.drawImage(athlete,ax,ay);
		r.drawImage(mage,mx,my);
		
		r.drawNumber(000000,500,300);
	}

}
