package gui;


import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;



public class MenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private boolean resumable = false;
	GameFrame frame;

	Image background;

	Icon newGame = new ImageIcon("images/newgame.png");
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
	MenuButton resumeButton = new MenuButton(resume,resumeSelected);
	Icon resumeDeselected = new ImageIcon("images/resume_deselected.png");
	public MenuPanel(GameFrame frameSup){
		frame=frameSup;
		
		

		setLayout(null);

		background = Toolkit.getDefaultToolkit().createImage("images/Background.jpg");


		newGameButton.addActionListener(new ActionListener() {

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
		});

		levelEditorButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GameFrame.switchTo(frame.getLevelEditorPanel());
			}
		});

		settingsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GameFrame.switchTo(frame.getSettingsPanel());
			}
		});

		creditsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GameFrame.switchTo(frame.getCreditsPanel());
			}
		});

		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.setVisible(false);
				System.exit(0);
				frame.dispose();
			}
		});

		resumeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					GameFrame.switchTo(frame.getGamePanel());
				} catch (IOException | FontFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});


		add(newGameButton);
		add(resumeButton);
		add(levelEditorButton);
		add(settingsButton);
		add(creditsButton);
		add(exitButton);


		resumeButton.setBounds(100,260,newGame.getIconWidth(),newGame.getIconHeight());
		newGameButton.setBounds(100,340,newGame.getIconWidth(),newGame.getIconHeight());
		levelEditorButton.setBounds(100,420,newGame.getIconWidth(),newGame.getIconHeight());
		settingsButton.setBounds(100,500,newGame.getIconWidth(),newGame.getIconHeight());
		creditsButton.setBounds(100,580,newGame.getIconWidth(),newGame.getIconHeight());
		exitButton.setBounds(100,660,newGame.getIconWidth(),newGame.getIconHeight());

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