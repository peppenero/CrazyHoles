package gui;

import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import Objects.Ranking;
import Objects.Record;


public class ScoreboardPanel extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Ranking ranking = new Ranking();
	private static JTextArea classifica = new JTextArea();
	
	public ScoreboardPanel(GameFrame frame){
		setLayout(null);
		
		UIManager.put("ScrollBar.width", new Integer(0));
		JScrollPane scroll = new JScrollPane(classifica);
		classifica.setFont(OurFont.getInstance().deriveFont(Font.TRUETYPE_FONT, 20));
		scroll.setSize(400, 500);
		
		
		
		
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		scroll.setBorder(null);
		scroll.setViewportBorder(null);
		
		classifica.setBorder(null);
		classifica.setOpaque(false);
		//classifica.setBackground(new Color(0, 0, 0, 0));
		
		scroll.setLocation(75, 250);
		add(scroll);
	}
	
	public static void readRanking(){
		try {
			ranking.readRanking();
			ArrayList<Record> classi = ranking.getClassifica();
			for(int i=0;i<classi.size();i++){
				classifica.setText(classifica.getText().concat(classi.get(i).toString()));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(ImageProv.getIstance().getBackground(), 0, 0,this);
	}
	
}
