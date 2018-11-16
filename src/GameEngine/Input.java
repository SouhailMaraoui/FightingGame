package GameEngine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Game.GameManager;

public class Input implements KeyListener,MouseListener,MouseMotionListener
{	
	private final int KEYS_NUM=256;
	private boolean[] keys = new boolean[KEYS_NUM];
	private boolean[] keysLast = new boolean[KEYS_NUM];
	private int mouseX,mouseY;
	
	public Input(GameEngine ge)
	{
		mouseX=0;
		mouseY=0;
		ge.getWindow().getCanvas().addKeyListener(this);
		ge.getWindow().getCanvas().addMouseListener(this);
		ge.getWindow().getCanvas().addMouseMotionListener(this);
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
	
	public boolean isKeyDown(int keyCode)
	{
		return keys[keyCode] && !keysLast[keyCode];
	}

	public void mouseMoved		(MouseEvent e)
	{
		mouseX=(int)e.getX();
		mouseY=(int)e.getY();
	}	
	
	public void mouseDragged	(MouseEvent e)	{}	
	public void mouseClicked	(MouseEvent e)	{}
	public void mouseEntered	(MouseEvent e)	{}
	public void mouseExited		(MouseEvent e)	{}
	
	public void mousePressed	(MouseEvent e)	{keys[e.getButton()]=true;}
	public void mouseReleased	(MouseEvent e)	{keys[e.getButton()]=false;}
	
	public void keyPressed		(KeyEvent e)	{keys[e.getKeyCode()]=true;}
	public void keyReleased		(KeyEvent e)	{keys[e.getKeyCode()]=false;}
	public void keyTyped		(KeyEvent e)	{}

	public int getMouseX()	{return mouseX+GameManager.offX;}
	public int getMouseY()	{return mouseY+GameManager.offY;}
	
}
