package Game;

import GameEngine.GameEngine;
import GameEngine.Renderer;
import GameEngine.GFX.Image;

public class player2 extends GameObject
{
	private Image image;
	private int x=886;
	private int y=10;
	
	public player2()
	{
		image=new Image("/Player2.png");
	}
	
	public void update(GameEngine ge, float dt)
	{
	}

	public void render(GameEngine ge, Renderer r)
	{
		r.drawImage(image,x,y);
		
	}
}
