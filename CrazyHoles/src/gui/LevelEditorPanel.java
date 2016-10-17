package gui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
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
	private final JLabel color = new JLabel("COLOR");
	private final JLabel selectedHole = new JLabel("SELECTED HOLE");
	private final JLabel ballsNumber = new JLabel("BALLS NUMBER");
	private final JLabel holeScore = new JLabel("HOLE SCORE");
	private final SpinnerNumberModel model = new SpinnerNumberModel(1, 1, 9, 1);
	private final JSpinner spinner = new JSpinner(model);
	
	private final JComboBox<Integer> holesBox;
	private final JComboBox<String> colorsBox;
	private JTextField score = new JTextField();
	private int numHoles=0,lastIndex=0;
	
	public LevelEditorPanel(GameFrame frameSup){
		frame=frameSup;
		setLayout(null);
		
		holesBox = new JComboBox<Integer>();
		colorsBox = new JComboBox<String>();
		colorsBox.addItem("Red");
		colorsBox.addItem("Green");
		colorsBox.addItem("Yellow");
		
		
		leftPanel.setBounds(40,20,700,750);
		add(leftPanel);
		
		holeScore.setFont(OurFont.getInstance().deriveFont(Font.TRUETYPE_FONT, 20));
		holeScore.setBounds(1100, 50 , 150, 50);
		add(holeScore);
		score.setBounds(1125, 85, 50,30 );
		add(score);
		
		color.setFont(OurFont.getInstance().deriveFont(Font.TRUETYPE_FONT, 20));
		color.setBounds(965,50,100,50);
		add(color);
		colorsBox.setBounds(950,85,100,30);
		add(colorsBox);
		
		addButton.setBounds(975,120,OurButton.WIDTH,OurButton.HEIGHT);
		add(addButton);
		
		ballsNumber.setBounds(975, 200, 150, 20);
		ballsNumber.setFont(OurFont.getInstance().deriveFont(Font.TRUETYPE_FONT, 20));
		add(ballsNumber);
		spinner.setBounds(1025, 225, 50, 30);
		add(spinner);
		
		selectedHole.setFont(OurFont.getInstance().deriveFont(Font.TRUETYPE_FONT, 20));
		selectedHole.setBounds(975, 265, 150, 50);
		add(selectedHole);
		holesBox.setBounds(1000, 305, 100, 30);
		add(holesBox);
		
		moveButton.setBounds(975, 340, OurButton.WIDTH, OurButton.HEIGHT);
		add(moveButton);
		moveButton.setEnabled(false);
		
		deleteButton.setBounds(975, 400, OurButton.WIDTH, OurButton.HEIGHT);
		add(deleteButton);
		deleteButton.setEnabled(false);
		
		
		
		saveButton.setBounds(975, 500, OurButton.WIDTH, OurButton.HEIGHT);
		add(saveButton);
		saveButton.setEnabled(false);
		
		backToMenuButton.setBounds(1000,700,OurButton.WIDTH,OurButton.HEIGHT);
		add(backToMenuButton);
		
		
		addButton.setOnClickBehaviour(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				leftPanel.addHole(++lastIndex,colorsBox.getSelectedItem(),Integer.valueOf(score.getText()));
				score.setText(null);
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
		
		backToMenuButton.setOnClickBehaviour(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				GameFrame.switchTo(frame.getMenuPanel());
			}
		});
		
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(ImageProv.getIstance().getBackgroundVuoto(), 0, 0, this);
	}
}
