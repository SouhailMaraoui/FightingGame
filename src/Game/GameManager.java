package Game;

import java.util.ArrayList;

import GameEngine.AbstractGame;
import GameEngine.GameEngine;
import GameEngine.Renderer;

public class GameManager extends AbstractGame
{
	
	private ArrayList<GameObject> objects= new ArrayList<GameObject>();
	private Camera camera;
	public static int offX;
	public static int offY;
	
	public GameManager()
	{
		camera= new Camera();
		objects.add(new UI());
		objects.add(new player1());
		objects.add(new player2());
	}
	
	public void update(GameEngine ge, float dt)
	{
		camera.update(ge,dt);
		offX=camera.getOffX();
		offY=camera.getOffY();
		
		for (int i=0;i<objects.size(); i++)
		{
			objects.get(i).update(ge, dt);
		}
		
	}
	
	public void render(GameEngine ge, Renderer r)
	{
		for(GameObject obj : objects)
		{
			obj.render(ge, r);
		}
	}
	
	public static void main(String args[])
	{
		GameEngine ge = new GameEngine(new GameManager());
		ge.start();
	}
}
