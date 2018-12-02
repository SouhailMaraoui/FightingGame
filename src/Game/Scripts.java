package Game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import Classes.Fighter;
import GameEngine.Image;

public class Scripts
{
	public static boolean isClicked(Image image,int x2, int y2)
	{
		return GameManager.MX>x2  && GameManager.MX<(x2+image.getW()) && GameManager.MY>y2 && GameManager.MY<(y2+image.getH()) && GameManager.MB;
	}
	
	public static boolean isHovered(Image image,int x2, int y2)
	{
		return GameManager.MX>x2  && GameManager.MX<(x2+image.getW()) && GameManager.MY>y2 && GameManager.MY<(y2+image.getH());
	}
	
	public static boolean isTrueInList(boolean[][] T)
	{
		for(int i=0;i<T.length;i++)
		{
			for(int j=0;j<T[0].length;j++)
			{
				if(T[i][j]==true)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public static int ifHit(int hit,int chance)
	{
		int h=0;
		Random r= new Random(); 
		int i=r.nextInt(100);
		if(i<chance)
		{
			h=hit;
		}
		return h;
	}
	
	public static int max(int a,int b)
	{
		if (a>b)
			return a;
		return b;
	}
	
	public static int min(int a,int b)
	{
		if (a>b)
			return b;
		return a;
	}
	
	public static void restart()
	{
		GameManager.objects.clear();
		GameManager.objects.add(new Game.Rooms.A_ClassSelect());
	}	
	
	public static int imgIndex(Fighter p,int i,int j)
	{
		int k=0;
		if(p.getTag()=="Warrior")
		{
			switch(i)
			{
			case 0:k=1; break;
			case 1:k=2+j; break;
			case 2:k=4; break;
			}
		}
		if(p.getTag()=="Athlete")
		{
			switch(i)
			{
			case 0:k=1+j; break;
			case 1:k=3; break;
			case 2:k=4; break;
			}
		}if(p.getTag()=="Mage")
		{
			switch(i)
			{
			case 0:k=1; break;
			case 1:k=2; break;
			case 2:k=3; break;
			}
		}
		return k;
	}
	
	public static void FileWrite(String path,String Line,boolean keepOld)
	{
		try	
		{
			FileWriter file=new FileWriter(path,keepOld);
			BufferedWriter bufferedFile=new BufferedWriter(file);
			bufferedFile.write(Line);
			bufferedFile.newLine();
			
			bufferedFile.close();
		}
		catch (IOException e)	{e.printStackTrace();}
	}
	
	public static void FileClear(String path)
	{
		try	
		{
			FileWriter file=new FileWriter(path);
			BufferedWriter bufferedFile=new BufferedWriter(file);
			
			bufferedFile.close();
		}
		catch (IOException e)	{e.printStackTrace();}
	}
	
	public static ArrayList<String> FileRead(String path)
	{
		String line;
		ArrayList<String> Lines=new ArrayList<String>();
		FileReader fileReader;
		try
		{
			fileReader = new FileReader(path);
			BufferedReader bufferedReader=new BufferedReader(fileReader);

			while((line= bufferedReader.readLine()) != null) 
			{
				Lines.add(line);
			}
			bufferedReader.close(); 
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return Lines;         
	}
	
	public static String getName(int line)
	{
		ArrayList<String> Lines=FileRead("Players.txt");
		String name="";
		for(char c:Lines.get(line).toCharArray())
		{
			if(c!=':')
			{
				name+=Character.toString(c);
			}
			else break;
		}
		return name;
	}
}

