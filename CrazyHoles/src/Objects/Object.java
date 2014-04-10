//Prima o poi a cambiu.
package Objects;

	public abstract class Object {
	private int x,y;
	protected String color;
	protected World world;
	private int direzione;
	
	protected Object(int defaultX,int defaultY,final World world) {
		this.world=world;
		this.x=defaultX;
		this.y=defaultY;
	}
	
	public void setWorld(World world)
	{
		this.world=world;
	}
	
	public void setX(int a){
		this.x=a;
	}
	
	public void setY(int a){
		this.y=a;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getDirezione() {
		return direzione;
	}

	public void setDirezione(int direzione) {
		this.direzione = direzione;
	}
	
	protected abstract void setColor(String c);
	protected abstract String getColor();

}