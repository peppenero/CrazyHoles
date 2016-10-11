package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class LevelEditorPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image background;
	private GameFrame frame;
	private LevelEditorLeftPanel leftPanel = new LevelEditorLeftPanel();
	
	
	private OurButton addButton = new OurButton("ADD OBJECT");
	private OurButton moveButton = new OurButton("MOVE OBJECT");
	private OurButton deleteButton = new OurButton("DELETE OBJECT");
	private OurButton saveButton = new OurButton("SAVE LEVEL");
	private OurButton backToMenuButton = new OurButton("BACK TO MENU");
	
	private JComboBox<Integer> holesBox;
	private JComboBox<String> colorsBox;
	private int numHoles=0,lastIndex=0;
	
	public LevelEditorPanel(GameFrame frameSup){
		frame=frameSup;
		setLayout(null);
		background = Toolkit.getDefaultToolkit().createImage("images/Vuoto.jpg");
		
		holesBox = new JComboBox<Integer>();
		colorsBox = new JComboBox<String>();
		colorsBox.addItem("Red");
		colorsBox.addItem("Green");
		colorsBox.addItem("Yellow");
		
		backToMenuButton.setOnClickBehaviour(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				GameFrame.switchTo(frame.getMenuPanel());
			}
		});
		
		leftPanel.setBounds(40,20,700,750);
		add(leftPanel);
		
		backToMenuButton.setBounds(1000,700,OurButton.WIDTH,OurButton.HEIGHT);
		add(backToMenuButton);
		
		addButton.setBounds(1000,50,OurButton.WIDTH,OurButton.HEIGHT);
		add(addButton);
		
		colorsBox.setBounds(1000,120,100,30);
		add(colorsBox);
		
		holesBox.setBounds(1000, 250, 100, 30);
		add(holesBox);
		
		moveButton.setBounds(1000, 300, OurButton.WIDTH, OurButton.HEIGHT);
		add(moveButton);
		moveButton.setEnabled(false);
		
		deleteButton.setBounds(1000, 350, OurButton.WIDTH, OurButton.HEIGHT);
		add(deleteButton);
		deleteButton.setEnabled(false);
		
		saveButton.setBounds(1000, 400, OurButton.WIDTH, OurButton.HEIGHT);
		add(saveButton);
		saveButton.setEnabled(false);
		
		addButton.setOnClickBehaviour(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				leftPanel.addHole(++lastIndex,colorsBox.getSelectedItem());
				++numHoles;
				holesBox.addItem(lastIndex);
				System.out.println(lastIndex);
				moveButton.setEnabled(true);
				deleteButton.setEnabled(true);
				saveButton.setEnabled(true);
				leftPanel.repaint();
			}
		});
		
		moveButton.setOnClickBehaviour(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				leftPanel.muoviBuca((Integer)holesBox.getSelectedItem());
			}
		});
		
		deleteButton.setOnClickBehaviour(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				leftPanel.removeHole((Integer)holesBox.getSelectedItem());
				holesBox.removeItem(holesBox.getSelectedItem());
				numHoles--;
				if(numHoles==0){
					moveButton.setEnabled(false);
					deleteButton.setEnabled(false);
					saveButton.setEnabled(false);
				}
				leftPanel.repaint();
			}
		});

		saveButton.setOnClickBehaviour(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				leftPanel.saveLevel();
			}
		});
		
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(background, 0, 0, this);
	}
}
