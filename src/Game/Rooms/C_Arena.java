package Game.Rooms;

import java.util.ArrayList;

import Classes.Classe;
import Classes.Warrior;
import Classes.Weapon;
import Game.GameManager;
import Game.GameObject;
import Game.Scripts;
import Game.Players.Player;
import GameEngine.GameEngine;
import GameEngine.Renderer;
import GameEngine.GFX.Image;

public class C_Arena extends GameObject
{
	private Classe player;
	
	public static ArrayList<Image> weaponList= new ArrayList<Image>();
	String[][] weaponTag;
	
	private Image attack, parry, heal;
	private Image sword,dagger,staff,shield,banner,potion,grimoire,hit,chance;
	private Image panel, pointer;
	
	private int ax=2100,ay=375;
	private int px=2100,py=425;
	private int hx=2100,hy=475;

	public static boolean[][] T= {{false,false,false},{false,false,false},{false,false,false}};
	
	public static boolean activePointer=false;
	private boolean additionalInfo=false;
	
	private int Px=2050,Py=375;
	private int Ix=2400,Iy=375;
	
	public C_Arena()
	{
		player=A_ClassSelect.getPlayer();
		GameManager.objects.add(new Game.Players.Enemy());
		GameManager.objects.add(new Game.Players.Player());
		
		attack	=	new Image("/UI/Attack_Button.png");
		parry	= 	new Image("/UI/Parry_Button.png");
		heal	= 	new Image("/UI/Heal_Button.png");
		
		sword	=	new Image("/UI/Info/Sword.png");
		dagger	=	new Image("/UI/Info/Dagger.png");
		staff	=	new Image("/UI/Info/Staff.png");
		shield	=	new Image("/UI/Info/Shield.png");
		banner	=	new Image("/UI/Info/Banner.png");
		potion	=	new Image("/UI/Info/Potion.png");
		grimoire=	new Image("/UI/Info/Grimoire.png");
		
		hit		=	new Image("/UI/Info/Hit.png");
		chance	=	new Image("/UI/Info/Chance.png");
				
		
		weaponTag=Classe.getWeaponTag();
		
		panel= new Image("/UI/Panel.png");
		pointer= new Image("/UI/Pointer2.png");
	}
	
	public void update(GameEngine ge, float dt)
	{
		//Attack--------------------------------------------------------------------------------------------
		if(Scripts.isClicked(attack,ax,ay))
		{
			weaponList.clear();
			T[0][0]=true;T[1][0]=false;T[2][0]=false;
			
			if(Scripts.isSinT("Sword", weaponTag[0]))	{weaponList.add(sword);}
			if(Scripts.isSinT("Staff", weaponTag[0]))	{weaponList.add(staff);}
			if(Scripts.isSinT("Dagger",weaponTag[0]))	{weaponList.add(dagger);}
			
			activePointer=true;
			additionalInfo=true;
			Py=ay;
		}
		if(T[0][0])
		{
			if(Scripts.isClicked(sword, Ix, 375))	{T[0][1]=true;}
			if(Scripts.isClicked(staff, Ix, 375))	{T[0][2]=true;}
			if(Scripts.isClicked(dagger, Ix, 425))	{T[0][1]=true;}
		}
		
		//Parry---------------------------------------------------------------------------------------------
		if(Scripts.isClicked(parry,px,py))
		{
			weaponList.clear();
			T[0][0]=false;T[1][0]=true;T[2][0]=false;
			
			if(Scripts.isSinT("Sword", weaponTag[1]))	{weaponList.add(sword);}
			if(Scripts.isSinT("Staff", weaponTag[1]))	{weaponList.add(staff);}
			if(Scripts.isSinT("Shield",weaponTag[1]))	{weaponList.add(shield);}
			
			activePointer=true;
			additionalInfo=true;
			Py=py;	
		}
		if(T[1][0])
		{
			if(Scripts.isClicked(sword, Ix, 375))	{T[1][1]=true;}
			if(Scripts.isClicked(staff, Ix, 375))	{T[1][1]=true;}
			if(Scripts.isClicked(shield, Ix, 425))	{T[1][2]=true;}
		}
		
		//Heal----------------------------------------------------------------------------------------------
		if(Scripts.isClicked(heal,hx,hy))
		{
			weaponList.clear();
			T[0][0]=false;T[1][0]=false;T[2][0]=true;
			
			if(Scripts.isSinT("Banner", weaponTag[2]))	{weaponList.add(banner);}
			if(Scripts.isSinT("Potion", weaponTag[2]))	{weaponList.add(potion);}
			if(Scripts.isSinT("Grimoire",weaponTag[2])){weaponList.add(grimoire);}
			
			activePointer=true;
			additionalInfo=true;
			Py=hy;
		}
		if(T[2][0])
		{
			if(Scripts.isClicked(banner, Ix, 375))	{T[2][1]=true;}
			if(Scripts.isClicked(potion, Ix, 375))	{T[2][1]=true;}
			if(Scripts.isClicked(grimoire, Ix, 375)){T[2][2]=true;}
		}
	}

	public void render(GameEngine ge, Renderer r)
	{
		Iy=375;
		r.drawImage(panel,2000, 350);
		r.drawImage(attack,ax,ay);
		r.drawImage(parry,px,py);
		r.drawImage(heal,hx,hy);
		
		if(activePointer)
		{
			r.drawImage(pointer, Px, Py);
		}
		for(Image i:weaponList)
		{
			r.drawImage(i, Ix, Iy);
			Iy+=50;
		}
	}
}