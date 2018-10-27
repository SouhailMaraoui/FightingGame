package Game;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import GameEngine.GameEngine;
import GameEngine.Renderer;
import GameEngine.GFX.Image;

public class player1 extends GameObject
{
	private Image image;
	private Renderer renderer;
	private int x=10;
	private int y=10;
	
	public player1()
	{
		image=new Image("/Player1.png");
	}
	
	public void start()
	{
		renderer =new Renderer(this);
	}
	public void update(GameEngine ge, float dt)
	{
		int MX=ge.getInput().getMouseX();
		int MY=ge.getInput().getMouseY();
		if(ge.getInput().isKeyDown(MouseEvent.BUTTON1) && MX>x  && MX<image.getW() && MY>y && MY<image.getH() )
		{
			System.out.println("Player 1 is pressed");
			
		}
	}

	public void render(GameEngine ge, Renderer r)
	{
		r.drawImage(image,x,y);
		
	}
}
