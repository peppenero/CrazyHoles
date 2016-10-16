package Objects;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Ranking {
	private ArrayList<Record> classifica = new ArrayList<Record>();
	private Scanner scanner;
	
	public Ranking(){
		try {
			readRanking();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ordina() throws IOException{
		scanner = new Scanner(new File("data/scoreboard.txt"));
        List<Record> records = new ArrayList<Record>();
        while(scanner.hasNext()) {
        	String s = scanner.nextLine();
        	String[] str = s.split("\\*");
            records.add(new Record(str[0],Integer.valueOf(str[1])));
        }
        Collections.sort(records);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("data/scoreboard.txt"));
        for(Record line : records) {
            bufferedWriter.write(line.toString());
        }
        bufferedWriter.close();
	}

	public void addRecord(Record r) {
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(
					"data/scoreboard.txt", true));
			bufferedWriter.write(r.toString());
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void readRanking() throws IOException{
		BufferedReader file = new BufferedReader(new FileReader(
				"data/scoreboard.txt"));
		String buffer = file.readLine();
		int i = 0;
		while (buffer != null) {
			String[] string = buffer.split("\\*");
			Record pos = new Record(string[0],
					Integer.parseInt(string[1]));
			classifica.add(i, pos);
			buffer = file.readLine();
			i++;
		}
		
		file.close();
	}
	
	public ArrayList<Record> getClassifica(){
		return classifica;
	}
	
}
