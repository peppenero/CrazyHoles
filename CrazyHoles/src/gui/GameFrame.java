package gui;

import java.awt.BorderLayout;
import java.awt.FontFormatException;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Objects.GameManager;
import Objects.SinglePlayerGameManager;


public class GameFrame extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static JPanel contentPanel;
	
	private static SingleplayerPanel singleplayerPanel;
	private static MenuPanel menuPanel;
	private static LevelEditorPanel levelEditorPanel;
	private static SettingsPanel settingsPanel;
	private static CreditsPanel creditsPanel;
	private static MultiplayerPanel multiplayerPanel;
	private static ScoreboardPanel scoreboardPanel;
	private GameManager manager;
	
	final static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
	
	public GameFrame() throws IOException{
		initFrame();
	}
	
	public void initFrame() throws IOException{
		this.setUndecorated(true);
		this.setSize(1280, 800);
		this.setResizable(true);
		this.setDefaultCloseOperation(GameFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	
	
	public static void main(String[] args) throws IOException
	{
		final GameFrame frame = new GameFrame();
		contentPanel = new JPanel(new BorderLayout());
        frame.add(contentPanel);
       
        
        
        levelEditorPanel = new LevelEditorPanel(frame);
        creditsPanel = new CreditsPanel(frame);
        settingsPanel = new SettingsPanel(frame);
        scoreboardPanel = new ScoreboardPanel(frame);
        
        menuPanel = new MenuPanel(frame);
        multiplayerPanel = new MultiplayerPanel(frame);
        singleplayerPanel = new SingleplayerPanel(frame);
        switchTo(menuPanel);
        
        device.setFullScreenWindow(frame);
		frame.setVisible(true);
	}
	
	public JPanel getGamePanel() throws IOException, FontFormatException{
		
		if(menuPanel.isResumable())
			return new GamePanel(manager,menuPanel);
		else{
			manager = new SinglePlayerGameManager();		
			return new GamePanel(manager,menuPanel);
		}
	}
	
	public JPanel getMultiplayerPanel()
	{
		return multiplayerPanel;
	}
	
	public JPanel getScoreboardPanel(){
		return scoreboardPanel;
	}
	
	public JPanel getLevelEditorPanel(){
		return levelEditorPanel;
	}
	
	public JPanel getCreditsPanel() {
		return creditsPanel;
	}
	
	public JPanel getSettingsPanel(){
		return settingsPanel;
	}
	
	public JPanel getMenuPanel(){
		return menuPanel;
	}
	
	public JPanel getSingleplayerPanel(){
		return singleplayerPanel;
	}
	
	
	public static void switchTo(final JPanel panel)
    {
        SwingUtilities.invokeLater(new Runnable()
            {
                @Override
                public void run()
                {
                    contentPanel.removeAll();
                    contentPanel.add(panel, BorderLayout.CENTER);
                    contentPanel.updateUI();
                    panel.requestFocus();
                }
            });
    }

}
