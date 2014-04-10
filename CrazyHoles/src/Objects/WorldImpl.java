package Objects;

import java.util.ArrayList;

public class WorldImpl implements World {
	
    ArrayList<Ball> balls;
    ArrayList<Hole> holes;
	private int height= 40;
	private int width = 20;

	@Override
	public boolean areThereBalls() {
		// TODO Auto-generated method stub
		if(balls.isEmpty()){
			return false;
		}
		
		return true;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

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
