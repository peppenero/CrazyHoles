package gui;

import java.awt.Graphics;

import javax.swing.JPanel;

import Objects.Ranking;


public class ScoreBoardPanel extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Ranking ranking = new Ranking();
	ImageProv imageProv;
	
	public ScoreBoardPanel(){
		imageProv = ImageProv.getIstance();
	}
	
	public void paintComponent(Graphics g){
		
	}
	
}
