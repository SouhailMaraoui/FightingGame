package Game;

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
	
	public static int max(int a,int b)
	{
		if (a>b)
			return a;
		return b;
	}
}
