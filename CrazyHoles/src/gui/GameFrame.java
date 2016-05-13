package gui;

import java.awt.BorderLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Objects.GameManager;
import Objects.WorldImpl;


public class GameFrame extends JFrame
{
	private static JPanel contentPanel;
	
	private static MenuPanel menuPanel;
	private static GamePanel gamePanel;
	private static LevelEditorPanel levelEditorPanel;
	private static SettingsPanel settingsPanel;
	private static CreditsPanel creditsPanel;
	private GameManager manager;
	
	final static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
	
	public GameFrame() throws IOException{
		initFrame();
	}
	
	public void initFrame() throws IOException{
		this.setUndecorated(true);
		this.setSize(1280, 800);
		this.setResizable(true);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		manager = new GameManager();
	}
	
	
	
	public static void main(String[] args) throws IOException
	{
		final GameFrame frame = new GameFrame();
		contentPanel = new JPanel(new BorderLayout());
        frame.add(contentPanel);
       
        
        
        levelEditorPanel = new LevelEditorPanel();
        creditsPanel = new CreditsPanel(frame);
        settingsPanel = new SettingsPanel(frame);
        
        menuPanel = new MenuPanel(frame);
        switchTo(menuPanel);

		device.setFullScreenWindow(frame);
		frame.setVisible(true);
	}
	
	public JPanel getGamePanel() throws IOException{
		return new GamePanel(manager);
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
