package gui;


import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JPanel;



public class MenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private boolean resumable = false;
	GameFrame frame;
	
	OurButton singleplayerButton = new OurButton("SINGLE PLAYER");
	OurButton levelEditorButton = new OurButton("LEVEL EDITOR");
	OurButton settingsButton = new OurButton("SETTINGS");
	OurButton creditsButton = new OurButton("CREDITS");
	OurButton resumeButton = new OurButton("RESUME");
	OurButton multiplayerButton = new OurButton("MULTIPLAYER");
	OurButton exitButton = new OurButton("EXIT");
	
	
	
	public MenuPanel(GameFrame frameSup){
		frame=frameSup;
		
		

		setLayout(null);

		singleplayerButton.setOnClickBehaviour(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				GameFrame.switchTo(frame.getSingleplayerPanel());
			}
		});
		
		levelEditorButton.setOnClickBehaviour(new MouseAdapter() {
			public void mouseClicked(MouseEvent e ){
				GameFrame.switchTo(frame.getLevelEditorPanel());
			}
		});
		
		
		
		settingsButton.setOnClickBehaviour(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				GameFrame.switchTo(frame.getSettingsPanel());
			}
		});

		
		creditsButton.setOnClickBehaviour(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				GameFrame.switchTo(frame.getCreditsPanel());
			}
		});

		
		exitButton.setOnClickBehaviour(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				frame.setVisible(false);
				System.exit(0);
				frame.dispose();
			}
		});

		
		resumeButton.setOnClickBehaviour(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				try {
					GameFrame.switchTo(frame.getGamePanel());
				} catch (IOException | FontFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		multiplayerButton.setOnClickBehaviour(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				GameFrame.switchTo(frame.getMultiplayerPanel());
			}
		});
		


		add(singleplayerButton);
		add(multiplayerButton);
		add(resumeButton);
		add(levelEditorButton);
		add(settingsButton);
		add(creditsButton);
		add(exitButton);
		


		resumeButton.setBounds(100,120,OurButton.WIDTH,OurButton.HEIGHT);
		singleplayerButton.setBounds(100,200,OurButton.WIDTH,OurButton.HEIGHT);
		multiplayerButton.setBounds(100,360,OurButton.WIDTH,OurButton.HEIGHT);
		levelEditorButton.setBounds(100,440,OurButton.WIDTH,OurButton.HEIGHT);
		settingsButton.setBounds(100,520,OurButton.WIDTH,OurButton.HEIGHT);
		creditsButton.setBounds(100,600,OurButton.WIDTH,OurButton.HEIGHT);
		exitButton.setBounds(100,680,OurButton.WIDTH,OurButton.HEIGHT);

	}


	public void paintComponent(Graphics g){

		g.drawImage(ImageProv.getIstance().getBackground(), 0, 0, this);
		if(!isResumable())
		{
			resumeButton.setVisible(false);
		}else{
			resumeButton.setVisible(true);
		}

	}


	public boolean isResumable() {
		return resumable;
	}


	public void setResumable(boolean resumable) {
		this.resumable = resumable;
	}
}