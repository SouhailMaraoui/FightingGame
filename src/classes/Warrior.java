package classes;

public class Warrior extends Classe
{

	private int vitality;
	private int exp=1;
	
	public Warrior(int force, int dexterity, int intelligence, int concentration)
	{
		super(force, dexterity, intelligence, concentration);
		vitality=200-(force+dexterity+intelligence+concentration)+exp*3;
	}
	
	public int getVitality()
	{
		return vitality;
	}
	public void setVitality(int vitality)
	{
		this.vitality = vitality;
	}
	public int getExp()
	{
		return exp;
	}
	public void setExp(int exp)
	{
		this.exp = exp;
	}
	public int[] getFRange()
	{
		int[] range= {20,100+exp};
		return range;
	}
	public int[] getDRange()
	{
		int[] range= {10,100+exp-force};
		return range;
	}
	public int[] getIRange()
	{
		int[] range= {0,100+exp-force-dexterity};
		return range;
	}
	public int[] getCRange()
	{
		int[] range= {0,100+exp-force-dexterity-intelligence};
		return range;
	}
}
