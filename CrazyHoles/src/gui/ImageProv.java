package gui;

import java.awt.Image;
import java.awt.Toolkit;



public class ImageProv 
{
	private final Image bucaVerde;
	//private final Image bucaBlu;
	private final Image bucaGialla;
	private final Image bucaRossa;
	private final Image pallaRossa;
	private final Image pallaVerde;
	private final Image pallaBlu;
	private final Image pallaGialla;
	
	
	public ImageProv()
	{
		final Toolkit tk = Toolkit.getDefaultToolkit();
		
		bucaGialla = tk.getImage("images/BucaGialla.png");
		bucaVerde = tk.getImage("images/BucaVerde.png");
		//bucaBlu = tk.getImage("images/BucaBlu.png");
		bucaRossa = tk.getImage("images/BucaRossa.png");
		pallaRossa = tk.getImage("images/PallaRossa.png");
		pallaVerde = tk.getImage("images/PallaVerde.png");
		pallaGialla = tk.getImage("images/PallaGialla.png");
		pallaBlu = tk.getImage("images/PallaBlu.png");	
	}
	
	public Image getHole(final String color)
	{
		switch(color)
		{
//			case "blu":
//			{
//				return bucaBlu;
//			}
			case "verde":
			{
				return bucaVerde;
			}
			case "giallo":
			{
				return bucaGialla;
			}
			case "rosso":
			{
				return bucaRossa;
			}
			default:
				break;
		}
		throw new IllegalArgumentException("unsupported hole color");
		
	}
	public Image getBall(final String color)
	{
		switch(color)
		{
			case "verde":
			{
				return pallaVerde;
			}
			case "blu":
			{
				return pallaBlu;
			}
			case "giallo":
			{
				return pallaGialla;
			}
			case "rosso":
			{
				return pallaRossa;
			}
			default:
				break;
				
		}
		throw new IllegalArgumentException("unsupported ball color");
	}
	
}











