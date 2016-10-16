package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

//Questa classe viene usata come bottone pur essendo una label. L'unica cosa che bisogna fare è dichiarare il
//comportamento con la funzione setOnClickBehaviour(MouseAdapter adapter). Dove andremo a dichiarare il comportamento
// si dovrà utilizzare un mouseAdapter e definire la funzione mouseClicked(MouseEvent e).

public class OurButton extends JLabel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 220;
	public static final int HEIGHT = 80;

	public OurButton(String t){
		super(t);
		Font font = OurFont.getInstance().deriveFont(Font.TRUETYPE_FONT, 30);
		setForeground(Color.BLACK);
		this.setFont(font);
		setSize(WIDTH, HEIGHT);
		mouseFunction();
		
	}
	
	public void setOnClickBehaviour(MouseAdapter adapter){
		this.addMouseListener(adapter);
	}
	
	private void mouseFunction(){
		this.addMouseListener(new MouseAdapter() {
			 @Override
             public void mouseEntered(MouseEvent e) {
                 setForeground(new Color(179,45,176));
             }

             @Override
             public void mouseExited(MouseEvent e) {
            	 setForeground(Color.BLACK);
             }
             
             public void mouseReleased(MouseEvent e){
            	 setForeground(Color.BLACK);
             }
		});
	}
	
	
}
