package Game.Rooms;

import java.util.ArrayList;

import Classes.Classe;
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
	private Weapon[][] weapon;
	
	private Image attack, parry, heal;
	private Image hit,chance;
	private Image panel, pointer;
	
	private int ax=2100,ay=375;
	private int px=2100,py=425;
	private int hx=2100,hy=475;
	
	public static int spr=0;
	
	public static boolean[]   T1= {false,false,false};
	public static boolean[][] T2= {{false,false},{false,false},{false,false}};
	
	public static boolean activePointer=false;
	public static boolean additionalInfo=false;
	
	private int Px=2050,Py=375;
	private int Ix=2400,Iy=375;
	
	public C_Arena()
	{
		player=A_ClassSelect.getPlayer();
		weapon=Classe.getWeapon();
		
		GameManager.objects.add(new Game.Players.Enemy());
		GameManager.objects.add(new Game.Players.Player());
		
		attack	=	new Image("/UI/Attack_Button.png");
		parry	= 	new Image("/UI/Parry_Button.png");
		heal	= 	new Image("/UI/Heal_Button.png");
		
		hit		=	new Image("/UI/Info/Hit.png");
		chance	=	new Image("/UI/Info/Chance.png");
						
		panel= new Image("/UI/Panel.png");
		pointer= new Image("/UI/Pointer2.png");
	}

	public void update(GameEngine ge, float dt)
	{
		if(Scripts.isClicked(attack,ax,ay))	 {T1[0]=true;T1[1]=false;T1[2]=false;}
		if(Scripts.isClicked(parry,px,py))	 {T1[0]=false;T1[1]=true;T1[2]=false;}
		if(Scripts.isClicked(heal,hx,hy))	 {T1[0]=false;T1[1]=false;T1[2]=true;}

		for(int i=0;i<T1.length;i++)
		{
			if (T1[i])
			{
				weaponList.clear();
				for(Weapon w:weapon[i])
				{
					if(w!=null) {weaponList.add(w.getImage());}
				}
				Py=375+50*i;
				activePointer=true;
			}
		}
	
		for(int k=0;k<3;k++)
		{
			if(T1[k])
			{
				for(int i=0;i<weaponList.size();i++)
				{
					spr+=1;
					Iy=375+50*i;
					if(Scripts.isHovered(weaponList.get(i),Ix, Iy)) {T2[k][i]=true;}
					else {T2[k][i]=false;}
				}
			}
		}
		if(Scripts.isTrueInList(T2))	{additionalInfo=true;}
		else 							{additionalInfo=false;}
		
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
		
		if(additionalInfo)
		{
			r.drawImage(hit,2700,375);
			r.drawImage(chance,2700,425);
			r.drawNumber(Player.playerAction[0], 2805, 385);
			r.drawNumber(Player.playerAction[1], 2805, 435);
		}
		for(Image i:weaponList)
		{
			r.drawImage(i, Ix, Iy);
			Iy+=50;
		}
	}
}