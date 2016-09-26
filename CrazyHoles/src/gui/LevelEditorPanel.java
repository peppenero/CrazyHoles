package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class LevelEditorPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image background;
	GameFrame frame;
	
	
	
	Icon add = new ImageIcon("images/add.png");
	Icon addSelected = new ImageIcon("images/add_selected.png");
	MenuButton addButton = new MenuButton(add,addSelected);
	
	Icon move = new ImageIcon("images/move.png");
	Icon moveSelected = new ImageIcon("images/move_selected.png");
	MenuButton moveButton = new MenuButton(move,moveSelected);
	
	Icon delete = new ImageIcon("images/delete.png");
	Icon deleteSelected = new ImageIcon("images/delete_selected.png");
	MenuButton deleteButton = new MenuButton(delete,deleteSelected);
	
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
		
		addButton.setBounds(1000,170,add.getIconWidth(),add.getIconHeight());
		add(addButton);
		
		moveButton.setBounds(1000, 300, move.getIconWidth(), move.getIconHeight());
		add(moveButton);
		
		deleteButton.setBounds(1000, 350, delete.getIconWidth(), delete.getIconHeight());
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
