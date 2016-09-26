package gui;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CreditsPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GameFrame frame;
	
	private Image background;
	private OurButton backToMenuButton = new OurButton("BACK TO MENU");
	private JLabel gMuratore = new JLabel("Giuseppe Muratore");
	private JLabel gSpanò = new JLabel("Giuseppe Spanò");
	
	public CreditsPanel(GameFrame f){
		frame = f;
		setLayout(null);
		background = Toolkit.getDefaultToolkit().createImage("images/Credits.jpg");
		Font font = null;
		try {
			 font= Font.createFont(Font.TRUETYPE_FONT, new File("data/EASPORTS15.ttf"));
		} catch (FontFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		font = font.deriveFont(Font.TRUETYPE_FONT, 50);
		
		gMuratore.setFont(font);
		gSpanò.setFont(font);
		
		gMuratore.setBounds(100, 250, 600, 100);
		gSpanò.setBounds(100, 400, 600, 100);
		add(gMuratore);
		add(gSpanò);
		
		
		backToMenuButton.setOnClickBehaviour(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				GameFrame.switchTo(frame.getMenuPanel());
			}
		});
		
		backToMenuButton.setBounds(100,700,OurButton.WIDTH,OurButton.HEIGHT);
		add(backToMenuButton);
		
		
		
	}
	
	
	
	public void paintComponent(Graphics g){
		g.drawImage(background, 0, 0, this);
	}
}
