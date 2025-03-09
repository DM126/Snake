
/**
 * The head of the snake.
 * This segment's movement determines the movement of the rest of the snake.
 */
public class Head extends Segment
{
	//directional movement
	private int mx;
	private int my;
	
	public Head(int x, int y) 
	{
		super(x, y);
		changeDirection(1, 0);
	}
	
	/**
	 * Changes the direction of the head.
	 * (One argument should be zero and the other should be 1 or -1)
	 * 
	 * @param mx the new x direction
	 * @param my the new y direction
	 */
	public void changeDirection(int mx, int my) 
	{
		//make sure the head doesn't go backwards and hit the next segment
		if ((this.getNext() == null) || ((x + mx != this.getNext().getX()) && (y + my != this.getNext().getY())))
		{
			this.mx = mx;
			this.my = my;
		}
	}
	
	public void move() 
	{
		int oldX = x;
		int oldY = y;
		
		x += mx;
		y += my;
		
		moveNext(oldX, oldY);
	}
	
	public int getMX() 
	{
		return mx;
	}
	
	public int getMY() 
	{
		return my;
	}
}
