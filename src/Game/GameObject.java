package Game;

import GameEngine.GameEngine;
import GameEngine.Renderer;

public abstract class GameObject
{
	public abstract void update(GameEngine ge, float dt);
	public abstract void render(GameEngine ge, Renderer r);
}
