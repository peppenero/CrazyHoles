package gui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class LevelEditorPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final GameFrame frame;
	private final LevelEditorLeftPanel leftPanel = new LevelEditorLeftPanel();
	
	
	private final OurButton addButton = new OurButton("ADD OBJECT");
	private final OurButton moveButton = new OurButton("MOVE OBJECT");
	private final OurButton deleteButton = new OurButton("DELETE OBJECT");
	private final OurButton saveButton = new OurButton("SAVE LEVEL");
	private final OurButton backToMenuButton = new OurButton("BACK TO MENU");
	private final JLabel color = new JLabel("Color");
	private final JLabel selectedHole = new JLabel("Selected hole");
	private final JLabel ballsNumber = new JLabel("Balls number");
	private final SpinnerNumberModel model = new SpinnerNumberModel(1, 1, 9, 1);
	private final JSpinner spinner = new JSpinner(model);
	
	private final JComboBox<Integer> holesBox;
	private final JComboBox<String> colorsBox;
	private int numHoles=0,lastIndex=0;
	
	public LevelEditorPanel(GameFrame frameSup){
		frame=frameSup;
		setLayout(null);
		
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
		
		addButton.setBounds(1000,120,OurButton.WIDTH,OurButton.HEIGHT);
		add(addButton);
		
		color.setFont(OurFont.getInstance().deriveFont(Font.TRUETYPE_FONT, 14));
		color.setBounds(1000,50,100,50);
		add(color);
		
		colorsBox.setBounds(1000,85,100,30);
		add(colorsBox);
		
		selectedHole.setFont(OurFont.getInstance().deriveFont(Font.TRUETYPE_FONT, 14));
		selectedHole.setBounds(1000, 215, 135, 50);
		add(selectedHole);
		
		holesBox.setBounds(1000, 250, 100, 30);
		add(holesBox);
		
		moveButton.setBounds(1000, 300, OurButton.WIDTH, OurButton.HEIGHT);
		add(moveButton);
		moveButton.setEnabled(false);
		
		deleteButton.setBounds(1000, 350, OurButton.WIDTH, OurButton.HEIGHT);
		add(deleteButton);
		deleteButton.setEnabled(false);
		
		ballsNumber.setBounds(1000, 410, 150, 20);
		ballsNumber.setFont(OurFont.getInstance().deriveFont(Font.TRUETYPE_FONT, 14));
		add(ballsNumber);
		spinner.setBounds(1000, 430, 50, 20);
		add(spinner);
		
		saveButton.setBounds(1000, 500, OurButton.WIDTH, OurButton.HEIGHT);
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
				leftPanel.saveLevel(model.getNumber().intValue());
			}
		});
		
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(ImageProv.getIstance().getBackgroundVuoto(), 0, 0, this);
	}
}
