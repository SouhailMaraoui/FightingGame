package Classes;

import GameEngine.Image;

public class Warrior extends Fighter 
{
	private static Image I,A,P1,P2,H;
	
	private static int Ix,Iy;
	private static int Ax,Ay;
	private static int P1x,P1y;
	private static int P2x,P2y;
	private static int Hx,Hy;

	public Warrior()
	{	
		Weapon sword=new Weapon(220,60,100);	sword.setTag("Sword"); 	sword.setImage("/UI/Info/Sword.png");
		Weapon shield=new Weapon(180,0,200);	shield.setTag("Shield");shield.setImage("/UI/Info/Shield.png");
		Weapon banner=new Weapon(175,50,0);	banner.setTag("Banner");banner.setImage("/UI/Info/Banner.png");
		
		Ix=2100;Iy=100;
		Ax=2500;Ay=100;
		P1x=2100;P1y=100;
		P2x=2100;P2y=100;
		Hx=2090;Hy=3;
		
		I	=new Image("/Sprites/Warrior/Arena/Idle.png");	
		A	=new Image("/Sprites/Warrior/Arena/Attack.png");
		P1	=new Image("/Sprites/Warrior/Arena/Parry.png");
		P2	=new Image("/Sprites/Warrior/Arena/ParryShield.png");
		H	=new Image("/Sprites/Warrior/Arena/Heal.png");
		
		splashArt	=new Image("/SplashArt/Warrior.png");
		logo		=new Image("/Sprites/Warrior/Logo.png");
		tag="Warrior";

		image[0]=I;image[1]=A;image[2]=P1;image[3]=P2;image[4]=H;
		imagePos[0][0]=Ix;imagePos[1][0]=Ax;imagePos[2][0]=P1x;imagePos[3][0]=P2x;imagePos[4][0]=Hx;
		imagePos[0][1]=Iy;imagePos[1][1]=Ay;imagePos[2][1]=P1y;imagePos[3][1]=P2y;imagePos[4][1]=Hy; 
		
		weapon[0][0]=sword; weapon[0][1]=null; 
		weapon[1][0]=sword ; weapon[1][1]=shield; 
		weapon[2][0]=banner; weapon[2][1]=null; 
	}
}