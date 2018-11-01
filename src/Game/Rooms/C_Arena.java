package Game.Rooms;

import Game.GameManager;
import Game.GameObject;
import Game.Scripts;
import GameEngine.GameEngine;
import GameEngine.Renderer;
import GameEngine.GFX.Image;

public class C_Arena extends GameObject
{
	private Image attack, parry, heal;
	private Image panel, pointer;

	private int ax=2100,ay=375;
	private int px=2100,py=425;
	private int hx=2100,hy=475;

	public static boolean Aing,Hing,Ping;
	private boolean activePointer=false;
	private int Px=2050,Py=0;
	
	public C_Arena()
	{
		GameManager.objects.add(new Game.Players.Player());
		
		attack=new Image("/UI/Attack_Button.png");
		parry=new Image("/UI/Parry_Button.png");
		heal=new Image("/UI/Heal_Button.png");
		
		panel= new Image("/UI/Panel.png");
		pointer= new Image("/UI/Pointer2.png");
	}
	
	public void update(GameEngine ge, float dt)
	{
		if(Scripts.isClicked(attack,ax,ay))
		{
			Aing=true;
			activePointer=true;
			Py=ay;			
		}
		
		if(Scripts.isClicked(parry,px,py))
		{
			Ping=true;
			activePointer=true;
			Py=py;	
		}
		
		if(Scripts.isClicked(heal,hx,hy))
		{
			Hing=true;
			activePointer=true;
			Py=hy;
		}
	}

	public void render(GameEngine ge, Renderer r)
	{
		r.drawImage(panel,2000, 350);
		
		r.drawImage(attack,ax,ay);
		r.drawImage(parry,px,py);
		r.drawImage(heal,hx,hy);
		
		if(activePointer)
		{
			r.drawImage(pointer, Px, Py);
		}
	}
}
