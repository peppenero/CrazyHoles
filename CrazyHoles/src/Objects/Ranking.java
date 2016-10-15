package Objects;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//non mi piace che viene istanziata ogni volta
public class Ranking {
	private ArrayList<Record> ranking = new ArrayList<Record>();

	public Ranking() throws IOException {
		readRanking();
	}

	public ArrayList<Record> getRaking() {
		return this.ranking;
	}

	public void addRecord(Record r) {
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("data/scoreboard.txt", true));
			bufferedWriter.write(r.toString());
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void readRanking() throws IOException {
		BufferedReader file = new BufferedReader(new FileReader(
				"data/scoreboard.txt"));
		String buffer = file.readLine();
		int i = 0;
		while (buffer != null) {
			String[] string = buffer.split("\\*");
			Record pos = new Record(string[0],
					Integer.parseInt(string[1]));
			this.getRaking().add(i, pos);
			buffer = file.readLine();
			i++;
		}
		
		file.close();
	}

//	public void writeRanking() throws IOException {
//		BufferedWriter file = new BufferedWriter(new FileWriter(
//				"data/scoreboard.txt", false));
//
//		for (int i = 0; i < ranking.size(); i++) {
//			file.write(ranking.get(i).getName() + "*"
//					+ ranking.get(i).getScore() + "\n");
//		}
//
//		file.flush();
//		file.close();
//	}

}
