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
	private ArrayList<Position> ranking = new ArrayList<Position>(10);
	public Ranking() throws IOException
	{
		readRanking();
	}

	public ArrayList<Position> getRaking() {
		return this.ranking;
	}

	public boolean addPosition(Position p) {
		if (ranking.isEmpty()) {
			ranking.add(0,p);
			return true;
		}
		else
		{
					
		}	
		
		return false;
		
	}

	private void readRanking() throws IOException {

		BufferedReader file = new BufferedReader(new FileReader(
				"data/scoreboard.txt"));
		String buffer = file.readLine();
		int i=0;
		while (buffer != null) {
			String[] string = buffer.split("\\*");
			Position pos = new Position(string[0],
					Integer.parseInt(string[string.length - 1]));
			this.getRaking().add(i,pos);
			buffer = file.readLine();
			i++;
		}

		file.close();
	}

	public void writeRanking() throws IOException {
		BufferedWriter file = new BufferedWriter(new FileWriter(
				"data/scoreboard.txt", false));

		for (int i = 0; i < ranking.size(); i++) {
			file.write(ranking.get(i).getName() + "*"
					+ ranking.get(i).getPoints() + "\n");
		}

		file.flush();
		file.close();
	}
	
}
