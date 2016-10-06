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
	
	ArrayList<Buca> buche = new ArrayList<Buca>();
	Buca bucaSelezionata;
	ImageProv imageProv = new ImageProv();
	
	public LevelEditorLeftPanel(){
		this.setBackground(Color.BLACK);
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(700,750));
		for(Buca b:buche){
			this.add(b);
		}
	}
	
	public void muoviBuca(int n){
		//C'è qualche problemino perchè si crea ogni volta un mouseAdapter.
		bucaSelezionata=buche.get(n-1);
				
		MouseAdapter ma = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if((e.getX()+bucaSelezionata.getDimx()/2<=bucaSelezionata.getDimPx() && e.getX()-bucaSelezionata.getDimx()/2>=0) && (e.getY()+bucaSelezionata.getDimy()/2<=bucaSelezionata.getDimPy() && e.getY()-bucaSelezionata.getDimy()/2>=0)){
				bucaSelezionata.setX(e.getX());
				bucaSelezionata.setY(e.getY());

				repaint();
				}
			} 
		};
		this.addMouseListener(ma);
	}
	
		
	public void addBuca(int lastIndex,Object color){
		buche.add(new Buca(lastIndex,(String)color));
		System.out.println((String)color);
	}
	
	public void removeBuca(Integer selectedItem) {
		// TODO Auto-generated method stub
		System.out.println("selected item" + selectedItem);
		Buca daEliminare = new Buca(0,"Verde");
		for(int i=0;i<buche.size();i++){
			System.out.println(buche.get(i).index+","+selectedItem);
			if(buche.get(i).index==selectedItem){
				daEliminare=buche.get(i);
				break;
			}
		}
		buche.remove(daEliminare);
	}
	
	public void saveLevel(){
		 final JFileChooser fileChooser = new JFileChooser();
	        final int response = fileChooser.showSaveDialog(this);
	        if (response == JFileChooser.APPROVE_OPTION)
	        {
	            try
	            {
	                final PrintWriter pw = new PrintWriter(fileChooser.getSelectedFile());
	                for(int i=0;i<buche.size();i++){
						pw.print("Buca, " + buche.get(i).getX() + " "  + buche.get(i).getY() + " " + buche.get(i).getColor());
						pw.println();
	                }
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
		for(Buca b:buche){
			g.drawImage(imageProv.getHole(b.colore), b.getX()-50, b.getY()-50, this);
		}
	}

}
