package Classes;

import GameEngine.GFX.Image;

public class Warrior extends Classe 
{
	public static Image I,A,P1,P2,H;
	
	public static int px,py;
	public static int Ix,Iy;
	public static int Ax,Ay;
	public static int P1x,P1y;
	public static int P2x,P2y;
	public static int Hx,Hy;
	
	//public static Image[] image=new Image[5];
	//public static int[][] imagePos=new int[5][2];
	

	public Warrior()
	{		
		Weapon sword=new Weapon(30000,1000,1000);	sword.setTag("Sword"); 	sword.setImage("/UI/Info/Sword.png");
		Weapon shield=new Weapon(10,10,10);	shield.setTag("Shield");shield.setImage("/UI/Info/Shield.png");
		Weapon banner=new Weapon(40000,10,10);	banner.setTag("Banner");banner.setImage("/UI/Info/Banner.png");
		
		Ix=2100;Iy=100;
		Ax=2500;Ay=100;
		P1x=2100;P1y=100;
		P2x=2100;P2y=100;
		Hx=2090;Hy=3;
		
		I=new Image("/Sprites/Warrior/Arena/Idle.png");	
		A=new Image("/Sprites/Warrior/Arena/Attack.png");
		P1=new Image("/Sprites/Warrior/Arena/Parry.png");
		P2=new Image("/Sprites/Warrior/Arena/Parry.png");
		H=new Image("/Sprites/Warrior/Arena/Heal.png");

		image[0]=I;image[1]=A;image[2]=P1;image[3]=P2;image[4]=H;
		
		imagePos[0][0]=Ix;imagePos[1][0]=Ax;imagePos[2][0]=P1x;imagePos[3][0]=P2x;imagePos[4][0]=Hx;
		imagePos[0][1]=Iy;imagePos[1][1]=Ay;imagePos[2][1]=P2y;imagePos[3][1]=P2y;imagePos[4][1]=Hy; 
		
		weapon[0][0]=sword;
		weapon[1][0]=sword ; weapon[1][1]=shield; 
		weapon[2][0]=banner;
	}
}