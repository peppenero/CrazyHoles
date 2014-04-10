package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainMenu {

  public static void main(String[] args) {
    ImagePanel panel = new ImagePanel();

    JFrame frame = new JFrame();
    frame.getContentPane().add(panel);
    frame.pack();
    frame.setVisible(true);
  }
}

class ImagePanel extends JPanel {

  private Image img = new ImageIcon("images/Sfondo.jpg").getImage();

  

  public ImagePanel() {
  
    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
    setPreferredSize(size);
    setMinimumSize(size);
    setMaximumSize(size);
    setSize(size);
    setLayout(null);
  }

  public void paintComponent(Graphics g) {
    g.drawImage(img, 0, 0, null);
  }

}