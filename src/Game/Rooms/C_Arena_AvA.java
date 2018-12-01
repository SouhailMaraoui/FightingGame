package Game.Rooms;

import Classes.Classe;
import Game.GameManager;
import Game.GameObject;
import Game.Players.AI_Left;
import Game.Players.AI_Right;
import GameEngine.GameEngine;
import GameEngine.Renderer;
import GameEngine.GFX.Image;

public class C_Arena_AvA extends GameObject
{
	public static boolean canNext;
	
	private Classe p1,p2;
	private AI_Left P1;
	private AI_Right P2;	
	private Image end,spell;
	
	public C_Arena_AvA()
	{
		P1=new AI_Left();
		P2=new AI_Right();
		
		GameManager.objects.add(P1);
		GameManager.objects.add(P2);
		
		p1=A_ClassSelect.getP1();
		p2=A_ClassSelect.getP2();
		
		spell	=	new Image("/Sprites/Spell.png");
	}

	public void update(GameEngine ge, float dt)
	{
		if(p1.getVitality()<=0)
		{
			end=new Image("/UI/Loss.png");
			canNext=true;
		}
		if(p2.getVitality()<=0)
		{
			end=new Image("/UI/Win.png");
			canNext=true;
		}
	}

	public void render(GameEngine ge, Renderer r)
	{
		if(p2.getVitality()<0 || p1.getVitality()<0) {r.drawImage(end, 2350, 100);}
		
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