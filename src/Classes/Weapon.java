package Classes;

import GameEngine.Image;

public class Weapon
{
	private String tag;
	private Image image;
		
	private int maniabilty;
	private int impact;
	private int parade;
	
	public Weapon(int maniabilty,int impact, int parade)
	{
		this.maniabilty=maniabilty;
		this.impact=impact;
		this.parade=parade;
	}
	
	public int getManiabilty()
	{
		return maniabilty;
	}

	public int getImpact()
	{
		return impact;
	}

	public int getParade()
	{
		return parade;
	}
	
	public int getFacility()
	{
		return maniabilty;
	}
	
	public int getEfficacity()
	{
		return impact;
	}

	public int getProtection()
	{
		return parade;
	}
	
	public void setTag(String tag)
	{
		this.tag = tag;
	}
	
	public String getTag()
	{
		return tag;
	}
	
	public void setImage(String path)
	{
		this.image=new Image(path);
	}
	
	public Image getImage()
	{
		return this.image;
	}
	
}
