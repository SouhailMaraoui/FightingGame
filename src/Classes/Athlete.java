package Classes;

import GameEngine.GFX.Image;

public class Athlete extends Classe
{
	public static Image player,I,A1,A2,P,H;
	
	public static int px,py;
	public static int Ix,Iy;
	public static int A1x,A1y;
	public static int A2x,A2y;
	public static int Px,Py;
	public static int Hx,Hy;

	public Athlete()
	{	
		Weapon staff=new Weapon(220,180,40);staff.setTag("Staff"); 	staff.setImage("/UI/Info/Staff.png");
		Weapon dagger=new Weapon(230,165,0);dagger.setTag("Dagger"); 	dagger.setImage("/UI/Info/Dagger.png");
		Weapon potion=new Weapon(220,250,0);potion.setTag("Potion"); 	potion.setImage("/UI/Info/Potion.png");
		
		Ix=2100;Iy=100;
		A1x=2610;A1y=100;
		A2x=2100;A2y=100;
		Px=2100;Py=100;
		Hx=2100;Hy=100;
		
		I=new Image("/Sprites/Athlete/Arena/Idle.png");
		A1=new Image("/Sprites/Athlete/Arena/Attack_Dagger.png");
		A2=new Image("/Sprites/Athlete/Arena/Attack_Staff.png");
		P=new Image("/Sprites/Athlete/Arena/Parry.png");
		H=new Image("/Sprites/Athlete/Arena/Heal.png");
		
		logo=new Image("/Sprites/Athlete/Logo.png");

		
		image[0]=I;image[1]=A1;image[2]=A2;image[3]=P;image[4]=H;
		
		imagePos[0][0]=Ix;imagePos[1][0]=A1x;imagePos[2][0]=A2x;imagePos[3][0]=Px;imagePos[4][0]=Hx;
		imagePos[0][1]=Iy;imagePos[1][1]=A1y;imagePos[2][1]=A2y;imagePos[3][1]=Py;imagePos[4][1]=Hy;

		weapon[0][0]=dagger;weapon[0][1]=staff; 
		weapon[1][0]=staff ;weapon[1][1]=null; 
		weapon[2][0]=potion;weapon[2][1]=null; 
	}
}