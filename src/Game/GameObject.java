package Game;

import GameEngine.GameEngine;
import GameEngine.Renderer;

public abstract class GameObject
{
	protected String tag;
	protected int posX, posY;
	
	public abstract void update(GameEngine ge, float dt);
	public abstract void render(GameEngine ge, Renderer r);
}
