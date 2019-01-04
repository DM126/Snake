
/**
 * Represents a non-head segment of the snake.
 * Body segments do not control their own movement
 */
public class Body extends Segment
{
	private Segment previous;
	
	public Body(Segment previous)
	{
		super(previous.getX(), previous.getY());
		this.previous = previous;
		previous.setNext(this);
	}
	
	public Segment getPrevious()
	{
		return previous;
	}
}
