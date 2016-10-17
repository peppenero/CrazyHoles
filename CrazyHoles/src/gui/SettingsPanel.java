package gui;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class SettingsPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	GameFrame frame;
	OurButton backToMenuButton = new OurButton("BACK TO MENU");
	
	public SettingsPanel(GameFrame f){
		frame = f;
		setLayout(null);
		
		backToMenuButton.setOnClickBehaviour(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				GameFrame.switchTo(frame.getMenuPanel());
			}
		});
		
		backToMenuButton.setBounds(100,700,OurButton.WIDTH,OurButton.HEIGHT);
		add(backToMenuButton);
		
	}
	
	
	
	public void paintComponent(Graphics g){
		g.drawImage(ImageProv.getIstance().getBackgroundSettings(), 0, 0, this);
	}
}