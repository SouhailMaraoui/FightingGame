package Game;

import java.util.Random;

import GameEngine.GFX.Image;

public class Scripts
{
	public static boolean isCollision(int x1,int y1,Image image,int x2, int y2,int room_num)
	{
		return (x1>x2  && x1<(x2+image.getW()) && y1>y2 && y1<(y2+image.getH()));
	}
	
	public static boolean isClicked(Image image,int x2, int y2)
	{
		return GameManager.MX>x2  && GameManager.MX<(x2+image.getW()) && GameManager.MY>y2 && GameManager.MY<(y2+image.getH()) && GameManager.MB;
	}
	
	public static boolean isHovered(Image image,int x2, int y2)
	{
		return GameManager.MX>x2  && GameManager.MX<(x2+image.getW()) && GameManager.MY>y2 && GameManager.MY<(y2+image.getH());
	}
	
	public static boolean isTrueInList(boolean[][] T)
	{
		for(int i=0;i<T.length;i++)
		{
			for(int j=0;j<T[0].length;j++)
			{
				if(T[i][j]==true)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public static int ifHit(int hit,int chance)
	{
		int h=0;
		Random r= new Random(); 
		float i=r.nextInt(100);
		if(i<chance)
		{
			h=hit;
		}
		System.out.println("chance= "+chance+" (i="+i+"), hit= "+h);
		return h;
	}
	
	public static int max(int a,int b)
	{
		if (a>b)
			return a;
		return b;
	}
	public static boolean isSinT(String S,String[] T)
	{
		for(int i=0;i<T.length;i++)
		{
			if(S==T[i])
				return true;
		}
		return false;
	}
	
	
}
