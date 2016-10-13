package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import Objects.Host;
import Objects.OnlineGameManager;


public class ListOfLevelPanel extends JPanel 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Image background;
	private File dir = new File("data/levels/");
	private JList list;
	private String[] items;
	private File world;
	private OnlineGameManager manager;
	private OurButton backButton = new OurButton("BACK");
	private JPanel backPanel;

	
	public ListOfLevelPanel(JPanel back)
	{
		background = Toolkit.getDefaultToolkit().getImage("images/Background.jpg");
		items = dir.list();
		setLayout(null);
		backPanel=back;
		list = new JList(items);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setOpaque(false);
		list.setBackground(new Color(255,255,255,0));
		add(backButton);
		add(list);
		 list.addMouseListener(new MouseAdapter() {
		     public void mouseClicked(MouseEvent evt) {
		    	 JList list = (JList)evt.getSource();
		         if (evt.getClickCount() == 2) {

		             // Double-click detected
		             int index = list.locationToIndex(evt.getPoint());
		             world = new File("data/levels/"+items[index]);
		           
		             try {
		            	  Host host = new Host(world);
						manager = new OnlineGameManager(host,world);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		             try {
						GameFrame.switchTo(new GamePanel(manager, ListOfLevelPanel.this));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (FontFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		         } 
		    	 }
		    });
			backButton .setOnClickBehaviour(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					GameFrame.switchTo(backPanel);
				}
			});
			backButton.setBounds(100,700,OurButton.WIDTH,OurButton.HEIGHT);
		 list.setBounds(100,280,100,200);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
	g.drawImage(background, 0, 0,this);
	}
	
}
