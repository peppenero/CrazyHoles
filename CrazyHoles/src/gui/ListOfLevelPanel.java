package gui;

import java.awt.Color;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import Objects.FreePracticeGameManager;
import Objects.GameManager;
import Objects.Host;
import Objects.OnlineGameManager;


public class ListOfLevelPanel extends JPanel 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private File dir = new File("data/levels/");
	private JList list;
	private String[] items;
	private File world;
	private GameManager manager;
	private OurButton backButton = new OurButton("BACK");
	private JPanel backPanel;
	private String type;
	
	public ListOfLevelPanel(JPanel back, final String type)
	{
		items = dir.list();
		setLayout(null);
		this.type=type;
		backPanel=back;
		list = new JList(items);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setOpaque(false);
		list.setBackground(new Color(255,255,255,0));	
		JScrollPane listScroller = new JScrollPane(list);	
		listScroller.getViewport().setOpaque(false);
		listScroller.setBorder(null);
		listScroller.setOpaque(false);
		add(listScroller);
		add(backButton);
	
		 list.addMouseListener(new MouseAdapter() {
		     public void mouseClicked(MouseEvent evt) {
		    	 JList list = (JList)evt.getSource();
		         if (evt.getClickCount() == 2) {

		             // Double-click detected
		             int index = list.locationToIndex(evt.getPoint());
		             world = new File("data/levels/"+items[index]);
		           if(type.equals("Online"))
		           {
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
		           else
		           {
		        	   try {
						manager = new FreePracticeGameManager(world);
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
		    	 }
		    });
		 
			backButton .setOnClickBehaviour(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					GameFrame.switchTo(backPanel);
				}
			});
			backButton.setBounds(100,700,OurButton.WIDTH,OurButton.HEIGHT);
			listScroller.setBounds(100,280,150,200);
			
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
	g.drawImage(ImageProv.getIstance().getBackground(), 0, 0,this);
	}
	
}
