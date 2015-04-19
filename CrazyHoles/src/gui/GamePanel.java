package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSplitPane;

import Objects.Ball;
import Objects.GameManeger;
import Objects.World;
import Objects.WorldImpl;

public class GamePanel extends JPanel 
{

	private GameManeger gameManager =null;
	
	
	public GamePanel(GameManeger gameManager ) {
		super.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.gameManager = gameManager;
       
        setPreferredSize(new Dimension(800, 800));
        LeftGamePanel panel1 = new LeftGamePanel(this.gameManager.getWorld());
    	RightGamePanel panel2 = new RightGamePanel(this.gameManager.getWorld());
        this.add(panel1);
	}

	
	
	public static void main(String[] args)
	{
		final JFrame frame = new JFrame("Split Pane Example");
		       // Display the window.
		        frame.setSize(1000,1000);
		        GameManeger gameManager = new GameManeger();
		        gameManager.start();
		        frame.setVisible(true);
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		        GamePanel panel = new GamePanel(gameManager);
		        
		        frame.getContentPane().add(panel);

	}

	
}
