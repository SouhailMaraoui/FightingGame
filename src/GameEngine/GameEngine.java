package GameEngine;

import Game.Camera;

public class GameEngine implements Runnable
{
	private Thread thread;
	private Window window;
	private Renderer renderer;
	private Input input;
	private AbstractGame game;
	private Camera camera;
	public static int sleep=10;

	private int width=1000, height=550;

	private String title="Fighting Game";
	
	public GameEngine(AbstractGame game)
	{
		this.game=game;
	}
	
	public void start()
	{
		window   =new Window(this);
		renderer =new Renderer(this);
		input    =new Input(this);
		thread   =new Thread(this);
		
		thread.run();
	}
	
	public void run()
	{	
		while(true)
		{			
			game.update(this,1f);
			input.update();

			renderer.clear();
			game.render(this, renderer);
			window.update();	
			
			try   {Thread.sleep(sleep);}
			catch (InterruptedException e){};	
		}
	}
	
	public int getWidth() 
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public Window getWindow()
	{
		return window;
	}

	public Input getInput()
	{
		return input;
	}

	public String getTitle()
	{
		return title;
	}

	public Renderer getRenderer()
	{
		return renderer;
	}

	public Camera getCamera()
	{
		return camera;
	}
}