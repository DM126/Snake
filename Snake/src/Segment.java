
public abstract class Segment 
{
	protected int x, y; //location coordinates
	protected Body next;
	
	public Segment(int x, int y) 
	{
		setPosition(x, y);
		next = null;
	}
	
	/**
	 * @return the x coordinate of this segment
	 */
	public int getX() 
	{
		return x;
	}
	
	/**
	 * @return the y coordinate of this segment
	 */
	public int getY() 
	{
		return y;
	}
	
	/**
	 * Sets the x and y coordinates of this segment only.
	 * 
	 * @param x the new x coordinate
	 * @param y the new y coordinate
	 */
	private void setPosition(int x, int y) 
	{		
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @return the next segment attached to this one
	 */
	public Segment getNext()
	{
		return next;
	}
	
	/**
	 * Add a segment behind this one.
	 * 
	 * @param next the segment to add to this one
	 */
	public void setNext(Body next)
	{
		this.next = next;
	}
	
	/**
	 * Moves this segment and any attached to it.
	 * 
	 * @param x the new x coordinate
	 * @param y the new y coordinate
	 */
	public void move(int x, int y)
	{
		int oldX = this.x;
		int oldY = this.y;
		
		setPosition(x, y);
		
		moveNext(oldX, oldY);
	}
	
	/**
	 * Moves the next segment attached to this one so it is in
	 * the spot this one used to occupy.
	 * 
	 * @param x the new x coordinate
	 * @param y the new y coordinate
	 */
	public void moveNext(int x, int y)
	{		
		if (next != null)
		{
			next.move(x, y);
		}
	}
}
