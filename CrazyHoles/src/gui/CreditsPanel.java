package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class CreditsPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	GameFrame frame;
	
	Image background;
	Icon backToMenu = new ImageIcon("images/backtomenu.png");
	Icon backToMenuSelected = new ImageIcon("images/backtomenu_selected.png");
	MenuButton backToMenuButton = new MenuButton(backToMenu,backToMenuSelected);
	
	public CreditsPanel(GameFrame f){
		frame = f;
		setLayout(null);
		background = Toolkit.getDefaultToolkit().createImage("images/Credits.jpg");
		
		backToMenuButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GameFrame.switchTo(frame.getMenuPanel());
			}
		});
		
		add(backToMenuButton);
		
		backToMenuButton.setBounds(100,700,backToMenu.getIconWidth(),backToMenu.getIconHeight());
		
	}
	
	
	
	public void paintComponent(Graphics g){
		g.drawImage(background, 0, 0, this);
	}
}
