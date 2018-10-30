package classes;

import java.util.Random;
import GameEngine.GameEngine;

public class Classe
{
	protected int force=0;
	protected int dexterity=0;
	protected int intelligence=0;
	protected int concentration=0;
	
	public Classe(int force,int dexterity, int intelligence, int concentration)
	{
		this.force=force;
		this.dexterity=dexterity;
		this.intelligence=intelligence;
		this.concentration=concentration;

	}
	
	public void update(GameEngine ge, float dt)
	{
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
}