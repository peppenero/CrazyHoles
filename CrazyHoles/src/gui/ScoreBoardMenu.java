package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class ScoreBoardMenu extends JDialog 
{
	private static final long serialVersionUID = 1L;
	private List<String> palore = new ArrayList<String>();
	private JLabel[] label;
	private BoxLayout layout;
	private Font font;
	private MenuButton back;
	private Icon escIcon = new ImageIcon("images/esc.png");


	public ScoreBoardMenu() throws IOException, FontFormatException
	{

		   this.setModal(true);
			layout = new BoxLayout(getContentPane(),BoxLayout.Y_AXIS);
			this.setUndecorated(true);
			this.setBackground(new Color(0,0,0,0));
		   this.setSize(800,300);
		   this.setLocation(200, 300);
		   this.setLayout(layout);
		   String filename = "images/ARCADE_N.TTF";
			font = Font.createFont(Font.TRUETYPE_FONT, new File(filename));
			font=font.deriveFont(Font.TRUETYPE_FONT,30);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);
			back = new MenuButton(escIcon, escIcon);
			back.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					dispose();
				}
			});
			loadLabel();


	}

	private void loadLabel() throws IOException
	{
		BufferedReader file = new BufferedReader(new FileReader("images/scoreboard.txt"));

		String buffer = file.readLine();

		while (buffer != null)
		{
			palore.add(buffer);
			buffer=file.readLine();
		}
		file.close();

		label = new JLabel[palore.size()];

		for(int i =0;i<label.length;i++)
		{
			label[i] = new JLabel(palore.get(i));
			label[i].setFont(font);
			label[i].setForeground(Color.black);
			label[i].setAlignmentX(CENTER_ALIGNMENT);
			label[i].setAlignmentY(TOP_ALIGNMENT);

		}
		for(int i =0;i<label.length;i++)
		{
			add(label[i]);
		}
		back.setAlignmentX(CENTER_ALIGNMENT);
		back.setAlignmentY(BOTTOM_ALIGNMENT);
		this.add(back);
	}



}
