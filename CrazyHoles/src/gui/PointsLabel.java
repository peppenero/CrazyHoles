package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Objects.GameManager;
import Objects.Ranking;

public class PointsLabel extends JDialog
{
	private JTextArea text;
	private JButton okay;
	private BoxLayout layout;
	private GameManager GameManager;
	private boolean setted=false;
	private LeftGamePanel leftP;
	
	
	public PointsLabel( GameManager manager,LeftGamePanel left)
	{	
		leftP=left;
		this.GameManager=manager;
		setUndecorated(true);
		setSize(250,100);
		setLocation(200,200);
		setBackground(new Color(255,255,255,0));
		layout= new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
		setLayout(layout);
		text=new JTextArea();
		text.setVisible(true);
		text.setForeground(Color.black);
		okay = new JButton("okay");

		text.addKeyListener(new KeyAdapter() {
		
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub		 
			}
		});
		
		okay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GameManager.setPlayer(text.getText());
				setSetted(true);
				dispose();
				leftP.setBackFlag(false);
				leftP.repaint();
			}
		});
		
		
		text.setAlignmentX(CENTER_ALIGNMENT);
		text.setAlignmentY(CENTER_ALIGNMENT);
		add(text);
		okay.setAlignmentX(CENTER_ALIGNMENT);
		okay.setAlignmentY(CENTER_ALIGNMENT);
		add(okay);	
	}


	public boolean isSetted() {
		return setted;
	}


	public void setSetted(boolean setted) {
		this.setted = setted;
	}
}
