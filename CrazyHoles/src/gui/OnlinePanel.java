package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.NumberFormat;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import Objects.Client;
import Objects.OnlineGameManager;

public class OnlinePanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image background;
	private OurButton newMatch = new OurButton("NEW MATCH");
	private OurButton joinMatch = new OurButton("JOIN MATCH");
	private JDialog ipDialog;
	private JButton okay;
	private JLabel title;
	private boolean setted =false;
	private OurButton backButton = new OurButton("BACK");
	private JPanel backPanel;
	private PanelIP ipPanel;
	private String ip;
	private Font font = OurFont.getInstance().deriveFont(Font.TRUETYPE_FONT, 30); 
	
	public OnlinePanel(final JPanel backPanel)
	{
		setLayout(null);
		background = Toolkit.getDefaultToolkit().getImage("images/Background.jpg");
		okay = new JButton("ENTER");
		title = new JLabel("INSERIRE IP HOST");
		ipDialog = new JDialog();
		title.setForeground(Color.BLACK);		
		ipDialog.setModal(true);
		ipDialog.setUndecorated(true);
		ipDialog.setSize(200,80);
		ipDialog.setLocation(200,200);
		ipDialog.setBackground(new Color(255,255,255,0));	
		ipDialog.setLayout(new BoxLayout(ipDialog.getContentPane(),BoxLayout.Y_AXIS));
		ipPanel = new PanelIP();
		title.setAlignmentX(CENTER_ALIGNMENT);
		title.setAlignmentY(BOTTOM_ALIGNMENT);
		ipDialog.add(title);
		ipDialog.add(ipPanel);
		ipDialog.setVisible(false);
		okay.setAlignmentX(CENTER_ALIGNMENT);
		okay.setAlignmentY(BOTTOM_ALIGNMENT);
		okay.setContentAreaFilled(false);
		ipDialog.add(okay);
		newMatch.setBounds(100, 400, OurButton.WIDTH, OurButton.HEIGHT);
		joinMatch.setBounds(100, 480, OurButton.WIDTH, OurButton.HEIGHT);
		backButton.setBounds(100,700,OurButton.WIDTH,OurButton.HEIGHT);
		this.backPanel = backPanel;
		
		newMatch.setOnClickBehaviour(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				
					GameFrame.switchTo(new ListOfLevelPanel(OnlinePanel.this,"Online"));
				
			}
			
		});
		joinMatch.setOnClickBehaviour(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				ipDialog.setVisible(true);			
			}
		});
		okay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
								
				setted=isSetted();
				if(setted)
				{
					ipDialog.setVisible(false);
					ip = ipPanel.getFirstField().getText() + "." + ipPanel.getSecondField().getText() + "." + ipPanel.getThirdField().getText() + "." + ipPanel.getFourthField().getText(); 
					try {
						Client client = new Client(ip);
						OnlineGameManager manager = new OnlineGameManager(client);
						GameFrame.switchTo(new GamePanel(manager, OnlinePanel.this));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
							
							try{
								if(e1 instanceof SocketTimeoutException)
								{
									throw new SocketTimeoutException();
								}
								else
								{
									try{
										if(e1 instanceof UnknownHostException)
										{
											throw new UnknownHostException();
										}
									}catch(UnknownHostException u)
										{
											JLabel unknownHost = new JLabel("WRONG IP");
											unknownHost.setFont(font);
											OnlinePanel.this.add(unknownHost);
											unknownHost.setBounds(100, 200, 400, 100);
											ipDialog.validate();
											ipDialog.repaint();
											ipDialog.pack();									
										}
								}
							}catch(SocketTimeoutException f)
							{
								JLabel timeout = new JLabel("TIMEOUT CONNESSIONE HOST NON DISPONIBILE");
								timeout.setFont(font);
								OnlinePanel.this.add(timeout);
								timeout.setBounds(100, 200, 400, 100);
								ipDialog.validate();
								ipDialog.repaint();
								ipDialog.pack();
							}
							e1.printStackTrace();
						}
					catch (FontFormatException e1) {
							// TODO Auto-generated catch block
						 e1.printStackTrace();
					 } catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					title.setForeground(Color.RED);
					title.setText("ONE OR MORE FIELDS EMPTY");
					ipDialog.validate();
					ipDialog.repaint();
					ipDialog.pack();
				}
			}
		});
		backButton .setOnClickBehaviour(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				GameFrame.switchTo(backPanel);
			}
		});
		add(backButton);
		add(newMatch);
		add(joinMatch);
		
	}
	private boolean isSetted()
	{
		
		if(ipPanel.getFirstField().getText().isEmpty())
		{
			return false;
		}
		else
		{
			if(ipPanel.getSecondField().getText().isEmpty())
			{
				return false;
			}
			else
			{
				if(ipPanel.getThirdField().getText().isEmpty())
				{
					return false;
				}
				else
				{
					if(ipPanel.getFourthField().getText().isEmpty())
					{
						return false;
					}
				}
			}
		}
		return true;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		g.drawImage(background, 0,0,this);
	}

	public JPanel getBackPanel() {
		return backPanel;
	}

	public void setBackPanel(JPanel backPanel) {
		this.backPanel = backPanel;
	}
	
	private class PanelIP extends JPanel
	{
		/**
		 * 
		 */
		
		private static final long serialVersionUID = 1L;
		private JTextField firstField = new JFormattedTextField(new DefaultFormatterFactory(new NumberFormatter())) ;
		private JTextField secondField = new JTextField(3);
		private JTextField thirdField = new JTextField(3);
		private JTextField fourthField = new JTextField(3);
		private JLabel dot = new JLabel(".");
		private JLabel dot1 = new JLabel(".");
		private JLabel dot2 = new JLabel(".");
		
		public PanelIP()
		{
			getFirstField().addKeyListener(new KeyAdapter() {
				int i =0;
				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
					super.keyPressed(e);
					if(!Character.isDigit(e.getKeyChar()))
					{
						e.consume();
					}
					else
					{
						i++;
					}
						if(i==3)
						{
							getSecondField().requestFocus();
						}		
				}
			});
			
			getSecondField().addKeyListener(new KeyAdapter() {
				int i =0;
				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
					super.keyPressed(e);
					if(!Character.isDigit(e.getKeyChar()))
					{
						e.consume();
					}
					else
					{
						i++;
					}
						if(i==3)
						{
							getThirdField().requestFocus();
						}		
				}
			});
			
			getThirdField().addKeyListener(new KeyAdapter() {
			
				int i =0;
				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
					super.keyPressed(e);
					if(!Character.isDigit(e.getKeyChar()))
					{
						e.consume();
					}
					else
					{
						i++;
					}
						if(i==3)
						{
							getFourthField().requestFocus();
						}		
				}
			});
			getFourthField().addKeyListener(new KeyAdapter() {

				int i =0;
				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
					super.keyPressed(e);
					if(!Character.isDigit(e.getKeyChar()))
					{
						e.consume();
					}
					else
					{
						i++;
					}
						if(i>3)
						{
							e.consume();
						}		
				}
			});
			
			setOpaque(false);;
			getFirstField().setColumns(3);
			add(getFirstField());
			add(dot);
			add(getSecondField());
			add(dot1);
			add(getThirdField());
			add(dot2);
			add(getFourthField());
		}

		public JTextField getFirstField() {
			return firstField;
		}

		public void setFirstField(JTextField firstField) {
			this.firstField = firstField;
		}

		public JTextField getSecondField() {
			return secondField;
		}

		public void setSecondField(JTextField secondField) {
			this.secondField = secondField;
		}

		public JTextField getThirdField() {
			return thirdField;
		}

		public void setThirdField(JTextField thirdField) {
			this.thirdField = thirdField;
		}

		public JTextField getFourthField() {
			return fourthField;
		}

		public void setFourthField(JTextField fourthField) {
			this.fourthField = fourthField;
		}
		
		
	
	}
}
