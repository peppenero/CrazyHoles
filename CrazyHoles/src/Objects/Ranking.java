package Objects;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//non mi piace che viene istanziata ogni volta
public class Ranking {
	private Position ranking[] = new Position[10];
	private Position nulla = new Position("nulla",0);
	public Ranking() throws IOException {
		readRanking();
		for(int i=0;i<ranking.length;i++)
		{
			if(ranking[i]==null)
			{
				ranking[i]=nulla;
			}
		}
	}

	public Position[] getRaking() {
		return this.ranking;
	}

	public boolean addPosition(Position p) {
		if (ranking.length==0) {
			ranking[ranking.length-1]=p;
			return true;
		}
		else
		{
			for(int i=0;i<ranking.length;i++)
			{
				if(ranking[i]==nulla)
				{
					ranking[i]=p;
				}
				else
				{
					if(p.getPoints()>ranking[i].getPoints())
					{
						sort(i--,ranking[i]);
						ranking[i]=p;
					}
				}
			}
		}		
		return false;
	}

	private void readRanking() throws IOException {

		BufferedReader file = new BufferedReader(new FileReader(
				"data/scoreboard.txt"));
		String buffer = file.readLine();

		while (buffer != null) {
			String[] string = buffer.split("\\*");
			Position pos = new Position(string[0],
					Integer.parseInt(string[string.length - 1]));
			this.addPosition(pos);
			buffer = file.readLine();
		}

		file.close();
	}

	public void writeRanking() throws IOException {
		BufferedWriter file = new BufferedWriter(new FileWriter(
				"data/scoreboard.txt", false));

		for (int i = 0; i < ranking.length; i++) {
			file.write(ranking[i].getName() + "*"
					+ ranking[i].getPoints() + "\n");
		}

		file.flush();
		file.close();
	}
	
	private void sort(int i,Position p)
	{
		if(i<0)
			return;
		sort(i--,ranking[i]);
		ranking[i]=p;
	}
}
