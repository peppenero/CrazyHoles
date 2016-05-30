package Objects;

import java.util.ArrayList;
import java.util.List;

public class WorldImpl implements World {
	
   
    private ArrayList<Hole> holes = new ArrayList<>();
    private List<Ball> balls = new ArrayList<>();
	private int height= 60;
	private int width = 80;
	
	@Override
	public void update() {
		

	}
	public void reset()
	{
		holes.clear();
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

	public ArrayList<Hole> getHoles() {
		return holes;
	}

	public void setHoles(ArrayList<Hole> holes) {
		this.holes = holes;
	}

	public List<Ball> getBalls() {
		return balls;
	}

	public void setBalls(List<Ball> balls) {
		this.balls = balls;
	}
	
	

}
