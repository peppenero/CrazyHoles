//Prima o poi a cambiu.
package Objects;

	public abstract class Object {
	private int x,y;
	protected String color;
	
	protected Object() {
		this.x=0;
		this.y=0;
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
	
	protected abstract void setColor(String c);
}