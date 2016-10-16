package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class LevelEditorLeftPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Hole> holes = new ArrayList<Hole>();
	private Hole selectedHole;
	private ImageProv imageProv = ImageProv.getIstance();
	
	public LevelEditorLeftPanel(){
		this.setBackground(Color.BLACK);
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(700,750));
		for(Hole b:holes){
			this.add(b);
		}
	}
	
	public void muoviBuca(int n){
		//C'è qualche problemino perchè si crea ogni volta un mouseAdapter.
		selectedHole=holes.get(n-1);
				
		MouseAdapter ma = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if((e.getX()+selectedHole.getDimx()/2<=selectedHole.getDimPx() && e.getX()-selectedHole.getDimx()/2>=0) && (e.getY()+selectedHole.getDimy()/2<=selectedHole.getDimPy() && e.getY()-selectedHole.getDimy()/2>=0)){
				selectedHole.setX(e.getX());
				selectedHole.setY(e.getY());

				repaint();
				}
			} 
		};
		this.addMouseListener(ma);
	}
	
		
	public void addHole(int lastIndex,Object color){
		holes.add(new Hole(lastIndex,(String)color));
		System.out.println((String)color);
	}
	
	public void removeHole(Integer selectedItem) {
		// TODO Auto-generated method stub
		System.out.println("selected item" + selectedItem);
		Hole daEliminare = new Hole(0,"Verde");
		for(int i=0;i<holes.size();i++){
			System.out.println(holes.get(i).index+","+selectedItem);
			if(holes.get(i).index==selectedItem){
				daEliminare=holes.get(i);
				break;
			}
		}
		holes.remove(daEliminare);
	}
	
	public void saveLevel(int ballsNumber){
		 final JFileChooser fileChooser = new JFileChooser();
	        final int response = fileChooser.showSaveDialog(this);
	        if (response == JFileChooser.APPROVE_OPTION)
	        {
	            try
	            {
	                final PrintWriter pw = new PrintWriter(fileChooser.getSelectedFile());
	                for(int i=0;i<holes.size();i++){
						pw.print(holes.get(i).toString());
						pw.println();
	                }
	                pw.print(ballsNumber);
	                pw.println();
	                pw.close();
	                JOptionPane.showMessageDialog(null, "File " + fileChooser.getSelectedFile().getAbsolutePath()
	                        + " saved");
	            }
	            catch (final IOException e1)
	            {
	                JOptionPane.showMessageDialog(null, "Impossible to write " + fileChooser.getSelectedFile().getAbsolutePath());
	            }
	        }
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(Hole b:holes){
			b.paintComponent(g);
			g.drawImage(imageProv.getHole(b.colore), b.getX()-50, b.getY()-50, this);
		}
	}

}
