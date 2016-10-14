package gui;

import javax.swing.JPanel;

public class FreePracticePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ListOfLevelPanel listLevels;
	
	public FreePracticePanel(GameFrame frame)
	{
		listLevels=new ListOfLevelPanel(frame.getMenuPanel(),"FreePractice");
		add(listLevels);
	}
}
