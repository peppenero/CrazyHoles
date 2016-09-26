package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class LevelEditorPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image background;
	GameFrame frame;
	
	
	private OurButton addButton = new OurButton("ADD OBJECT");
	private OurButton moveButton = new OurButton("MOVE OBJECT");
	private OurButton deleteButton = new OurButton("DELETE OBJECT");
	private OurButton backToMenuButton = new OurButton("BACK TO MENU");
	
	public LevelEditorPanel(GameFrame frameSup){
		frame=frameSup;
		setLayout(null);
		background = Toolkit.getDefaultToolkit().createImage("images/Vuoto.jpg");
		
		backToMenuButton.setOnClickBehaviour(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				GameFrame.switchTo(frame.getMenuPanel());
			}
		});
		
		backToMenuButton.setBounds(1000,700,OurButton.WIDTH,OurButton.HEIGHT);
		add(backToMenuButton);
		
		addButton.setBounds(1000,170,OurButton.WIDTH,OurButton.HEIGHT);
		add(addButton);
		
		moveButton.setBounds(1000, 300, OurButton.WIDTH, OurButton.HEIGHT);
		add(moveButton);
		
		deleteButton.setBounds(1000, 350, OurButton.WIDTH, OurButton.HEIGHT);
		add(deleteButton);
		
		final JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(1000, 70, 150, 30);
		comboBox.addItem("Hole");
		comboBox.addItem("Obstacle");
		add(comboBox);
		
		final JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(1000, 120, 150, 30);
		comboBox_1.addItem("Yellow");
		comboBox_1.addItem("Green");
		add(comboBox_1);
		
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(comboBox.getSelectedItem()=="Obstacle"){
					comboBox_1.setEnabled(false);
				}
				else{
					comboBox_1.setEnabled(true);
				}
			}
		});
		
		
	}
	
	public void paintComponent(Graphics g){

		g.drawImage(background, 0, 0, this);

	}
}
