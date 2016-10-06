package gui;

import java.awt.Color;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Objects.Client;

public class OnlinePanel extends JPanel
{
	private Image background;
	private OurButton newMatch = new OurButton("NEW MATCH");
	private OurButton joinMatch = new OurButton("JOIN MATCH");
	private JDialog ipDialog;
	private JTextArea iptext;
	private JButton okay;
	private JLabel title;
	private boolean setted =false;
	
	public OnlinePanel()
	{
		setLayout(null);
		background = Toolkit.getDefaultToolkit().getImage("images/Background.jpg");
		okay = new JButton("ENTER");
		title = new JLabel("INSERIRE IP HOST");
		ipDialog = new JDialog();
		iptext = new JTextArea();
		title.setForeground(Color.BLACK);
		ipDialog.setModal(true);
		ipDialog.setUndecorated(true);
		ipDialog.setSize(150,80);
		ipDialog.setLocation(200,200);
		ipDialog.setBackground(new Color(255,255,255,0));	
		ipDialog.setLayout(new BoxLayout(ipDialog.getContentPane(),BoxLayout.Y_AXIS));
		title.setAlignmentX(CENTER_ALIGNMENT);
		title.setAlignmentY(BOTTOM_ALIGNMENT);
		iptext.setAlignmentX(CENTER_ALIGNMENT);
		iptext.setAlignmentY(BOTTOM_ALIGNMENT);
		ipDialog.add(title);
		ipDialog.add(iptext);
		ipDialog.setVisible(false);
		okay.setAlignmentX(CENTER_ALIGNMENT);
		okay.setAlignmentY(BOTTOM_ALIGNMENT);
		okay.setContentAreaFilled(false);
		ipDialog.add(okay);
		newMatch.setBounds(100, 400, OurButton.WIDTH, OurButton.HEIGHT);
		joinMatch.setBounds(100, 480, OurButton.WIDTH, OurButton.HEIGHT);
	
		newMatch.setOnClickBehaviour(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				
					GameFrame.switchTo(new ListOfLevelPanel());
				
			}
			
		});
		joinMatch.setOnClickBehaviour(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				ipDialog.setVisible(true);
				if(setted)
				{
					try {
						Client client = new Client(iptext.getText());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		okay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ipDialog.setVisible(false);
				setted=true;
			}
		});
		add(newMatch);
		add(joinMatch);
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		g.drawImage(background, 0,0,this);
	}
}
