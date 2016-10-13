package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import Objects.GameManager;
import Objects.Object;
import Objects.OnlineGameManager;



public class ReturnDialog extends JDialog {

	
	private static final long serialVersionUID = 1L;

	private JLabel label;
	private LeftGamePanel leftPanel;
	private GameManager gameManager;
	
	public ReturnDialog(LeftGamePanel left)
	{
		leftPanel = left;
		gameManager = leftPanel.getGameManager();
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		label = new JLabel("PRESS ESC TO RETURN");
		label.setAlignmentX(CENTER_ALIGNMENT);
		label.setAlignmentY(BOTTOM_ALIGNMENT);
		add(label);
		this.setModal(true);
		this.setUndecorated(true);
		
		addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyPressed(e);
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
				{
					
					if(((OnlineGameManager) gameManager).isServer())
					{
						try {
							((OnlineGameManager) gameManager).getHost().ends();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						GameFrame.switchTo(leftPanel.getMenuPanel());
					}
					else
					{
						try {
							((OnlineGameManager) gameManager).getClient().ends();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						GameFrame.switchTo(leftPanel.getMenuPanel());
					}
					getContentPane().setVisible(false);
				}

			}
		});
	}

	

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}
}
