package GameEngine;

import java.awt.image.DataBufferInt;
import GameEngine.GFX.Image;

public class Renderer
{
	private int pW,pH;
	private int[] p;
	private int camX,camY;
	
	Image n1=new Image("/Numbers/1.png");
	Image n2=new Image("/Numbers/2.png");
	Image n3=new Image("/Numbers/3.png");
	Image n4=new Image("/Numbers/4.png");
	Image n5=new Image("/Numbers/5.png");
	Image n6=new Image("/Numbers/6.png");
	Image n7=new Image("/Numbers/7.png");
	Image n8=new Image("/Numbers/8.png");
	Image n9=new Image("/Numbers/9.png");
	Image n0=new Image("/Numbers/0.png");
	
	public Renderer(GameEngine ge)
	{
		pW=ge.getWidth();
		pH=ge.getHeight();
		p=((DataBufferInt)ge.getWindow().getImage().getRaster().getDataBuffer()).getData();	
	}
	
	public void clear()
	{
		for (int i=0; i<p.length; i++)	{p[i]=0xffffffFF;}
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
	
	public void drawNumber(int i,int x, int y)
	{
		
		char[] t=(Integer.toString(i)).toCharArray();
		for(char s:t)
		{
			switch(s)
			{
			case '1':	drawImage(n1,x,y);break;
			case '2':	drawImage(n2,x,y);break;
			case '3':	drawImage(n3,x,y);break;
			case '4':	drawImage(n4,x,y);break;
			case '5':	drawImage(n5,x,y);break;
			case '6':	drawImage(n6,x,y);break;
			case '7':	drawImage(n7,x,y);break;
			case '8':	drawImage(n8,x,y);break;
			case '9':	drawImage(n9,x,y);break;
			case '0':	drawImage(n0,x,y);break;
			}
			x+=10;
		}
	}

	public void drawRectangle(int offX,int offY,int width, int height, int color)
	{
		for(int y=0;y<height;y++)
		{
			for(int x=0;x<width;x++)
			{
				setPixel(x+offX,y+offY,color);
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