package gui;

import javax.swing.Icon;
import javax.swing.JButton;

public class MenuButton extends JButton {

	private static final long serialVersionUID = 1L;

	public MenuButton(Icon background,Icon rollover){
		this.setContentAreaFilled(false);
		this.setIcon(background);
		this.setRolloverIcon(rollover);
		this.setBorderPainted(false);
	}
}
