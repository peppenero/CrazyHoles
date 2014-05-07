package gui;

import java.awt.TextArea;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JPanel;

import Objects.World;

public class RightGamePanel extends JPanel 
{
	private World world;
	TextField score = new TextField();
	TextField time = new TextField();
	
	public RightGamePanel(World world) {
		// TODO Auto-generated constructor stub
	
		this.world=world;
		this.add(score);
		this.add(time);
	}
}
