package Game;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import GameEngine.AbstractGame;
import GameEngine.GameEngine;
import GameEngine.Renderer;

public class GameManager extends AbstractGame
{
	
	public static final boolean canNext = false;
	public static ArrayList<GameObject> objects= new ArrayList<GameObject>();
	private Camera camera;
	public static int offX=0;
	public static int offY=0;
	public static int MX=0;
	public static int MY=0;
	public static boolean MB=false;
	public static boolean EB=false;
	public static boolean LB=false;
	public static boolean RB=false;
	public static boolean Esc=false;
	public static boolean End=false;
	
	public GameManager()
	{
		camera= new Camera();
		objects.add(new Game.Rooms.A_ClassSelect());		
	}
	
	public void update(GameEngine ge, float dt)
	{		
		camera.update(ge,dt);

		offX=camera.getOffX();
		offY=camera.getOffY();

		MX=ge.getInput().getMouseX();
		MY=ge.getInput().getMouseY();
		MB=ge.getInput().isKeyDown(MouseEvent.BUTTON1);
		EB=ge.getInput().isKeyDown(KeyEvent.VK_ENTER);
		LB=ge.getInput().isKey(KeyEvent.VK_LEFT);
		RB=ge.getInput().isKey(KeyEvent.VK_RIGHT);
		Esc=ge.getInput().isKeyDown(KeyEvent.VK_ESCAPE);

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