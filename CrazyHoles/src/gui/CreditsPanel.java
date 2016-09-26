package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class CreditsPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GameFrame frame;
	
	private Image background;
	private OurButton backToMenuButton = new OurButton("BACK TO MENU");
	
	public CreditsPanel(GameFrame f){
		frame = f;
		setLayout(null);
		background = Toolkit.getDefaultToolkit().createImage("images/Credits.jpg");
		
		
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
