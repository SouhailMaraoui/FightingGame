package Game.Rooms;

import Classes.Classe;
import Classes.Warrior;

import javax.swing.JOptionPane; 

import java.util.ArrayList;

import Classes.Athlete;
import Classes.Mage;
import Game.GameManager;
import Game.GameObject;
import Game.Scripts;
import GameEngine.GameEngine;
import GameEngine.Renderer;
import GameEngine.GFX.Image;

public class A_ClassSelect extends GameObject
{	
	private static Classe p1,p2;
	private Image player,warrior, athlete, mage;
	private Image PvP,PvA,AvA;
	private Image image,wallpaper,background,Pointer1Img;
	private Image newP,loadP;
	private Image namePointer,setName;
	private Image experience;
	public static Classe classe;
	
	String name=null;
	
	private static String Mode="";
	String[] names;
	char[] classeTag;
	int[] force,dexterity,intelligence,concentration,exp;
	
	private int wx=100,wy=50;
	private int ax=400,ay=50;
	private int mx=700,my=50;
	private int Px=0, Py=275;
	private int P3x,P4x,NPy;
	private int phase=0;
		
	private boolean Pointer1=false,Pointer2=false,Pointer3=false,Pointer4=false;
	private boolean NamePointer=false;
	private boolean drawList=false;
	private boolean NameSet=false;
	public static boolean canNext=false;

	public A_ClassSelect()
	{
		GameManager.End=false;

		p1=new Classe();
		p2=new Classe();
		Mode="";
		
		experience=new Image("/UI/Stats/Experience.png");
		wallpaper=new Image("/Wallpaper.png");
		background=new Image("/Background.png");

		Pointer1Img=new Image("/UI/Select.png");
		namePointer=new Image("/UI/Pointer1.png");
		
		newP=new Image("/UI/New.png");
		loadP=new Image("/UI/Load.png");
		setName=new Image("/UI/SetName.png");
			
		player=new Image("/UI/Player.png");
		warrior=new Image("/Sprites/Warrior/Warrior.png");
		athlete=new Image("/Sprites/Athlete/Athlete.png");
		mage=new Image("/Sprites/Mage/Mage.png");
		
		PvP=new Image("/UI/PvP.png");
		PvA=new Image("/UI/PvA.png");
		AvA=new Image("/UI/AvA.png");
	}
	
	public void update(GameEngine ge, float dt)
	{	
		if (phase==2)
		{
			if(p2.isNew())
			{
				p2=setClasseNew(p2);
				if(Scripts.isClicked(setName, 400, 0) && !NameSet)
				{
			        name= JOptionPane.showInputDialog("Enter player 2 Name");
			        name=name.toUpperCase();
			        NameSet=true;
				}

			}
			else
			{
				drawList=true;
				p2=LoadClasse(p2);
			}
			if (GameManager.EB && ((Pointer2==true && name!=null) || NamePointer))
			{
				if(name!=null)p2.setName(name);
				NamePointer=false;
				Pointer2=false;
				canNext=true;
				phase=-1;
			}
		}
		
		if (phase==1)
		{
			if(p1.isNew())
			{
				p1=setClasseNew(p1);
				if(Scripts.isClicked(setName, 400, 0) && !NameSet)
				{
			        name= JOptionPane.showInputDialog("Enter player 1 Name");
			        name=name.toUpperCase();
			        NameSet=true;
				}
			}
			else
			{
				drawList=true;
				p1=LoadClasse(p1);
			}
			if (GameManager.EB && ((Pointer2==true && name!=null) || NamePointer))
			{
				if(name!=null)p1.setName(name);
				NameSet=false;
				NamePointer=false;
				Pointer2=false;
				phase=2;
			}
		}
		
		if(phase==0)
		{
			Mode=setMode();
			if(Scripts.isClicked(newP, 350, 290))
			{
				p1.setNew(true);
				Pointer3=true;
				P3x=350;
			}
			if(Scripts.isClicked(loadP,600, 290))
			{
				p1.setNew(false);
				Pointer3=true;
				P3x=600;
			}
			if(Scripts.isClicked(newP, 350, 360))
			{
				p2.setNew(true);
				Pointer4=true;
				P4x=350;
			}
			
			if(Scripts.isClicked(loadP, 600, 360))
			{
				p2.setNew(false);
				Pointer4=true;
				P4x=600;
			}
			if (GameManager.EB && Pointer1 && Pointer3 && Pointer4) 
			{
				Pointer1=false;
				Pointer3=false;
				Pointer4=false;
				phase=1;
			}
			
		}
	}

