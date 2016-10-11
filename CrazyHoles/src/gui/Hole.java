package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;


import javax.swing.JComponent;


public class Hole extends JComponent {
	
	private int x=250,y=250;
	private int dimx=100,dimy=105;
	private int dimCampox=700, dimCampoy=750;
	public MouseListener ml;
	public int index;
	private Color color;
	public String colore;
	

	public Hole(int i,String c){
		index=i;
		System.out.println(c);
		switch(c){
		case "verde":
			color = Color.GREEN;
			colore = "verde";
			break;
		case "giallo":
			color = Color.YELLOW;
			colore = "giallo";
			break;
		case "blu":
			color = Color.BLUE;
			colore = "blu";
			break;
		}
			
	}
	
	public String getColor() {
		return color.toString();
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public int getDimx() {
		return dimx;
	}


	public void setDimx(int dimx) {
		this.dimx = dimx;
	}


	public int getDimy() {
		return dimy;
	}


	public void setDimy(int dimy) {
		this.dimy = dimy;
	}


	public int getDimPx() {
		return dimCampox;
	}


	public void setDimPx(int dimPx) {
		this.dimCampox = dimPx;
	}


	public int getDimPy() {
		return dimCampoy;
	}


	public void setDimPy(int dimPy) {
		this.dimCampoy = dimPy;
	}


	public MouseListener getMl() {
		return ml;
	}


	public void setMl(MouseListener ml) {
		this.ml = ml;
	}
	
}

