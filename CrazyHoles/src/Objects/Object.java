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
	
	protected  void setColor()
	{
		int casual = (int) (Math.random()*2);
		switch(casual)
		{
			case 0:
			{
				this.color="rosso";
				break;
			}
			case 1:
			{
				this.color="verde";
				break;
			}
			case 2:
			{
				this.color="giallo";
				break;
			}
		
		}
		
	}
	public abstract String getColor();
	

}