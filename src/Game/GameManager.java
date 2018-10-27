package Game;

import java.util.ArrayList;

import GameEngine.AbstractGame;
import GameEngine.GameEngine;
import GameEngine.Renderer;

public class GameManager extends AbstractGame
{
	
	private ArrayList<GameObject> objects= new ArrayList<GameObject>();
	
	
	public GameManager()
	{
		objects.add(new player1());
		objects.add(new player2());
	}
	
	public void update(GameEngine ge, float dt)
	{
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
