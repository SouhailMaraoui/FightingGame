package Classes;

import GameEngine.GFX.Image;

public class Weapon
{
	private String tag;
	private Image image;
		
	private int maniabilty;
	private int impact;
	private int parade;
	
	private int facility;
	private int efficacity;
	private int protection;
	
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
		return facility;
	}
	
	public void setTag(String tag)
	{
		this.tag = tag;
	}
	
	public String getTag()
	{
		return tag;
	}
	
	public int getEfficacity()
	{
		return efficacity;
	}


	public int getProtection()
	{
		return protection;
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
