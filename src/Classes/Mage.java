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
		
		Weapon staff=new Weapon(220,75,150); staff.setTag("Staff"); 	staff.setImage("/UI/Info/Staff.png");
		Weapon grimoire=new Weapon(850,200,0);grimoire.setTag("Grimoire"); 	grimoire.setImage("/UI/Info/Grimoire.png");
		
		Ix=2100;Iy=100;
		Ax=2100;Ay=100;
		Px=2100;Py=100;
		Hx=2100;Hy=100;
		
		I=new Image("/Sprites/Mage/Arena/Idle.png");
		A=new Image("/Sprites/Mage/Arena/Attack_Staff.png");
		P=new Image("/Sprites/Mage/Arena/Parry.png");
		H=new Image("/Sprites/Mage/Arena/Heal.png");
		
		logo=new Image("/Sprites/Mage/Logo.png");


		image[0]=I;image[1]=A;image[2]=P;image[3]=H;
		
		imagePos[0][0]=Ix;imagePos[1][0]=Ax;imagePos[2][0]=Px;imagePos[3][0]=Hx;
		imagePos[0][1]=Iy;imagePos[1][1]=Ay;imagePos[2][1]=Py;imagePos[3][1]=Hy;		

		weapon[0][0]=staff;		weapon[0][1]=null;
		weapon[1][0]=staff ; 	weapon[1][1]=null;
		weapon[2][0]=grimoire;	weapon[2][1]=null;
	}
}
