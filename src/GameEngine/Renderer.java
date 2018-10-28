package GameEngine;

import java.awt.image.DataBufferInt;

import GameEngine.GFX.Image;

public class Renderer
{
	private int pW,pH;
	private int[] p;
	private int camX,camY;
	
	public Renderer(GameEngine ge)
	{
		pW=ge.getWidth();
		pH=ge.getHeight();
		p=((DataBufferInt)ge.getWindow().getImage().getRaster().getDataBuffer()).getData();	
	}
	
	public void clear()
	{
		for (int i=0; i<p.length; i++)	{p[i]=0xffCADAFF;}
	}
	
	public void setPixel(int x, int y, int value)
	{
		x-=camX;
		y-=camY;
		if ((x<0 || x>=pW || y<0 || y>=pH) || value == 0xffff00ff)
		{
			return;
		}
		p[x+y*pW]=value;	
	}
	
	public void drawImage(Image image, int offX, int offY)
	{
		int newx=0;
		int newy=0;
		int newW=image.getW();
		int newH=image.getH();
		
		for(int y=newy;y<newH;y++)
		{
			for(int x=newx;x<newW;x++)
			{
				setPixel(x+offX,y+offY,image.getP()[x+y*image.getW()]);
			}
		}
	}

	public int getCamX()
	{
		return camX;
	}

	public void setCamX(int camX)
	{
		this.camX = camX;
	}

	public int getCamY()
	{
		return camY;
	}

	public void setCamY(int camY)
	{
		this.camY = camY;
	}
}
