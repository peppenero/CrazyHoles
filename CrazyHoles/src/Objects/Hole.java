package Objects;

import common.HasScore;

public class Hole extends Object implements HasScore {

	private int score = 0;
	private int radius;
	private float x1;
	private float x2;
	private float y1;
	private float y2;
	private int angle = 90;
	private int speed;

	public Hole(int s, int x, int y, int radius, World world, String color, int speed) {
		super(world);
		this.setColor(color);
		this.setScore(s);
		setX(x);
		setY(y);
		setRadius(radius);
		this.speed = speed;
		
		
		setX1(this.getX() + this.getRadius()
				* (float) Math.cos(Math.toRadians((getAngle() - 30))));
		setY1(this.getY() + this.getRadius()
				* (float) Math.sin(Math.toRadians((getAngle() - 30))));
		setX2(this.getX() + this.getRadius()
				* (float) Math.cos(Math.toRadians((getAngle() + 30))));
		setY2(this.getY() + this.getRadius()
				* (float) Math.sin(Math.toRadians((getAngle() + 30))));

	}

	public EquazioniCirconferenza getEquation() {
		EquazioniCirconferenza eq = new EquazioniCirconferenza(
				 this.getX(),  this.getY(), this.getRadius());
		return eq;
	}

	@Override
	public void setScore(int s) {
		this.score = s;
	}

	@Override
	public int getScore() {
		return this.score;
	}

	public void move() {
		setAngle(((angle + 10) % 360));
		setX1(this.getX() + this.getRadius()
				* (float) Math.cos(Math.toRadians((getAngle() - 30))));
		setY1(this.getY() + this.getRadius()
				* (float) Math.sin(Math.toRadians((getAngle() - 30))));
		setX2(this.getX() + this.getRadius()
				* (float) Math.cos(Math.toRadians((getAngle() + 30))));
		setY2(this.getY() + this.getRadius()
				* (float) Math.sin(Math.toRadians((getAngle() + 30))));

	}

	@Override
	public String getColor() {

		return this.color;
	}

	public void setWorld(final World world) {
		this.world = world;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public float getX1() {
		return x1;
	}

	public void setX1(float x1) {
		this.x1 = x1;
	}

	public float getX2() {
		return x2;
	}

	public void setX2(float x2) {
		this.x2 = x2;
	}

	public float getY1() {
		return y1;
	}

	public void setY1(float y1) {
		this.y1 = y1;
	}

	public float getY2() {
		return y2;
	}

	public void setY2(float y2) {
		this.y2 = y2;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

	protected void setColor(String color) {
		this.color = color;

	}
	
	public int getSpeed(){
		return speed;
	}

}
