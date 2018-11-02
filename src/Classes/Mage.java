package Classes;

import GameEngine.GFX.Image;

public class Mage extends Classe
{
	public static Image player,I,A,P,H;
	
	public static int px,py;
	public static int Ix,Iy;
	public static int Ax,Ay;
	public static int Px,Py;
	public static int Hx,Hy;
	
	public Mage()
	{		
		Weapon staff=new Weapon(10,10,10);
		Weapon grimoire=new Weapon(10,10,10);
		
		Ix=2100;Iy=100;
		Ax=2100;Ay=50;
		Px=2100;Py=100;
		Hx=2100;Hy=100;
		
		I=new Image("/Sprites/Mage/Arena/Idle.png");
		A=new Image("/Sprites/Mage/Arena/Attack_Staff.png");	Ay=50;
		P=new Image("/Sprites/Mage/Arena/Parry.png");
		H=new Image("/Sprites/Mage/Arena/Heal.png");

		image[0]=I;image[1]=A;image[2]=P;image[3]=H;
		
		imagePos[0][0]=Ix;imagePos[1][0]=Ax;imagePos[2][0]=Px;imagePos[3][0]=Hx;
		imagePos[0][1]=Iy;imagePos[1][1]=Ay;imagePos[2][1]=Py;imagePos[3][1]=Hy;
		
		weaponTag[0][0]="Staff";
		weaponTag[1][0]="Staff" ;
		weaponTag[2][0]="Grimoire";  
		

		weapon[0][0]=staff;
		weapon[1][0]=staff ; 
		weapon[2][0]=grimoire;
	}
}
