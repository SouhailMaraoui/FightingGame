package Classes;

import java.util.Random;

import Game.Rooms.A_ClassSelect;
import GameEngine.GameEngine;
import GameEngine.GFX.Image;

public class Classe
{
	private String tag;
	protected int force=0;
	protected int dexterity=0;
	protected int intelligence=0;
	protected int concentration=0;
	
	public static Image[] image=new Image[5];
	public static int[][] imagePos=new int[5][2];
	public static String[][] weaponTag=new String[3][2];
	public static Weapon[][] weapon=new Weapon[3][2];
	
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
		//player=A_ClassSelect.getPlayer();
	}
	
	private int ifHit(int hit,float chance)
	{
		int h=0;
		Random r= new Random(); 
		float i=r.nextFloat();
		if(i<chance)
		{
			h=hit;
		}
		System.out.println("chance= "+chance+" (i="+i+"), hit= "+h);
		return h;
	}
	
	public int[] Attack(Weapon weapon)
	{
		int chance=0;
		int damage=0;
		
		if(weapon.getTag()=="Sword")
		{
			chance=dexterity*weapon.getManiabilty()/10000;
			damage=force*weapon.getImpact()/100;
		}
		if(weapon.getTag()=="Staff")
		{
			chance=dexterity*weapon.getManiabilty()/10000;
			damage=force*weapon.getImpact()/100;
		}
		int[] r= {damage, chance};
		return r;
	}
	
	public int[] Parry(Weapon weapon)
	{
		int chance=0;
		int deflect=0;
		
		if(weapon.getTag()=="Sword")
		{
			chance=dexterity*weapon.getManiabilty()/10000;
			deflect=force*weapon.getParade()/100;
		}
		if(weapon.getTag()=="Staff")
		{
			chance=concentration*weapon.getFacility()/10000;
			deflect=intelligence*weapon.getEfficacity()/100;
		}
		if(weapon.getTag()=="Shield")
		{
			chance=dexterity*weapon.getManiabilty()/10000;
			deflect=force*weapon.getProtection()/100;
		}
		int[] r= {deflect, chance};
		return r;
	}
	
	
	public int[] Heal(Weapon weapon)
	{
		int chance=0;
		int heal=0;
		
		if(weapon.getTag()=="Potion")
		{
			chance=dexterity*weapon.getFacility()/10000;
			heal=dexterity*weapon.getEfficacity()/100;
		}
		if(weapon.getTag()=="Staff")
		{
			chance=concentration*weapon.getFacility()/10000;
			heal=intelligence*weapon.getEfficacity()/100;
		}
		int[] r= {heal, chance};
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

	public static String[][] getWeaponTag()
	{		
		return weaponTag;
	}
	public static Weapon[][] getWeapon()
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
}