package GameEngine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Input implements KeyListener,MouseListener,MouseMotionListener
{
	private final int BUTTONS_NUM=3;
	private boolean[] buttons = new boolean[BUTTONS_NUM];
	private boolean[] buttonsLast = new boolean[BUTTONS_NUM];
	
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
		for(int i=0; i<BUTTONS_NUM;i++)
		{
			buttonsLast[i]=buttons[i];
		}
	}

	public boolean isButtonDown(int keyCode)
	{
		return buttons[keyCode] /*&& !buttonsLast[keyCode]*/;
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

	public int getMouseX()	{return mouseX;}
	public int getMouseY()	{return mouseY;}

	public boolean getKeys(int i)
	{
		return keys[i];
	}
}
