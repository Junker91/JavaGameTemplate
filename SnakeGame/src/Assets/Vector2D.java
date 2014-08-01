package Assets;

public class Vector2D {
	
	private int x;
	private int y;
	
	public Vector2D(int x, int y) {
		this.x  = x;
		this.y = y;
	}
	
	public void setCoords(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int x() {
		return x;
	}
	
	public int y() {
		return y;
	}
	
}
