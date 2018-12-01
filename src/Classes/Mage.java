package Classes;

import GameEngine.GFX.Image;

public class Mage extends Classe
{
	private static Image I,A,P,H;
	
	private static int Ix,Iy;
	private static int Ax,Ay;
	private static int Px,Py;
	private static int Hx,Hy;
	
	public Mage()
	{		
		Weapon staff=new Weapon(190,70,80); staff.setTag("Staff"); 	staff.setImage("/UI/Info/Staff.png");
		Weapon grimoire=new Weapon(200,65,0);grimoire.setTag("Grimoire"); 	grimoire.setImage("/UI/Info/Grimoire.png");
		
		Ix=2100;Iy=100;
		Ax=2100;Ay=100;
		Px=2100;Py=100;
		Hx=2100;Hy=100;
		
		I	=new Image("/Sprites/Mage/Arena/Idle.png");
		A	=new Image("/Sprites/Mage/Arena/Attack_Staff.png");
		P	=new Image("/Sprites/Mage/Arena/Parry.png");
		H	=new Image("/Sprites/Mage/Arena/Heal.png");
		
		splashArt	=new Image("/SplashArt/Mage.png");		
		logo		=new Image("/Sprites/Mage/Logo.png");

		image[0]=I;image[1]=A;image[2]=P;image[3]=H;
		
		imagePos[0][0]=Ix;imagePos[1][0]=Ax;imagePos[2][0]=Px;imagePos[3][0]=Hx;
		imagePos[0][1]=Iy;imagePos[1][1]=Ay;imagePos[2][1]=Py;imagePos[3][1]=Hy;		

		weapon[0][0]=staff;		weapon[0][1]=null;
		weapon[1][0]=staff ; 	weapon[1][1]=null;
		weapon[2][0]=grimoire;	weapon[2][1]=null;
	}
}
