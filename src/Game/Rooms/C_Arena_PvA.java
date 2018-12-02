package Game.Rooms;

import java.util.ArrayList;

import Classes.Fighter;
import Classes.Weapon;
import Game.GameManager;
import Game.GameObject;
import Game.Scripts;
import Game.Players.Player_Left;
import GameEngine.GameEngine;
import GameEngine.Image;
import GameEngine.Renderer;

public class C_Arena_PvA extends GameObject
{
	public static boolean canNext=false;
	
	private Fighter p1,p2;
	public static Fighter winner=null,losser=null;
	
	public static ArrayList<Image> weaponList= new ArrayList<Image>();
	private Weapon[][] weapon1;
	
	public static boolean P1Turn,P2Turn;
	
	private Image attack, parry, heal;
	private Image hit,chance,spell;
	private Image panel, pointer,end,role;
	
	private int ax=2100,ay=375;
	private int px=2100,py=425;
	private int hx=2100,hy=475;
		
	public static boolean[]   P1T1= {false,false,false};
	public static boolean[][] P1T2= {{false,false},{false,false},{false,false}};
	
	public static boolean activePointer=false;
	public static boolean additionalInfo=false;
	
	private int Px=2050,Py=375;
	private int Ix=2400,Iy=375;
	
	public C_Arena_PvA()
	{
		winner=losser=null;
		GameManager.objects.add(new Game.Players.AI_Right());
		GameManager.objects.add(new Game.Players.Player_Left());
		

		p1=A_ClassSelect.getP1();
		p2=A_ClassSelect.getP2();
		
		weapon1=p1.getWeapon();

		attack	=	new Image("/UI/Attack_Button.png");
		parry	= 	new Image("/UI/Parry_Button.png");
		heal	= 	new Image("/UI/Heal_Button.png");
		
		hit		=	new Image("/UI/Info/Hit.png");
		chance	=	new Image("/UI/Info/Chance.png");
		spell	=	new Image("/Sprites/Spell.png");
		role	=	new Image("/UI/Role.png");
		
		panel= new Image("/UI/Panel2.png");
		pointer= new Image("/UI/Pointer2.png");
		
	}

	public void update(GameEngine ge, float dt)
	{
		if(p1.isMyTurn() && Scripts.isClicked(attack,ax,ay))	 {P1T1[0]=true;P1T1[1]=false;P1T1[2]=false;}
		if(p1.isMyTurn() && Scripts.isClicked(parry,px,py))	 {P1T1[0]=false;P1T1[1]=true;P1T1[2]=false;}
		if(p1.isMyTurn() && Scripts.isClicked(heal,hx,hy))	 {P1T1[0]=false;P1T1[1]=false;P1T1[2]=true;}

		for(int i=0;i<P1T1.length;i++)
		{
			if (P1T1[i])
			{
				weaponList.clear();
				for(Weapon w:weapon1[i])
				{
					if(w!=null) {weaponList.add(w.getImage());}
				}
				Py=375+50*i;
				activePointer=true;
			}
		}
	
		for(int k=0;k<3;k++)
		{
			if(P1T1[k])
			{
				for(int i=0;i<weaponList.size();i++)
				{
					Iy=375+50*i;
					if(Scripts.isHovered(weaponList.get(i),Ix, Iy)) {P1T2[k][i]=true;}
					else {P1T2[k][i]=false;}
				}
			}
		}
		if(Scripts.isTrueInList(P1T2))	{additionalInfo=true;}
		else 							{additionalInfo=false;}
		
		if(p1.getVitality()<=0 && winner==null)
		{
			winner=p2;
			losser=p1;
			end=new Image("/UI/Loss.png");
			canNext=true;
			p1.setMyTurn(false);
			p2.setMyTurn(false);
		}
		if(p2.getVitality()<=0 && winner==null)
		{
			winner=p1;
			losser=p2;
			end=new Image("/UI/Win.png");
			canNext=true;
			p1.setMyTurn(false);
			p2.setMyTurn(false);
		}
		
	}

	public void render(GameEngine ge, Renderer r)
	{
		Iy=375;
		if(p2.getVitality()<0 || p1.getVitality()<0) {r.drawImage(end, 2350, 100);}
		
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
			r.drawNumber((int)Player_Left.playerAction[0], 2845, 405);
			r.drawNumber((int)Player_Left.playerAction[1], 2845, 455);
		}
		
		for(Image i:weaponList)
		{
			r.drawImage(i, Ix, Iy);
			Iy+=50;
		}
		
		//Draw-Player-1-Health---------------------------------------------------------------------------
		r.drawImage(p1.getLogo(), 2010,10);
		r.drawRectangle(2060, 23, p1.getVitality()+p1.getToBeParried(), 24, 0xff1f1f1f,1);
		r.drawRectangle(2060, 25, p1.getVitality(), 20, 0xff88313C,1);
		r.drawRectangle(2060, 42, p1.getVitality()+p1.getToBeParried(), 3, 0xff8C202F,1);
		if(p1.getVitality()>=0) r.drawNumber(p1.getVitality(), 2000, 65);
		
		//Draw-Player-2-Health---------------------------------------------------------------------------
		r.drawImageReversed(p2.getLogo(), 2940,10);
		r.drawRectangle(2940, 23, p2.getVitality()+p2.getToBeParried(), 24, 0xff1f1f1f,-1);
		r.drawRectangle(2940, 25, p2.getVitality(), 20, 0xff88313C,-1);
		r.drawRectangle(2940, 42, p2.getVitality()+p2.getToBeParried(), 3, 0xff8C202F,-1);
		if(p2.getVitality()>=0) r.drawNumber(p2.getVitality(), 2920, 65);
		
		if(p1.isMyTurn())
		{
			r.drawImage(role, 2110, 300);
		}
		if(p2.isMyTurn())
		{
			r.drawImage(role, 2720, 300);
		}
		
		if(p1.isDrawSpell())
		{
			r.drawImage(spell,2700, 100);
		}
		if(p2.isDrawSpell())
		{
			r.drawImage(spell,2100, 100);
		}
	}
}