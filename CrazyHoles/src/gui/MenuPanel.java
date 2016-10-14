package gui;


import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;



public class MenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private boolean resumable = false;
	GameFrame frame;

	Image background;

	/*Icon newGame = new ImageIcon("images/newgame.png");
	Icon newGameSelected = new ImageIcon("images/newgame_selected.png");
	MenuButton newGameButton = new MenuButton(newGame,newGameSelected);
	Icon levelEditor = new ImageIcon("images/leveleditor.png");
	Icon levelEditorSelected = new ImageIcon("images/leveleditor_selected.png");
	MenuButton levelEditorButton = new MenuButton(levelEditor,levelEditorSelected);
	Icon settings = new ImageIcon("images/settings.png");
	Icon settingsSelected = new ImageIcon("images/settings_selected.png");
	MenuButton settingsButton = new MenuButton(settings,settingsSelected);
	Icon credits = new ImageIcon("images/credits.png");
	Icon creditsSelected = new ImageIcon("images/credits_selected.png");
	MenuButton creditsButton = new MenuButton(credits,creditsSelected);
	Icon exit = new ImageIcon("images/exit.png");
	Icon exitSelected = new ImageIcon("images/exit_selected.png");
	MenuButton exitButton = new MenuButton(exit,exitSelected);
	Icon resume = new ImageIcon("images/resume.png");
	Icon resumeSelected = new ImageIcon("images/resume_selected.png");
	Icon resumeDeselected = new ImageIcon("images/resume_deselected.png");
	MenuButton resumeButton = new MenuButton(resume,resumeSelected);
	Icon multi = new ImageIcon("images/Multiplayer.png");
	Icon multiSelected = new ImageIcon("images/Multiplayer_Selected.png");
	MenuButton multiplayerButton = new MenuButton(multi, multiSelected);
	*/
	
	OurButton newGameButton = new OurButton("NEW GAME");
	OurButton freePracticeButton = new OurButton("FREE PRACTICE");
	OurButton levelEditorButton = new OurButton("LEVEL EDITOR");
	OurButton settingsButton = new OurButton("SETTINGS");
	OurButton creditsButton = new OurButton("CREDITS");
	OurButton resumeButton = new OurButton("RESUME");
	OurButton multiplayerButton = new OurButton("MULTIPLAYER");
	OurButton exitButton = new OurButton("EXIT");
	
	
	
	public MenuPanel(GameFrame frameSup){
		frame=frameSup;
		
		

		setLayout(null);

		background = Toolkit.getDefaultToolkit().createImage("images/Background.jpg");

		newGameButton.setOnClickBehaviour(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				setResumable(false);
				try {
					GameFrame.switchTo(frame.getGamePanel());
				} catch (IOException | FontFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		/*newGameButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setResumable(false);
				try {
					GameFrame.switchTo(frame.getGamePanel());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FontFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});*/

//		levelEditorButton.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				GameFrame.switchTo(frame.getLevelEditorPanel());
//			}
//		});
		
		levelEditorButton.setOnClickBehaviour(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				GameFrame.switchTo(frame.getLevelEditorPanel());
			}
		});

/*		settingsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GameFrame.switchTo(frame.getSettingsPanel());
			}
		});*/
		
		settingsButton.setOnClickBehaviour(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				GameFrame.switchTo(frame.getSettingsPanel());
			}
		});

//		creditsButton.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				GameFrame.switchTo(frame.getCreditsPanel());
//			}
//		});
		
		creditsButton.setOnClickBehaviour(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				GameFrame.switchTo(frame.getCreditsPanel());
			}
		});

//		exitButton.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				frame.setVisible(false);
//				System.exit(0);
//				frame.dispose();
//			}
//		});
		
		exitButton.setOnClickBehaviour(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				frame.setVisible(false);
				System.exit(0);
				frame.dispose();
			}
		});

//		resumeButton.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO Auto-generated method stub
//				try {
//					GameFrame.switchTo(frame.getGamePanel());
//				} catch (IOException | FontFormatException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		});
		
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
		
//		multiplayerButton.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO Auto-generated method stub
//				GameFrame.switchTo(frame.getMultiplayerPanel());
//			}
//		});
		
		multiplayerButton.setOnClickBehaviour(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				GameFrame.switchTo(frame.getMultiplayerPanel());
			}
		});
		
		freePracticeButton.setOnClickBehaviour(new MouseAdapter() {
		
			@Override
			public void mouseClicked(MouseEvent e) {
				GameFrame.switchTo(new ListOfLevelPanel(frame.getMenuPanel(), "FreePractice"));
			}
		});


		add(newGameButton);
		add(freePracticeButton);
		add(multiplayerButton);
		add(resumeButton);
		add(levelEditorButton);
		add(settingsButton);
		add(creditsButton);
		add(exitButton);
		


		resumeButton.setBounds(100,120,OurButton.WIDTH,OurButton.HEIGHT);
		newGameButton.setBounds(100,200,OurButton.WIDTH,OurButton.HEIGHT);
		freePracticeButton.setBounds(100, 280, OurButton.WIDTH, OurButton.HEIGHT);
		multiplayerButton.setBounds(100,360,OurButton.WIDTH,OurButton.HEIGHT);
		levelEditorButton.setBounds(100,440,OurButton.WIDTH,OurButton.HEIGHT);
		settingsButton.setBounds(100,520,OurButton.WIDTH,OurButton.HEIGHT);
		creditsButton.setBounds(100,600,OurButton.WIDTH,OurButton.HEIGHT);
		exitButton.setBounds(100,680,OurButton.WIDTH,OurButton.HEIGHT);

	}


	public void paintComponent(Graphics g){

		g.drawImage(background, 0, 0, this);
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