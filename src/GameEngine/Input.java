package GameEngine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener
{
	
	private final int KEYS_NUM=256;
	private boolean[] keys = new boolean[KEYS_NUM];
	private boolean[] keysLast = new boolean[KEYS_NUM];
	
	public Input(GameEngine ge)
	{		
		ge.getWindow().getCanvas().addKeyListener(this);
	}
	
	public void update()
	{
		for(int i=0; i<KEYS_NUM;i++)
		{
			keysLast[i]=keys[i];
		}
	}

	public boolean isKey(int keyCode)
	{
		return keys[keyCode];
	}
	
	public boolean isKeyUp(int keyCode)
	{
		return !keys[keyCode] && keysLast[keyCode];
	}
	
	public boolean isKeyDown(int keyCode)
	{
		return keys[keyCode] && !keysLast[keyCode];
	}
	
	
	public void keyPressed(KeyEvent e)
	{
		keys[e.getKeyCode()]=true;
	}

	public void keyReleased(KeyEvent e)
	{
		keys[e.getKeyCode()]=false;
	}

	public void keyTyped(KeyEvent e)
	{
		
	}
}
