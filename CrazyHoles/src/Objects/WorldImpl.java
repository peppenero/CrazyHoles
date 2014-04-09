package Objects;

import java.util.ArrayList;

public class WorldImpl implements World {
	
	ArrayList<Ball> balls;
	ArrayList<Hole> holes;

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

}
