package gui;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Objects.GameManager;


public class GamePanel extends JPanel 
{
	
	private static final long serialVersionUID = 1L;
	private LeftGamePanel leftGamePanel;
	private GameManager gameManager;
	
	
	
	public GamePanel(GameManager gameManager ) {
		super.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.gameManager = gameManager;
       
        setPreferredSize(new Dimension(800, 800));
        leftGamePanel = new LeftGamePanel(this.gameManager.getWorld());
        this.add(leftGamePanel);
	}
	
	public LeftGamePanel getLeftPanel(){
		return leftGamePanel;
	}
	
	
	
	public static void main(String[] args)
	{
		final JFrame frame = new JFrame("Split Pane Example");
		       // Display the window.
		        frame.setSize(1000,1000);
		        GameManager gameManager = new GameManager();
		        gameManager.start();
		        frame.setVisible(true);
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		        GamePanel panel = new GamePanel(gameManager);
		        
		        frame.getContentPane().add(panel);
		        
		    

	}

	
}
