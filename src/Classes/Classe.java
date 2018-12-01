package Classes;

import GameEngine.GameEngine;
import GameEngine.GFX.Image;

public class Classe
{
	private String tag;
	
	protected int[] stat=new int[4];
	protected Image logo,splashArt;
	
	protected int force=0;
	protected int dexterity=0;
	protected int intelligence=0;
	protected int concentration=0;
	
	protected int vitality;
	protected int initVitality;
	private int toBeParried;
	private boolean myTurn;
	private boolean missed;
	private boolean drawSpell;
	
	protected Image[] image=new Image[5];
	protected int[][] imagePos=new int[5][2];
	protected Weapon[][] weapon=new Weapon[3][2];
	
	public Classe()
	{
		
	}
	
	public void setStat(int force,int dexterity, int intelligence, int concentration)
	{
		this.force=force;
		this.dexterity=dexterity;
		this.intelligence=intelligence;
		this.concentration=concentration;
	}
	
	public void update(GameEngine ge, float dt)
	{
		
	}
	
	public int[] Attack(Weapon weapon)
	{
		float chance=0;
		int damage=0;
		
		if(weapon.getTag()=="Sword" || weapon.getTag()=="Dagger" )
		{
			chance=dexterity*weapon.getManiabilty()/10000f;
			damage=force*weapon.getImpact()/100;
		}
		if(weapon.getTag()=="Staff")
		{
			chance=concentration*weapon.getFacility()/10000f;
			damage=intelligence*weapon.getEfficacity()/100;
		}
		int[] r= {damage, (int)(100*chance)};
		return r;
	}
	
	public int[] Parry(Weapon weapon)
	{
		float chance=0;
		int deflect=0;
		
		if(weapon.getTag()=="Sword")
		{
			chance=dexterity*weapon.getManiabilty()/10000f;
			deflect=force*weapon.getParade()/100;
		}
		if(weapon.getTag()=="Staff")
		{
			chance=concentration*weapon.getFacility()/10000f;
			deflect=intelligence*weapon.getProtection()/100;
		}
		if(weapon.getTag()=="Shield")
		{
			chance=dexterity*weapon.getManiabilty()/10000f;
			deflect=force*weapon.getProtection()/100;
		}
		int[] r= {deflect, (int)(100*chance)};
		return r;
	}
	
	public int[] Heal(Weapon weapon)
	{
		float chance=0;
		int heal=0;
		
		if(weapon.getTag()=="Potion" || weapon.getTag()=="Banner")
		{
			chance=dexterity*weapon.getFacility()/10000f;
			heal=dexterity*weapon.getEfficacity()/100;
		}
		if(weapon.getTag()=="Grimoire")
		{
			chance=concentration*weapon.getFacility()/10000f;
			heal=intelligence*weapon.getEfficacity()/100;
		}
		
		int[] r= {heal,(int)(100*chance)};
		return r;
	}
	
	public int[][] getImagePos()
	{
		return imagePos;
	}
	

	public Image[] getImage()
	{		
		return image;
	}

	public Weapon[][] getWeapon()
	{		
		return weapon;
	}

	public String getTag()
	{
		return tag;
	}

	public void setTag(String tag)
	{
		this.tag = tag;
	}

	public int getVitality()
	{
		return vitality;
	}

	public void setVitality(int vitality)
	{
		this.vitality = vitality;
	}
	
	public void setInitVitality(int vitality)
	{
		this.initVitality = vitality;
		this.vitality = vitality;
	}
	
	public int getInitVitality()
	{
		return initVitality;
	}
	
	public void setVitalityRelative(int RelativeVit)
	{
		this.vitality+=RelativeVit;
	}

	public Image getLogo()
	{
		return logo;
	}


	public Image getSplashArt()
	{
		return splashArt;
	}

	public int[] getStat()
	{
		return stat;
	}
	
	public int getToBeParried()
	{
		return toBeParried;
	}

	public void setToBeParried(int toBeParried)
	{
		this.toBeParried = toBeParried;
	}

	public boolean isMyTurn()
	{
		return myTurn;
	}

	public void setMyTurn(boolean myTurn)
	{
		this.myTurn = myTurn;
	}

	public boolean isMissed()
	{
		return missed;
	}

	public void setMissed(boolean missed)
	{
		this.missed = missed;
	}

	public boolean isDrawSpell()
	{
		return drawSpell;
	}

	public void setDrawSpell(boolean drawSpell)
	{
		this.drawSpell = drawSpell;
	}

}