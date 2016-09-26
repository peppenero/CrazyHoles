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

public class SettingsPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	GameFrame frame;
	
	Image background;
	OurButton backToMenuButton = new OurButton("BACK TO MENU");
	
	public SettingsPanel(GameFrame f){
		frame = f;
		setLayout(null);
		background = Toolkit.getDefaultToolkit().createImage("images/Settings.jpg");
		
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