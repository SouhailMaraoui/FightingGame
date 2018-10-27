package Game;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import GameEngine.GameEngine;
import GameEngine.Renderer;
import GameEngine.GFX.Image;

public class UI extends GameObject
{
	private Image attack, parry, heal;
	private Image panel, pointer;
	
	private int ax=100,ay=375;
	private int px=100,py=425;
	private int hx=100,hy=475;
	
	private boolean activePointer=false;
	private int Px=50,Py=0;
	
	public UI()
	{
		attack=new Image("/UI/Attack_Button.png");
		parry=new Image("/UI/Parry_Button.png");
		heal=new Image("/UI/Heal_Button.png");
		
		panel= new Image("/UI/Panel.png");
		pointer= new Image("/UI/Pointer2.png");
	}
	
	public void start()
	{
	}
	
	public void update(GameEngine ge, float dt)
	{
		int MX=ge.getInput().getMouseX();
		int MY=ge.getInput().getMouseY();
		if(ge.getInput().isKeyDown(MouseEvent.BUTTON1) && MX>ax  && MX<(ax+attack.getW()) && MY>ay && MY<(ay+attack.getH()) )
		{
			System.out.println("Attack");
			activePointer=true;
			Py=ay;
			
		}
		if(ge.getInput().isKeyDown(MouseEvent.BUTTON1) && MX>px  && MX<(py+parry.getW()) && MY>py && MY<(py+parry.getH()) )
		{
			System.out.println("Parry");
			activePointer=true;
			Py=py;

			
		}
		if(ge.getInput().isKeyDown(MouseEvent.BUTTON1) && MX>hx  && MX<(hy+heal.getW()) && MY>hy && MY<(hy+heal.getH()) )
		{
			System.out.println("Heal");
			activePointer=true;
			Py=hy;
			
		}
	}

	public void render(GameEngine ge, Renderer r)
	{
		r.drawImage(panel,0, 350);
		
		r.drawImage(attack,ax,ay);
		r.drawImage(parry,px,py);
		r.drawImage(heal,hx,hy);
		
		if(activePointer)
		{
			r.drawImage(pointer, Px, Py);
		}
		
	}

}
