package gui;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSplitPane;

import Objects.World;
import Objects.WorldImpl;

public class GamePanel extends JPanel 
{

	private World world;
	LeftGamePanel panel1 = new LeftGamePanel(world);
	RightGamePanel panel2 = new RightGamePanel(world);
	
	public GamePanel(World world) {
		super.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.world=world;
        this.add(panel1);
        this.add(panel2);
	}

	
	
	
	
	
	public static void main(String[] args)
	{
		final JFrame frame = new JFrame("Split Pane Example");
		       // Display the window.
		        frame.setSize(400,400);
		
		        frame.setVisible(true);
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		        World world = new WorldImpl();
		        GamePanel panel = new GamePanel(world);
		        
		        frame.getContentPane().add(panel);

	}

	
}
