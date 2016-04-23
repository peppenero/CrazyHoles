package Objects;

import java.util.ArrayList;

public class WorldImpl implements World {
	
   
    ArrayList<Hole> holes;
	private int height= 60;
	private int width = 40;
	
	@Override
	public void update() {
		

	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	

}
