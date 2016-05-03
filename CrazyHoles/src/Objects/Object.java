//Prima o poi a cambiu.
package Objects;

	public abstract class Object {
		
		
	private float x,y;
	protected String color;
	protected World world;
	
	protected Object(final World world) {
		this.world=world;
	}
	
	public void setWorld(World world)
	{
		this.world=world;
	}
	
	public void setX(float a){
		this.x=a;
	}
	
	public void setY(float a){
		this.y=a;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
	public abstract String getColor();
	

}