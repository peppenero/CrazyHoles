package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import Objects.FreePracticeGameManager;
import Objects.GameManager;
import Objects.Host;
import Objects.OnlineGameManager;

public class ListOfLevelPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private File dir = new File("data/levels/");
	private JList<String> list;
	private String[] items;
	private File world;
	private GameManager manager;
	private OurButton backButton = new OurButton("BACK");
	private JPanel backPanel;
	private OurButton loadLevel = new OurButton("PICK LEVEL");
	private String typeOfManager;
	private JLabel information = new JLabel(
			"PICK A LEVEL FROM FILESYSTEM OR CHOOSE ONE FROM THE LIST");

	public ListOfLevelPanel(JPanel back, final String type) {
		typeOfManager = type;
		items = dir.list();
		setLayout(null);
		backPanel = back;
		list = new JList<String>(items);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setOpaque(false);
		list.setBackground(new Color(255, 255, 255, 0));
		list.setFont(OurFont.getInstance().deriveFont(Font.TRUETYPE_FONT, 15));
		list.setForeground(Color.BLACK);

		JScrollPane listScroller = new JScrollPane(list);
		listScroller.getViewport().setOpaque(false);
		listScroller.setBorder(null);
		listScroller.setOpaque(false);

		add(listScroller);
		add(backButton);
		add(loadLevel);

		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();
				if (evt.getClickCount() == 2) {

					// Double-click detected
					int index = list.locationToIndex(evt.getPoint());
					world = new File("data/levels/" + items[index]);
					if (type.equals("Online")) {
						try {
							Host host = new Host(world);
							manager = new OnlineGameManager(host, world);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							GameFrame.switchTo(new GamePanel(manager,
									ListOfLevelPanel.this));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (FontFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						try {
							manager = new FreePracticeGameManager(world);
							GameFrame.switchTo(new GamePanel(manager,
									ListOfLevelPanel.this));
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

		information.setFont(OurFont.getInstance().deriveFont(
				Font.TRUETYPE_FONT, 30));
		information.setBounds(100, 175, 850, 30);
		information.setForeground(Color.BLUE);
		add(information);

		loadLevel.setOnClickBehaviour(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				load();
			}
		});
		loadLevel.setBounds(100, 200, OurButton.WIDTH, OurButton.HEIGHT);
		loadLevel.setFont(OurFont.getInstance().deriveFont((Font.TRUETYPE_FONT),20));

		backButton.setOnClickBehaviour(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				GameFrame.switchTo(backPanel);
			}
		});
		backButton.setBounds(100, 700, OurButton.WIDTH, OurButton.HEIGHT);

		listScroller.setBounds(100, 280, 150, 200);

	}

	private void load() {
		final JFileChooser fileChooser = new JFileChooser();
		final int response = fileChooser.showOpenDialog(this);
		if (response == JFileChooser.APPROVE_OPTION) {
			world = fileChooser.getSelectedFile();
			if (typeOfManager.equals("Online")) {
				try {
					Host host = new Host(world);
					manager = new OnlineGameManager(host, world);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					GameFrame.switchTo(new GamePanel(manager,
							ListOfLevelPanel.this));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FontFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					manager = new FreePracticeGameManager(world);
					GameFrame.switchTo(new GamePanel(manager,
							ListOfLevelPanel.this));
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

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);

		g.drawImage(ImageProv.getIstance().getBackground(), 0, 0, this);
	}

}
