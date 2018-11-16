package Game.Rooms;

import java.util.ArrayList;

import Classes.Classe;
import Classes.Weapon;
import Game.GameManager;
import Game.GameObject;
import Game.Scripts;
import Game.Players.Player1;
import Game.Players.Player2;
import GameEngine.GameEngine;
import GameEngine.Renderer;
import GameEngine.GFX.Image;

public class C_Arena extends GameObject
{
	public static boolean canNext;
	
	private Classe p1,p2;
	
	public static ArrayList<Image> weaponList= new ArrayList<Image>();
	private Weapon[][] weapon;
	
	public static boolean myTurn;
	
	private Image attack, parry, heal;
	private Image hit,chance,spell;
	private Image panel, pointer,end;
	
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
		GameManager.objects.add(new Game.Players.Player2());
		GameManager.objects.add(new Game.Players.Player1());
		
		myTurn=true;
		p1=A_ClassSelect.getP1();
		p2=A_ClassSelect.getP2();
		weapon=p1.getWeapon();
		

		
		attack	=	new Image("/UI/Attack_Button.png");
		parry	= 	new Image("/UI/Parry_Button.png");
		heal	= 	new Image("/UI/Heal_Button.png");
		
		hit		=	new Image("/UI/Info/Hit.png");
		chance	=	new Image("/UI/Info/Chance.png");
		spell=new Image("/Sprites/Spell.png");
		
		panel= new Image("/UI/Panel2.png");
		pointer= new Image("/UI/Pointer2.png");
		
	}

	public void update(GameEngine ge, float dt)
	{
		if(myTurn && Scripts.isClicked(attack,ax,ay))	 {T1[0]=true;T1[1]=false;T1[2]=false;}
		if(myTurn && Scripts.isClicked(parry,px,py))	 {T1[0]=false;T1[1]=true;T1[2]=false;}
		if(myTurn && Scripts.isClicked(heal,hx,hy))	 {T1[0]=false;T1[1]=false;T1[2]=true;}

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
		
		if(p1.getVitality()<=0)
		{
			Player1.Missed=false;
			end=new Image("/UI/Loss.png");
			canNext=true;
		}
		if(p2.getVitality()<=0)
		{
			Player2.Missed=false;
			end=new Image("/UI/Win.png");
			canNext=true;
		}
		
	}

	public void render(GameEngine ge, Renderer r)
	{
		Iy=375;
		if(p2.getVitality()<0 || p1.getVitality()<0) {r.drawImage(end, 2350, 100);}
		if(Game.Players.Player2.drawSpell)
		{
			r.drawImage(spell, 2100, 100);
		}
		
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
			r.drawImage(hit,2754,395);
			r.drawImage(chance,2740,445);
			r.drawNumber((int)Player1.playerAction[0], 2845, 405);
			r.drawNumber((int)Player1.playerAction[1], 2845, 455);
		}
		
		for(Image i:weaponList)
		{
			r.drawImage(i, Ix, Iy);
			Iy+=50;
		}
		
		//Draw-Player-1-Health---------------------------------------------------------------------------
		r.drawImage(p1.getLogo(), 2010,10);
		r.drawRectangle(2060, 23, p1.getVitality()+Player1.toBeParried, 24, 0xff1f1f1f,1);
		r.drawRectangle(2060, 25, p1.getVitality(), 20, 0xff88313C,1);
		r.drawRectangle(2060, 42, p1.getVitality()+Player1.toBeParried, 3, 0xff8C202F,1);
		if(p1.getVitality()>=0) r.drawNumber(p1.getVitality(), 2000, 65);
		
		//Draw-Player-2-Health---------------------------------------------------------------------------
		r.drawImageReversed(p2.getLogo(), 2940,10);
		r.drawRectangle(2940, 23, p2.getVitality()+Player2.toBeParried, 24, 0xff1f1f1f,-1);
		r.drawRectangle(2940, 25, p2.getVitality(), 20, 0xff88313C,-1);
		r.drawRectangle(2940, 42, p2.getVitality()+Player2.toBeParried, 3, 0xff8C202F,-1);
		if(p2.getVitality()>=0) r.drawNumber(p2.getVitality(), 2920, 65);
		
	}
}