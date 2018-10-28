package Game;

import GameEngine.GameEngine;
import GameEngine.Renderer;
import GameEngine.GFX.Image;

public class player1 extends GameObject
{
	private Image image;
	private int x=10;
	private int y=10;
	
	public player1()
	{
		image=new Image("/Player1.png");
	}
	
	public void update(GameEngine ge, float dt)
	{
	}

	public void render(GameEngine ge, Renderer r)
	{
		r.drawImage(image,x,y);
		
	}
}