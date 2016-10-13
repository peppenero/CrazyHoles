package gui;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class OurFont {
	
	private static Font instance = null;
	
	private OurFont(){
		
	}
	
	public static Font getInstance(){
		if(instance==null){
			try {
				instance = Font.createFont(Font.TRUETYPE_FONT, new File("data/EASPORTS15.ttf"));
			} catch (FontFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	
	
	
}