	public void render(GameEngine ge, Renderer r)
	{		
		r.drawImage(background, 2000, 0);
		r.drawImage(wallpaper,0,0);

		if(phase==0)
		{
			r.drawImage(PvP,wx,100);
			r.drawImage(PvA,ax,100);
			r.drawImage(AvA,mx,100);
			r.drawImage(player,150,300);r.drawNumber(1,250, 300);
			r.drawImage(player,150,360);r.drawNumber(2,250, 360);
			r.drawImage(newP, 350, 290);r.drawImage(loadP, 600, 290);
			r.drawImage(newP, 350, 360);r.drawImage(loadP, 600, 360);
		}
		else
		{
			r.drawImage(player, 0, 0);
			r.drawNumber(phase, 100, 0);
			if(phase==1 && p1.isNew())
			{
				r.drawImage(warrior,wx,wy);
				r.drawImage(athlete,ax,ay);
				r.drawImage(mage,mx,my);
				if(!NameSet)r.drawImage(setName, 400, 0);
				else r.drawString(name, 400,17);
				p1=setExp(p1, r);
			}
			if(phase==2 && p2.isNew())
			{
				drawList=false;
				r.drawImage(warrior,wx,wy);
				r.drawImage(athlete,ax,ay);
				r.drawImage(mage,mx,my);
				p2=setExp(p2, r);
				if(!NameSet)r.drawImage(setName, 400, 0);
				else r.drawString(name, 400, 17);
			}
			
			if(drawList)
			{
				r.drawImage(experience, 400, 20);
				int y=100;
				for(int i=0;i<names.length;i++)
				{
					r.drawImage(namePointer, 25, y);
					r.drawString(names[i], 100, y+5);
					r.drawNumber(exp[i], 450, y+5);
					y+=50;
				}
			}
			if(NamePointer)
			{
				r.drawImage(new Image("/UI/Pointer2.png"), 25, NPy);
			}
		}
		
		if(Pointer1)
		{
			r.drawImage(Pointer1Img, Px, 100);
		}
		if(Pointer2)
		{
			r.drawImage(image, Px, Py);
		}
		if(Pointer3)
		{
			r.drawImage(Pointer1Img, P3x, 290);
		}
		if(Pointer4)
		{
			r.drawImage(Pointer1Img, P4x, 360);
		}
	}

	private Classe LoadClasse(Classe player)
	{
		ArrayList<String> players=Scripts.FileRead("Players.txt");
		int k=0;
		for(String line:players)
		{
			if(line!="\n" && line.charAt(line.length()-1) == ';')
			{
				k+=1;
			}
		}
		names=new String[k];
		classeTag=new char[k];
		force			=new int[k];
		dexterity		=new int[k];
		intelligence	=new int[k];
		concentration	=new int[k];
		exp				=new int[k];
		
		for(int n=0;n<names.length;n++)
		{
			names[n]="";
		}
		int i=0;
		int stage=0;
		for(String line:players)
		{
			if(line!="\n" && line.charAt(line.length()-1) == ';')
			{
				for(char c:line.toCharArray())
				{
					if(c==':')
					{
						stage+=1;
					}
					else if(c==';')
					{
						break;
					}
					else
					{
						switch(stage)
						{
						case 0:names[i]		+=Character.toString(c); break;
						case 1:classeTag[i]	 =c; break;
						case 2:force[i]		   = 10*force[i]+c-'0';			break;
						case 3:dexterity[i]	   = 10*dexterity[i]+c-'0';		break;
						case 4:intelligence[i] = 10*intelligence[i]+c-'0';	break;
						case 5:concentration[i]= 10*concentration[i]+c-'0';	break;
						case 6:exp[i]		   = 10*exp[i]+c-'0';			break;
						}
					}
				}
			}
			stage=0;
			i++;
		}
		drawList=true;
		int y=100;
		for(int j=0;j<names.length;j++)
		{
			if(Scripts.isClicked(namePointer,25, y))
			{
				NamePointer=true;
				NPy=y;
				if(classeTag[j]=='W')
				{
					player =new Warrior();
					player.setStat(force[j], dexterity[j], intelligence[j], concentration[j]);
					player.setInitVitality(200+exp[j]-force[j]- dexterity[j]- intelligence[j]- concentration[j]);
					player.setExp(exp[j]);
					player.setName(names[j]);
					player.setNew(false);
				}
				if(classeTag[j]=='A')
				{
					player =new Athlete();
					player.setStat(force[j], dexterity[j], intelligence[j], concentration[j]);
					player.setInitVitality(200+exp[j]-force[j]- dexterity[j]- intelligence[j]- concentration[j]);
					player.setExp(exp[j]);
					player.setName(names[j]);
					player.setNew(false);
				}
				if(classeTag[j]=='M')
				{
					player =new Mage();
					player.setStat(force[j], dexterity[j], intelligence[j], concentration[j]);
					player.setInitVitality(200+exp[j]-force[j]- dexterity[j]- intelligence[j]- concentration[j]);
					player.setExp(exp[j]);
					player.setName(names[j]);
					player.setNew(false);
				}
			}
			y+=50;
		}
		
		return player;
	}
	
	private Classe setClasseNew(Classe player)
	{		
		if(Scripts.isClicked(warrior,wx,wy))
		{
			player=null;
			player=new Warrior();
			
			image= new Image("/UI/Warrior.png");
			Pointer2=true;
			Px=wx;
		}
		
		if(Scripts.isClicked(athlete,ax,ay))
		{
			player=null;
			player=new Athlete();
			
			image= new Image("/UI/Athlete.png");
			Pointer2=true;
			Px=ax;	
		}

		if(Scripts.isClicked(mage,mx,my))
		{
			player=null;
			player=new Mage();
			
			image= new Image("/UI/Mage.png");
			Pointer2=true;
			Px=mx;
		}
		return player;
	}
	
	private String setMode()
	{
		if(Scripts.isClicked(PvP, wx, 100))
		{
			Mode="PvP";
			Pointer1=true;
			Px=wx;	
		}
		if(Scripts.isClicked(PvA, ax, 100))
		{
			Mode="PvA";
			Pointer1=true;
			Px=ax;	
		}
		if(Scripts.isClicked(AvA, mx, 100))
		{
			Mode="AvA";
			Pointer1=true;
			Px=mx;
		}
		return Mode;
	}
	
	private Classe setExp(Classe player, Renderer r)
	{
		r.drawImage(experience, 300, 400);
		player.setExp(r.setStat(player.getExp(), 550, 410, 1, 4));
		return player;
	}
	
	public static Classe getP1()
	{
		return p1;
	}

	public static Classe getP2()
	{
		return p2;
	}
	
	public static String getMode()
	{
		return Mode;
	}
}