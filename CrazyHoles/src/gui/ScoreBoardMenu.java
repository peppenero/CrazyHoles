package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;

import Objects.GameManager;
import Objects.Ranking;

public class ScoreBoardMenu extends JDialog 
{
	private static final long serialVersionUID = 1L;
	private Ranking ranking;
	private JLabel[] label;
	private BoxLayout layout;
	private Font font;
	private OurButton back;
	private GameManager manager;
	boolean addable=true;


	public ScoreBoardMenu(final LeftGamePanel panel,GameManager manager) throws IOException, FontFormatException
	{	
		this.manager=manager;
		this.setModal(true);
		layout = new BoxLayout(getContentPane(),BoxLayout.Y_AXIS);
		this.setUndecorated(true);
		this.setBackground(new Color(255,255,255,0));
		this.setSize(800,300);
		this.setLocation(200, 300);
		this.setLayout(layout);
		ranking=manager.getRanking();
		String filename = "data/EASPORTS15.ttf";
		font = Font.createFont(Font.TRUETYPE_FONT, new File(filename));
		font=font.deriveFont(Font.TRUETYPE_FONT,30);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(font);
		back = new OurButton("BACK");
		
		back.setOnClickBehaviour(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				panel.setsBoardActive(false);
				panel.setPause(false);
				dispose();
			}
		});
		
		loadLabel();
	}

	private void loadLabel() throws IOException
	{
		
		if(ranking.getRaking().size()<=3)
		{
			label = new JLabel[ranking.getRaking().size()];
		}
		else
		{
			label = new JLabel[3];
		}
		for(int i =label.length-1;i>=0;i--)
		{
			
				label[i] = new JLabel(ranking.getRaking().get(i).getName()+" "+ranking.getRaking().get(i).getPoints());
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
