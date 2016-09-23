package Objects;

import java.util.ArrayList;
import java.util.List;

import common.*;

public class Ball extends Object implements HasScore {

	private double speed = 1;
	private int score = 0;
	private float corner = 350;
	private int ballRadius = 1;
	private float deltaY, deltaX;
	private List<Hole> holes;
	private int d;
	private boolean intersecate = false;
	private int holePoint;
<<<<<<< HEAD
	private boolean dropped=false;
	
	public EquazioniCirconferenza getEquation()
	{
		EquazioniCirconferenza eq = new EquazioniCirconferenza((int)this.getX(), (int) this.getY(), this.getBallRadius());
=======

	public EquazioniCirconferenza getEquation() {
		EquazioniCirconferenza eq = new EquazioniCirconferenza(
				(int) this.getX(), (int) this.getY(), this.getBallRadius());
>>>>>>> refs/remotes/origin/master
		return eq;
	}

	public Ball(int s, ArrayList<String> colors, WorldImpl world) {
		super(world);
		this.setColor(colors);
		this.setScore(s);
		deltaX = (float) ((speed * Math.cos(Math.toRadians(corner))));
		deltaY = (float) (speed * (float) Math.sin(Math.toRadians(corner)));
		this.setX((world.getWidth() / 2) - getBallRadius());
		this.setY(world.getHeight() - getBallRadius());
		holes = world.getHoles();
		System.out.println(this.color);
	}

	public float getDeltaX() {
		return deltaX;
	}

	public float getDeltaY() {
		return deltaY;
	}

	@Override
	public void setScore(int s) {
		this.score = s;
	}

	@Override
	public int getScore() {
		return this.score;
	}

	@Override
	public String getColor() {

		return this.color;
	}

	public int move() {

		float x = getX();
		float y = getY();
		float diff = 0;
		boolean xflag = false;
		boolean yflag = false;

		if ((((x + getBallRadius()) + deltaX) > (world.getWidth()))
				|| (((x - getBallRadius()) + deltaX) < 0)) {
			if ((deltaX < 0) && (x - getBallRadius() > 0)) {
				xflag = true;
				diff = (getX() - (x - getBallRadius()));
			}
			if ((deltaX > 0) && (x + getBallRadius() < world.getWidth())) {
				xflag = true;
				diff = getX() + (world.getWidth() - (x + getBallRadius()));
			}
			deltaX = -deltaX;
		}
		for (int i = 0; i < holes.size(); i++) {

			setD(this.intersezioni(this, holes.get(i)));
			System.out.println(getD());
			if (getD() == 1) {
				if (intersecate)
					return holePoint;
				deltaX = -deltaX;
				deltaY = -deltaY;
			}

			// int dst = (int)
			// Math.sqrt(Math.pow((holes.get(i).getX()-getX()),2)+
			// Math.pow((holes.get(i).getY()-getY()), 2));
			// if((dst)<=(getBallRadius()+holes.get(i).getRadius()))
			// {
			// deltaX=-deltaX;
			// deltaY=-deltaY;
			// }

			if (getD() != 1 && getD() != 0) {
				setX(getX() + deltaX);
				setY(getY() + deltaY);
				setIntersecate(true);
				holePoint = getD();
			}
			if (isIntersecate() && getY() < holes.get(i).getY())
				return holePoint;

		}
<<<<<<< HEAD
		if((((y-getBallRadius())+deltaY)<0))
		{
			if((deltaY<0) && (y-getBallRadius()>0))
			{
=======
		if ((((y + getBallRadius()) + deltaY) > (world.getHeight()))
				|| (((y - getBallRadius()) + deltaY) < 0)) {
			if ((deltaY < 0) && (y - getBallRadius() > 0)) {
>>>>>>> refs/remotes/origin/master
				yflag = true;
				diff = getY() - (y - getBallRadius());
			}
			if (deltaY > 0 && (y + getBallRadius()) < world.getHeight()) {
				yflag = true;
				diff = getY() + (world.getHeight() - (y + getBallRadius()));
			}
			deltaY = -deltaY;
		}
<<<<<<< HEAD
		if(((y+getBallRadius())+deltaY)>(world.getHeight()))
		{
			setDropped(true);
		}
		if(!xflag && !yflag)
		{
			setX(getX()+deltaX);
			setY(getY()+deltaY);
		}
		else{
			if(xflag)
			{
=======

		if (!xflag && !yflag) {
			setX(getX() + deltaX);
			setY(getY() + deltaY);
		} else {
			if (xflag) {
>>>>>>> refs/remotes/origin/master
				setX(diff);
				setY(getY() + deltaY);
			}
			if (yflag) {
				setX(getX() + deltaX);
				setY(diff);
			}
		}
		return 0;
	}

	public void moveRight() {
		if ((getX() + getBallRadius()) < world.getWidth())
			setX(getX() + 1);
	}

	public void moveLeft() {
		if ((getX() - getBallRadius()) > 0)
			setX(getX() - 1);
	}

	public void setWorld(final World world) {
		this.world = world;
	}

	public float getCorner() {
		return corner;
	}

	public void updateCorner() {
		deltaX = ((float) (speed * Math.cos(Math.toRadians(this.corner))));
		deltaY = ((float) (speed * (float) Math
				.sin(Math.toRadians(this.corner))));
	}

	public void setCorner(float corner) {
		this.corner = corner;
		updateCorner();
	}

	public int getBallRadius() {
		return ballRadius;
	}

	public void setBallRadius(int ballRadius) {
		this.ballRadius = ballRadius;
	}

	public void setColor(ArrayList<String> colors) {
		int casual = (int) (Math.random() * colors.size());

		this.color = colors.get(casual);
	}

	public List<Hole> getHoles() {
		return holes;
	}

	public void setHoles(List<Hole> holes) {
		this.holes = holes;
	}

	public synchronized int getD() {
		return d;
	}

	public synchronized void setD(int d) {
		this.d = d;
	}

	private int intersezioni(Ball ball, Hole eq) {
		double xnoto = 0;
		double yvar = 0;
		double xy = 0;
		double delta;
		double a;
		double b;
		double c;
		double x1;
		double x2;
		double y1;
		double y2;
		boolean spuria = false;

		EquazioniCirconferenza hole = eq.getEquation();
		EquazioniCirconferenza bal = ball.getEquation();
		if (bal.getX() == hole.getX()) {
			spuria = true;
			double yeq = bal.getY() + (-1 * hole.getY());
			double notoeq = bal.getNoto() + (-1 * hole.getNoto());
			yvar = (-1 * notoeq) / yeq;
			a = 1;
			b = bal.getX();
			c = (Math.pow(yvar, 2)) + (bal.getY() * yvar) + bal.getNoto();
			delta = (Math.pow(b, 2) + ((4 * a * c) * -1));
		} else {
			double xeq = bal.getX() + (-1 * hole.getX());
			double yeq = bal.getY() + (-1 * hole.getY());
			double notoeq = bal.getNoto() + (-1 * hole.getNoto());

			xy = (-1 * yeq) / xeq;
			xnoto = (-1 * notoeq) / xeq;
			double yquadro = Math.pow(xy, 2);
			double dprod = 2 * xy * xnoto;
			double nquadro = Math.pow(xnoto, 2);
			double sx = bal.getX() * xy;
			double nx = bal.getX() * xnoto;
			a = yquadro + 1;

			b = dprod + sx + bal.getY();
			c = nquadro + nx + bal.getNoto();
			delta = (Math.pow(b, 2) + ((4 * a * c) * -1));
		}

		if (delta < 0) {
			return 0;
		} else {
			if (!(ball.getColor().equals(eq.getColor()))) {
				return 1;
			}

			if (spuria) {
				x1 = (-b + Math.sqrt(delta)) / (2 * a);
				x2 = (-b - Math.sqrt(delta)) / (2 * a);
				y1 = yvar;
				y2 = yvar;
			} else {
				y1 = (-b + Math.sqrt(delta));
				y2 = (-b - Math.sqrt(delta));
				y1 /= 2 * a;
				y2 /= 2 * a;
				x1 = xy * y1 + xnoto;
				x2 = xy * y2 + xnoto;
			}

		}

		if ((eq.getX1() > eq.getX2() && eq.getY1() < eq.getY2())
				&& (x1 < eq.getX1() && x2 < eq.getX1() && x1 > eq.getX2()
						&& x2 > eq.getX2() && y1 > eq.getY())) {
			System.out.println("c");
			return eq.getScore();
		}
		if ((eq.getX1() < eq.getX2() && eq.getY1() < eq.getY2())
				&& (x1 > eq.getX1() && x1 < eq.getX2() && x2 > eq.getX1()
						&& x2 < eq.getX2() && y1 < eq.getY())) {
			System.out.println("c");
			return eq.getScore();
		}
		if ((eq.getX1() < eq.getX2() && eq.getY1() > eq.getY2())
				&& (x1 > eq.getX1() && x1 < eq.getX2() && x2 > eq.getX1()
						&& x2 < eq.getX2() && y1 < eq.getY())) {
			System.out.println("c");
			return eq.getScore();
		}
		if ((eq.getX1() > eq.getX2() && eq.getY1() > eq.getY2())
				&& (x1 < eq.getX1() && x1 > eq.getX2() && x2 < eq.getX1()
						&& x2 > eq.getX2() && y1 > eq.getY())) {
			System.out.println("c");
			return eq.getScore();
		}
		if ((eq.getY1() == eq.getY2() && eq.getX2() < eq.getX1())
				&& (x1 < eq.getX1() && x2 < eq.getX1() && x1 > eq.getX2()
						&& x2 > eq.getX2() && y1 > eq.getY())) {
			System.out.println("c");
			return eq.getScore();
		}
		if ((eq.getY1() == eq.getY2() && eq.getX1() < eq.getX2())
				&& (x1) > eq.getX1() && x2 > eq.getX1() && x1 < eq.getX2()
				&& x2 < eq.getX2() && y1 < eq.getY()) {
			System.out.println("c");
			return eq.getScore();
		}
		if ((eq.getX1() == eq.getX2() && eq.getY1() > eq.getY2())
				&& (y1 < eq.getY1() && y2 < eq.getY1() && y1 > eq.getY2()
						&& y2 > eq.getY2() && x1 < eq.getX())) {
			System.out.println("c");
			return eq.getScore();
		}
		if ((eq.getX1() == eq.getX2() && eq.getY1() < eq.getY2())
				&& (y1 > eq.getY1() && y2 > eq.getY1() && y1 < eq.getY2()
						&& y2 < eq.getY2() && x1 > eq.getX())) {
			System.out.println("c");
			return eq.getScore();
		}
		if ((eq.getX1() > eq.getX2() && eq.getY1() > eq.getY2())
				&& (y1 < eq.getY1() && y1 > eq.getY2() && y2 < eq.getY1()
						&& y2 > eq.getY2() && x1 < eq.getX())) {
			System.out.println("c");
			return eq.getScore();
		}
		if ((eq.getX1() > eq.getX2() && eq.getY1() < eq.getY2())
				&& (y1 > eq.getY1() && y1 < eq.getY2() && y2 > eq.getY1()
						&& y2 < eq.getY2() && x1 > eq.getX())) {
			System.out.println("c");
			return eq.getScore();
		}
		if ((eq.getX1() < eq.getX2() && eq.getY1() < eq.getY2())
				&& (y1 > eq.getY1() && y1 < eq.getY2() && y2 > eq.getY1()
						&& y2 < eq.getY2() && x1 > eq.getX())) {
			System.out.println("c");
			return eq.getScore();
		}
		if ((eq.getX1() < eq.getX2() && eq.getY1() > eq.getY2())
				&& (y1 < eq.getY1() && y1 > eq.getY2() && y2 < eq.getY1()
						&& y2 > eq.getY2() && x1 < eq.getX())) {
			System.out.println("c");
			return eq.getScore();
		}
		return 1;

	}

	public boolean isIntersecate() {
		return intersecate;
	}

	public void setIntersecate(boolean intersecate) {
		this.intersecate = intersecate;
	}

	public int getHolePoint() {
		return holePoint;
	}

	public void setHolePoint(int holePoint) {
		this.holePoint = holePoint;
	}

	public void reset() {
		this.setX((world.getWidth() / 2) - getBallRadius());
		this.setY(world.getHeight() - getBallRadius());
	}

<<<<<<< HEAD
	public boolean isDropped() {
		return dropped;
	}

	public void setDropped(boolean dropped) {
		this.dropped = dropped;
	}
	
=======
>>>>>>> refs/remotes/origin/master
}
