package Objects;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class Ranking 
{
	private List<Position> ranking = new ArrayList<>();
	
	public Ranking() throws IOException
	{
		readRanking();
	}
	
	public boolean addPosition(Position p)
	{
		if(ranking.isEmpty())
		{
			ranking.add(p);
			return true;
		}
		else
		{
			for(int i=0;i<ranking.size();i++)
			{
				if(ranking.get(i).getPoints()<p.getPoints())
				{
					ranking.add(i, p);
					return true;
				}
			}
			ranking.add(p);
			return true;
			
		}
	}
	
	private void readRanking() throws IOException
	{
		
		BufferedReader file = new BufferedReader(new FileReader("data/scoreboard.txt"));
		String buffer = file.readLine();
		
		while(buffer!=null)
		{
			String[] string = buffer.split(" ");			
			Position pos = new Position(string[0],Integer.parseInt(string[1]));
			this.addPosition(pos);
			buffer=file.readLine();
		}
		
		file.close();
	}
	public void writeRanking() throws IOException
	{
		BufferedWriter file = new BufferedWriter(new FileWriter("images/scoreboard.txt",false));
		
		for(int i=0;i<ranking.size();i++)
		{
			file.write(ranking.get(i).getName()+" "+ranking.get(i).getPoints()+"\n");
		}
		
		file.flush();
	}
}
