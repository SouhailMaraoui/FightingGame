package classes;

import java.util.Random;

public class Class
{
	private int exp;
	private int force;
	private int dexterity;
	private int intelligence;
	private int concentration;
	private int vitality;

	public Class(int force,int dexterity, int intelligence, int concentration, int vitality,int exp)
	{
		this.force=force;
		this.dexterity=dexterity;
		this.intelligence=intelligence;
		this.concentration=concentration;
		this.vitality=vitality;
		this.exp=exp;
	}
	
	private int ifHit(int hit,int chance)
	{
		int h=0;
		Random r= new Random(); 
		int i=r.nextInt(100);
		if(chance<i)
		{
			h=hit;
		}
		return h;
	}
	
	public int Attack(Weapon weapon)
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
		
		return ifHit(damage, chance);
	}
	
	public int Parade(Weapon weapon)
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
		return ifHit(deflect, chance);
	}
	public int Heal(Weapon weapon)
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
		return ifHit(heal, chance);
	}

	public int getExp()
	{
		return exp;
	}

	public void setExp(int exp)
	{
		this.exp = exp;
	}

	public int getVitality()
	{
		return vitality;
	}

	public void setVitality(int vitality)
	{
		this.vitality = vitality;
	}
}