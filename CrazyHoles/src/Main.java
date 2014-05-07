import Objects.Ball;
import Objects.Hole;
import Objects.Object;
import Objects.World;
import Objects.WorldImpl;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Hole buco = new Hole("bianco",50,0,0);
		World world = new WorldImpl();
		Ball ball = new Ball("rosso", 10, 0, 5);
		ball.setWorld(world);
		System.out.println(ball.getCorner());
		System.out.println(ball.getCorner());
		System.out.print(buco.getScore());

	}

}
